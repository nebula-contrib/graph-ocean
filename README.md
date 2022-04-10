# graph-ocean
## 框架描述
ORM of nebula-java
<br/>
一个 [nebula-java](https://github.com/vesoft-inc/nebula-java) 的 ORM 框架，graph ocean 意为图海洋，
<br/>
与 [Nebula Graph](https://github.com/vesoft-inc/nebula) 的星云相匹配，星辰大海。

## 设计理念

详细见 graph-ocean 设计文档.docx：https://github.com/Anyzm/graph-ocean/blob/main/graph-ocean%E8%AE%BE%E8%AE%A1%E6%96%87%E6%A1%A3.docx

## 使用示例

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

拥有丰富的 API，详情请见测试用例：com.github.anyzm.graph.ocean.GraphOceanExample]
