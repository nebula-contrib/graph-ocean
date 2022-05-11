# graph-ocean详细使用文档
## 主要接口或类描述
<br/>
io.github.anyzm.graph.ocean.annotation.GraphVertex：注解用来标识顶点Tag的实体
<br/>
value是Tag名称，
<br/>
keyPolicy是主键策略（io.github.anyzm.graph.ocean.enums.GraphKeyPolicy，uuid,hash,string类型），
<br/>
idAsField顶点id是否作为图属性字段。
<br/>
<br/>
io.github.anyzm.graph.ocean.annotation.GraphEdge：注解用来标识边类型的实体
<br/>
value是边名称，
<br/>
srcVertex是边起点Tag实体，
<br/>
dstVertex是边终点Tag实体，
<br/>
srcIdAsField起点id是否作为图属性字段，
<br/>
dstIdAsField终点id是否作为图属性字段。
<br/>
<br/>
io.github.anyzm.graph.ocean.annotation.GraphProperty：注解用来标识图属性字段
<br/>
value是图里面的字段名称，
<br/>
dataType是数据类型，io.github.anyzm.graph.ocean.enums.GraphDataTypeEnum是数据类型枚举，
<br/>
required是否是必需字段，目前这个还没有真正限制，属于是预留字段，
<br/>
propertyTypeEnum：图属性类型，用io.github.anyzm.graph.ocean.enums.GraphPropertyTypeEnum枚举来标识，
<br/>
formatter：用来做字段值的格式转换，需要实现io.github.anyzm.graph.ocean.dao.GraphValueFormatter接口，其中format方法是将字段值到数据库值，reformat是将数据库值反转为字段值
<br/>
io.github.anyzm.graph.ocean.annotation.GraphProperty：注解用来标识图属性
<br/>
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper：是操作数据库的基础接口
<br/>
io.github.anyzm.graph.ocean.mapper.NebulaGraphMapper：是其主要实现接口
<br/>

##### 方法列表：
io.github.anyzm.graph.ocean.dao.GraphMapper.saveVertexEntities：批量保存顶点
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.saveEdgeEntitiesWithVertex：批量保存边和顶点，需要提供两个自定义的顶点生成方法，其中提供的参数是边里面的顶点值
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.saveEdgeEntities：批量保存边，不会保存顶点（假设图里面原来没有顶点的时候，直接查询边可能会出现BAD_DATA）
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeBatchUpdateSql：指定空间批量执行ngql
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeUpdateSql(java.lang.String, java.lang.String)
<br/>


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

