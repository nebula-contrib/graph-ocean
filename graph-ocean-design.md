# Graph Ocean 设计文档

Graph Ocean 又名：Nebula Java ORM。

## Session 管理

由于 [nebula-java-client](https://github.com/vesoft-inc/nebula-java) 中 Session 是由 NebulaPool 所产生，且 `com.vesoft.nebula.client.graph.net.NebulaPool#getSession` 每次获取 Session 都需要传入用户名、密码等不变配置，所以想要加强 Session 则需要同步加强 NebulaPool 类

![image1](https://user-images.githubusercontent.com/38887077/162661260-9e95511c-1ad6-4496-8d5d-d9505545d069.png)

![image2](https://user-images.githubusercontent.com/38887077/162661263-9e6975e8-da75-449e-bd86-cabc54b75440.png)

加强的连接池管理和 Session 管理有如下优点：

1. 获取 Session 时不用每次指定用户名、密码和重连次数，交由加强类管理；
2. 微服务时代，每个应用通常只对应了一个图空间（space），加强之后不必每次指定 space；
3. 加强类区分了查询和更新操作，并且查询封装了自己的返回 POJO，为实体类的转换打下基础。

## 注解定义

本套 ORM 框架是使用注解将实体映射到数据库元素的，有点仿 JPA 的意思。

注解包括：

* GraphVertex：顶点 Tag
* GraphEdge：边类型
* GraphProperty：图属性

![image3](https://user-images.githubusercontent.com/38887077/162661267-de834267-dfb5-407d-951b-621314f94621.png)

![image4](https://user-images.githubusercontent.com/38887077/162661276-6c579ea1-291c-43e8-915e-410ff36400f5.png)

![image5](https://user-images.githubusercontent.com/38887077/162661278-ef023679-0812-4504-ac01-378ce266669d.png)

其中包括主键策略（因为没有图空间实体映射，主键策略放在了顶点 TAG 的注解上），ID 是否作为属性、属性格式工厂等设置。

## 整体架构

精炼之后的整体骨架设计图如下：

![image6](https://user-images.githubusercontent.com/38887077/162661283-38b70e93-88fe-4c8c-af68-6e20ca5a0005.png)

有了上面的注解定义，于是应该有注解解释器，将注解标注的实体映射成图空间中的元素，而元素也是一种抽象概念，它有两种形式：TAG 和 EDGE，这两种有很多共同的特性。

![image7](https://user-images.githubusercontent.com/38887077/162661284-94e4d98c-abcc-42d1-be02-d7aae3dda2ed.png)

![image8](https://user-images.githubusercontent.com/38887077/162661285-d4e1887b-c8c1-4084-92ac-9d520c74a097.png)

注解映射成抽象元素的过程，一般不常变，所以每次应用第一次解析成抽象实体之后，都应该缓存起来，所以有了缓存管理。

![image9](https://user-images.githubusercontent.com/38887077/162661286-abc34faf-1b12-473c-9b54-71fde036d6f9.png)

将实体解析映射成抽象图元素以及抽象图元素的实体是一个复杂的解析过程，并且 Tag 和 Edge 的解析略有区别，所以区别对待。

![image10](https://user-images.githubusercontent.com/38887077/162661289-039fa381-27c7-4180-acf2-9dd29f7239b0.png)

![image11](https://user-images.githubusercontent.com/38887077/162661293-939f658d-7efe-4fc4-8e2e-97d79490575c.png)

![image12](https://user-images.githubusercontent.com/38887077/162661296-40212437-89ea-49c9-9b35-8053e8c407b6.png)

![image13](https://user-images.githubusercontent.com/38887077/162661299-0f348034-fa37-4a30-ac1a-1417cbe8d330.png)

![image14](https://user-images.githubusercontent.com/38887077/162661302-1077a017-9fdb-4da2-8ed7-78519864406e.png)

POJO 中的属性值和 Nebula 中的字段值不一定是一致的，所以需要向用户提供开放接口。

![image15](https://user-images.githubusercontent.com/38887077/162661304-7b6899fd-cf5c-4c1a-b92f-080bfed6f9a9.png)

在基础 Mapper 中封装了写和查询的方法，但写和查的引擎又是分开的。

![image16](https://user-images.githubusercontent.com/38887077/162661307-9b322f8b-fd0c-4a7a-bf07-b3082d772d53.png)

![image17](https://user-images.githubusercontent.com/38887077/162661309-ef17c236-38a0-4f5a-9537-d2a9a849599c.png)

![image18](https://user-images.githubusercontent.com/38887077/162661311-46160d1c-5386-4728-83cf-de4dab0011ae.png)

![image19](https://user-images.githubusercontent.com/38887077/162661314-94be517c-c689-417c-9f34-0f15f8f8c4a1.png)

![image20](https://user-images.githubusercontent.com/38887077/162661321-cdeaf645-742b-4df0-a6eb-1325f10d476b.png)

更新和查询都会运用缓存中的抽象元素实体，所以缓存中的元素统一由 GraphTypeManager 管理

![image21](https://user-images.githubusercontent.com/38887077/162661325-e807c15d-5a5f-405c-a1a2-922419e59387.png)

查询中可能会有一些比较复杂的语句，比如条件过滤，case when 等，所以引入了 `GraphExpression` 和 `GraphCondition` 两种概念，而 `GraphCondition` 又继承了 `GraphExpression`。

![image22](https://user-images.githubusercontent.com/38887077/162661327-1be54c3b-2859-4ca8-8c03-bcdfd49ab009.png)

![image23](https://user-images.githubusercontent.com/38887077/162661328-5437fce8-dbfa-481d-ae36-b1a84788e994.png)

结果返回封装类：`QueryResult`。

上面的设计完成之后，再辅助一些工具类和异常管理，整个 ORM 框架即呼之欲出。