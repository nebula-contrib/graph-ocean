# graph-ocean详细使用文档
## 主要接口或类描述
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
io.github.anyzm.graph.ocean.dao.GraphMapper.executeBatchUpdateSql：指定空间批量执行更新ngql
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeUpdateSql(java.lang.String, java.lang.String)：指定空间单条执行更新ngql
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeUpdateSql(java.lang.String)：使用默认图空间执行单条更新ngql
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeQuerySql(java.lang.String)：使用默认图空间执行查询ngql
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeQuerySql(java.lang.String, java.lang.String)：指定图空间执行查询ngql
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeQuerySql(java.lang.String, java.lang.Class<T>)：使用默认图空间执行查询ngql，并且返回指定的实体类
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeQuery(io.github.anyzm.graph.ocean.domain.GraphQuery)：使用默认图空间执行查询API
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeQuery(java.lang.String, io.github.anyzm.graph.ocean.domain.GraphQuery)：使用指定的图空间执行查询API
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.executeQuery(io.github.anyzm.graph.ocean.domain.GraphQuery, java.lang.Class<T>)：使用默认图空间执行查询API并返回相应的实体类
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.goOutEdge：使用默认图空间查询相应的实体类对应的出边，可以传入多个顶点id
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.goReverseEdge：使用默认图空间查询相应的实体类对应的出边，可以传入多个顶点id
<br/>
io.github.anyzm.graph.ocean.dao.GraphMapper.fetchVertexTag：使用默认图空间查询相应的实体类对应的顶点，可以传入多个顶点id
<br/>
<br/>
GraphMapper这个接口有一个默认的实现类NebulaGraphMapper，它的构造方法如下，一般来说顶点、边的引擎以及类型管理我们用默认的即可。
<br/>
但是我们还要传入NebulaPoolSessionManager（session管理）和space（图空间）
```java
  public NebulaGraphMapper(NebulaPoolSessionManager nebulaPoolSessionManager,
                             String space) {
        this.graphTypeManager = new DefaultGraphTypeManager();
        this.graphUpdateVertexEngineFactory = new NebulaUpdateVertexEngineFactory();
        this.graphUpdateEdgeEngineFactory = new NebulaUpdateEdgeEngineFactory();
        this.nebulaPoolSessionManager = nebulaPoolSessionManager;
        this.space = space;
        init();
    }

    public NebulaGraphMapper(NebulaPoolSessionManager nebulaPoolSessionManager,
                             String space,
                             GraphUpdateVertexEngineFactory graphUpdateVertexEngineFactory,
                             GraphUpdateEdgeEngineFactory graphUpdateEdgeEngineFactory) {
        this.graphTypeManager = new DefaultGraphTypeManager();
        this.graphUpdateVertexEngineFactory = graphUpdateVertexEngineFactory;
        this.graphUpdateEdgeEngineFactory = graphUpdateEdgeEngineFactory;
        this.nebulaPoolSessionManager = nebulaPoolSessionManager;
        this.space = space;
        init();
    }
```
<br/>
NebulaPoolSessionManager的构造方法如下，其中NebulaPool是nebula-java池化类，剩下的就是用户名、密码和是否重连了。

```java
    public NebulaPoolSessionManager(NebulaPool nebulaPool, String userName, String password, boolean reconnect) {
        this.nebulaPool = nebulaPool;
        this.userName = userName;
        this.password = password;
        this.reconnect = reconnect;
    }
```
<br/>
讲了这么多，我们不如来看一个生产在使用的例子吧。
<br/>
下面就是一个我们生产正在使用的初始化的方法，这样初始化之后我们可以在任意的spring容器管理的类里面用注解注入NebulaGraphMapper实例了
<br/>
这样也就能愉快的使用上面的一些方法了

## 初始化示例
```java
@Configuration
public class NebulaPoolInit {

    @Value("${nebula.pool.max.connect.size:1000}")
    private int nebulaPoolMaxConnSize;

    @Value("${nebula.pool.min.connect.size:50}")
    private int nebulaPoolMinConnSize;

    @Value("${nebula.pool.idle.time:180000}")
    private int nebulaPoolIdleTime;

    @Value("${nebula.pool.timeout:300000}")
    private int nebulaPoolTimeout;

    @Value("${nebula.cluster.address:}")
    private String nebulaCluster;

    @Value("${nebula.userName:}")
    private String userName;

    @Value("${nebula.password:}")
    private String password;

    @Value("${socialBook.nebula.space:}")
    private String space;

    @Bean
    public NebulaPoolConfig nebulaPoolConfig() {
        NebulaPoolConfig nebulaPoolConfig = new NebulaPoolConfig();
        nebulaPoolConfig.setMaxConnSize(nebulaPoolMaxConnSize);
        nebulaPoolConfig.setMinConnSize(nebulaPoolMinConnSize);
        nebulaPoolConfig.setIdleTime(nebulaPoolIdleTime);
        nebulaPoolConfig.setTimeout(nebulaPoolTimeout);
        return nebulaPoolConfig;
    }

    @Bean
    public NebulaPool nebulaPool(NebulaPoolConfig nebulaPoolConfig) throws UnknownHostException {
        List<HostAddress> addresses = null;
        try {
            String[] hostPorts = StringUtils.split(nebulaCluster, ",");
            addresses = Lists.newArrayListWithExpectedSize(hostPorts.length);
            for (String hostPort : hostPorts) {
                String[] linkElements = StringUtils.split(hostPort, ":");
                HostAddress hostAddress = new HostAddress(linkElements[0], Integer.valueOf(linkElements[1]));
                addresses.add(hostAddress);
            }
        } catch (Exception e) {
            throw new RuntimeException("nebula数据库连接信息配置有误，正确格式：ip1:port1,ip2:port2");
        }
        NebulaPool pool = new NebulaPool();
        pool.init(addresses, nebulaPoolConfig);
        return pool;
    }

    @Bean
    public NebulaPoolSessionManager nebulaPoolSessionManager(NebulaPool nebulaPool) {
        return new NebulaPoolSessionManager(nebulaPool, userName, password, true);
    }
    @Bean
    public NebulaGraphMapper nebulaGraphMapper(NebulaPoolSessionManager nebulaPoolSessionManager) {
        return new NebulaGraphMapper(nebulaPoolSessionManager, space);
    }
}
```

## 边注解使用示例
下面是一个通讯类边关系的注解使用示例

```java
@GraphEdge(value = "e_address_book", srcVertex = VertexEntity.class, dstVertex = VertexEntity.class)
@Setter
@Getter
public class EAddressBookEntity {
    @GraphProperty(value = "user_no1", required = true, propertyTypeEnum = GraphPropertyTypeEnum.GRAPH_EDGE_SRC_ID)
    private String userNo1;
  
    @GraphProperty(value = "user_no2", required = true, propertyTypeEnum = GraphPropertyTypeEnum.GRAPH_EDGE_DST_ID)
    private String userNo2;
    /**
     * userNo1对userNo2的姓名备注
     */
    @GraphProperty(value = "contact_name", formatter = ContractNameGraphValueFormatter.class)
    private String contactName = "";
    /**
     * 回溯时可以使用userNo1的授信申请时间
     */
    @GraphProperty(value = "create_time", dataType = GraphDataTypeEnum.TIMESTAMP, formatter = DateGraphValueFormatter.class)
    private Date createTime;

    @GraphProperty(value = "update_time", dataType = GraphDataTypeEnum.TIMESTAMP, formatter = DateGraphValueFormatter.class)
    private Date updateTime;

}
```
上面的DateGraphValueFormatter类主要作用是时间的转换，代码如下：
```java
@Slf4j
public class DateGraphValueFormatter implements GraphValueFormatter {

    @Override
    public Object format(Object oldValue) {
        if (oldValue instanceof Date) {
            Date date = (Date) oldValue;
            long value = date.getTime() / 1000;
            log.debug("格式化oldValue={},value={}", oldValue, value);
            return value;
        }
        return null;
    }
    /**
     * nebula属性值反转为javaBean值
     *
     * @param nebulaValue
     * @return
     */
    @Override
    public Object reformat(Object nebulaValue) {
        if (nebulaValue instanceof Long) {
            return (Long)nebulaValue * 1000L;
        }
        return nebulaValue;
    }
}
```
## TAG注解使用示例
下面是顶点TAG注解的使用示例
```java
@GraphVertex(value = "user", keyPolicy = GraphKeyPolicy.string_key)
@Setter
@Getter
public class VertexEntity {
    @GraphProperty(value = "user_no", required = true, propertyTypeEnum = GraphPropertyTypeEnum.GRAPH_VERTEX_ID)
    private String userNo;
    @GraphProperty(value = "mobile_no_encryptx")
    private String mobileNoEncryptx;
    @GraphProperty(value = "mobile_no_md5x")
    private String mobileNoMd5x;
    @GraphProperty(value = "birth_date")
    private String birthDate;
    @GraphProperty(value = "gender")
    private String gender;
    @GraphProperty(value = "credit_amt", dataType = GraphDataTypeEnum.DOUBLE)
    private Double creditAmt;
    @GraphProperty(value = "credit_datetime", dataType = GraphDataTypeEnum.TIMESTAMP, formatter = DateGraphValueFormatter.class)
    private Date creditDatetime;
    @GraphProperty(value = "register_time", dataType = GraphDataTypeEnum.TIMESTAMP, formatter = DateGraphValueFormatter.class)
   private Date registerTime;
}
```
## 查询API的使用示例
```java
    public GraphQuery getGraphQuery(Class labelClazz, LocalDate backtraceDate, int steps, int limitSize, String userNo, String... userNos) {
        //go from 指定实体类（也就是指定边），指定方向，指定步数，指定顶点id（用户号）
        EdgeQuery edgeQuery = NebulaEdgeQuery.build().goFromSteps(labelClazz, EdgeDirectionEnum.BIDIRECT, 1, steps, userNos);
         if (backtraceDate != null) {
            //设置时间范围
           backtraceDate = backtraceDate.plusDays(1);
           Date date = Date.from(backtraceDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
           GraphCondition createTimeCondition = NebulaCondition.build().andLessEqualsWithOriginalValue(labelClazz, "createTime", date);
           if (defaultCondition != null) {
               createTimeCondition.and(defaultCondition);
           }
           graphQuery.where(createTimeCondition);
        } 
        //返回边对应的顶点的属性并且limit限制条数，最后管道符号再指定相应的返回内容
        //其中getYieldShortQuery()是抽象类的方法，有不同的实现，比如有求平均值的，有求最大值的等等
        return edgeQuery.yieldDistinct("$$.", VertexEntity.class, fieldArray()).limit(limitSize).pipe().yield().connectAdd(getYieldShortQuery());
    }
```
下面是其中一个getShortQuery() 的实现，类似这样，你可以根据自己的业务需求制定自己的写法
```java

    @Override
    public Pair<String, GraphExpression> getAliasExpression() {
        NebulaExpression nebulaExpression = NebulaExpression.build().caseWhenThenEnd(getGraphCondition(), "(date()-date($-.birthDate))/365");
        return new Pair<>("AvgAge", nebulaExpression);
    }

    @Override
    public GraphQuery getShortQuery() {
        Pair<String, GraphExpression> pair = getAliasExpression();
        return NebulaEdgeQuery.build().avgComma(pair.getValue(), pair.getKey());
    }

    /**
     * 获取过滤条件
     *
     * @return
     */
    @Override
    public GraphCondition getGraphCondition() {
        GraphCondition graphCondition = super.getGraphCondition();
        return graphCondition.andSymbol("$-.birthDate", " is not ", null)
                .andSymbol("$-.birthDate", "!=", "");
    }
```
其实API查询有各种各样的实现方式，也有不同的API，建议直接去看源码和javadoc注解，结合不同的设计模式可以玩出不同的花样，实现的本质就是ngql的顺序拼接，所以学好ngql才能更好的使用API查询。
<br/>
有人会疑惑，既然学好了ngql，我还用API查询干啥，直接写语句不行吗？直接写ngql，不用API有以下不方便：
<br/>
1、不方便移植，每次ngql编写都是一次性的；
<br/>
2、ngql比较长的时候，可读性很低；
<br/>
3、到处都是ngql，代码不够优雅，也不能很好的结合设计模式实现多变而又复杂的业务需求。
<br/>
总之，学好ngql是使用nebula graph必须的也是最关键的一步，学好了之后再配合ORM框架才能快速地开发并且快速的解决问题，最后无往不利。


