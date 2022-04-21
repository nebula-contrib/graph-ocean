/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.domain;

import java.util.Map;

/**
 * Description  VertexQuery is used for
 *
 * @author Anyzm
 * Date  2021/8/10 - 14:16
 * @version 1.0.0
 */
public interface VertexQuery extends GraphQuery {

    /**
     * 查询哪个tag的哪些顶点
     *
     * @param clazz 类类型
     * @param vertexIds 顶点id
     * @return 顶点查询API
     */
    public VertexQuery fetchPropOn(Class clazz, String... vertexIds);


    /**
     * 连接两个查询片段
     *
     * @param graphQuery 查询API
     * @return 查询API
     */
    @Override
    public VertexQuery connectAdd(GraphQuery graphQuery);

    /**
     * limit
     *
     * @param size 限制条数
     * @return 查询API
     */
    @Override
    public VertexQuery limit(int size);

    /**
     * limit
     *
     * @param offset 偏移量
     * @param size size
     * @return 查询API
     */
    @Override
    public VertexQuery limit(int offset, int size);

    /**
     * 查询哪个标签的哪些属性
     *
     * @param clazz 类类行
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public VertexQuery yield(Class clazz, String... fields);

    /**
     * 去重
     *
     * @return 查询API
     */
    @Override
    public VertexQuery distinct();

    /**
     * 添加yield关键字
     *
     * @return 查询API
     */
    @Override
    public VertexQuery yield();


    /**
     * 查询哪个标签的哪些属性
     *
     * @param symbol 符号
     * @param clazz 类类型
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public VertexQuery yield(String symbol, Class clazz, String... fields);

    /**
     * 查询哪些属性
     *
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public VertexQuery yield(String... fields);


    /**
     * 查询哪些属性
     *
     * @param fieldAlias 字段与别名映射
     * @return 查询API
     */
    @Override
    public VertexQuery yield(Map<String, String> fieldAlias);

    /**
     * 查询哪个标签的哪些属性
     *
     * @param clazz 类类型
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public VertexQuery yieldDistinct(Class clazz, String... fields);


    /**
     * 查询哪个标签的哪些属性
     *
     * @param prefix 符号
     * @param clazz 类类型
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public VertexQuery yieldDistinct(String prefix, Class clazz, String... fields);

    /**
     * 查询哪些属性
     *
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public VertexQuery yieldDistinct(String... fields);


    /**
     * 查询哪些属性
     *
     * @param fieldAlias 字段与别名映射
     * @return 查询API
     */
    @Override
    public VertexQuery yieldDistinct(Map<String, String> fieldAlias);

    /**
     * 管道分隔符
     *
     * @return 查询API
     */
    @Override
    public VertexQuery pipe();

    /**
     * 根据某些属性分组查询
     *
     * @param clazz 类类型
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public VertexQuery groupBy(Class clazz, String... fields);


    /**
     * 根据某些属性分组查询
     *
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public VertexQuery groupBy(String... fields);

    /**
     * count(*)
     *
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery countComma(String alias);

    /**
     * 对某字段计数并取别名
     *
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery countComma(String field, String alias);

    /**
     * 计算属性
     *
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery countComma(Class clazz, String field, String alias);

    /**
     * count条件表达式
     *
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery countComma(GraphExpression graphExpression, String alias);


    /**
     * 对某字段计数并取别名
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery count(String field, String alias);


    /**
     * 对某些字段计数并取别名
     * @param fieldAlias 字段与别名map
     * @return 查询API
     */
    @Override
    public VertexQuery count(Map<String, String> fieldAlias);

    /**
     * count(*)
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery count(String alias);

    /**
     * 计算属性
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery count(Class clazz, String field, String alias);

    /**
     * count条件表达式
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery count(GraphExpression graphExpression, String alias);

    /**
     * avg条件表达式
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery avg(GraphExpression graphExpression, String alias);

    /**
     * 某个字段的平均值
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery avg(String field, String alias);

    /**
     * 某个字段的平均值
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery avg(Class clazz, String field, String alias);

    /**
     * avgComma条件表达式
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery avgComma(GraphExpression graphExpression, String alias);

    /**
     * 某个字段的平均值，并且逗号分割
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery avgComma(String field, String alias);

    /**
     * 某个字段的平均值，并且逗号分割
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery avgComma(Class clazz, String field, String alias);

    /**
     * sum条件表达式
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery sum(GraphExpression graphExpression, String alias);

    /**
     * 某个字段的求和
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery sum(String field, String alias);

    /**
     * 某个字段的求和
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery sum(Class clazz, String field, String alias);

    /**
     * sumComma条件表达式
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery sumComma(GraphExpression graphExpression, String alias);

    /**
     * 某个字段的求和，并且逗号分割
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery sumComma(String field, String alias);

    /**
     * 某个字段的求和，并且逗号分割
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public VertexQuery sumComma(Class clazz, String field, String alias);

    /**
     * 逗号分隔
     * @return 查询API
     */
    @Override
    public VertexQuery comma();

    /**
     *
     * @param graphCondition 条件过滤
     * @return 查询API
     */
    @Override
    public VertexQuery where(GraphCondition graphCondition);

}
