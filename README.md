# graph-ocean
## 框架描述
ORM of nebula-java
<br/>
一个 [nebula-java](https://github.com/vesoft-inc/nebula-java) 的 ORM 框架，graph ocean 意为图海洋，
<br/>
与 [Nebula Graph](https://github.com/vesoft-inc/nebula) 的星云相匹配，星辰大海。

## 设计理念

详细见 graph-ocean 设计文档：https://github.com/Anyzm/graph-ocean/blob/main/graph-ocean-design.md

## 使用示例
maven项目引入坐标：
```java
<dependency>
  <groupId>io.github.anyzm</groupId>
  <artifactId>graph-ocean</artifactId>
  <version>1.0.0</version>
</dependency>
```
<<<<<<< HEAD
=======
<br/>

nebula 3.x的maven项目引入坐标：
```
<dependency>
    <groupId>io.github.anyzm</groupId>
    <artifactId>graph-ocean</artifactId>
    <version>3.0.0</version>
</dependency>
```

<br/>

>>>>>>> c37ab84... Update README.md
1.0.0支持的是nebula-java2.0.0-rc1，这个版本的java客户端亲测可以支持nebula 2.5.0，猜想应该也能支持nebula2系列的各服务端版本（未一一验证，大家可以先在测试环境验证）。
<br/>
另外如果nebula-java客户端的连接池API没有改动的话，也可以在pom中剔除nebula-java，然后引入自己需要的客户端版本。

```java
        NebulaGraphMapper nebulaGraphMapper = nebulaGraphMapper(nebulaPoolSessionManager(
                nebulaPool(nebulaPoolConfig())));
        User user = new User("UR123", "张三");
        //保存顶点
        int i = nebulaGraphMapper.saveVertexEntities(Lists.newArrayList(user));
        //查询顶点
        List<User> users = nebulaGraphMapper.fetchVertexTag(User.class, "UR123");
        //保存边和查询边类似
        Follow follow = new Follow("UR123", "UR234", 1);
        //保存边
        nebulaGraphMapper.saveEdgeEntities(Lists.newArrayList(follow));
        //查询出边
        List<Follow> follows = nebulaGraphMapper.goOutEdge(Follow.class, "UR123");
        //查询反向边
        List<Follow> fans = nebulaGraphMapper.goReverseEdge(Follow.class, "UR123");
        //查询API
        VertexQuery queryUserName = NebulaVertexQuery.build().fetchPropOn(User.class, "UR123")
                .yield(User.class,"userName");
        QueryResult rows = nebulaGraphMapper.executeQuery(queryUserName);
        System.out.println(rows);
```

拥有丰富的 API，详情请见测试用例：com.github.anyzm.graph.ocean.GraphOceanExample
<br/>
也可以查看更详细一点的使用文档：https://github.com/Anyzm/graph-ocean/blob/main/detailed_introduction.md


## 写在最后
由于本框架目前几乎是由一人开发完成，所以难免有些不完善或者不优雅的地方，望大家谅解。
<br/>
初期最佳实践是下载源码自己在本地根据自己的需要修改，同时也希望能有更多的人能够参与其中一起开发完善这个框架。


