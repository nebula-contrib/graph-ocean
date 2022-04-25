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
        VertexQuery query = NebulaVertexQuery.build().fetchPropOn(User.class, "UR123")
                .yield("userName");
        QueryResult rows = nebulaGraphMapper.executeQuery(query);
```

拥有丰富的 API，详情请见测试用例：com.github.anyzm.graph.ocean.GraphOceanExample


## 写在最后
由于本框架目前几乎是由一人开发完成，所以难免有些不完善或者不优雅的地方，望大家谅解。
<br/>
初期最佳实践是下载源码自己在本地根据自己的需要修改，同时也希望能有更多的人能够参与其中一起开发完善这个框架。


