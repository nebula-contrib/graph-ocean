/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.domain;

import io.github.anyzm.graph.ocean.enums.EdgeDirectionEnum;

import java.util.Map;

/**
 * Description  EdgeQuery is used for
 *
 * @author Anyzm
 * Date  2021/8/10 - 14:18
 * @version 1.0.0
 */
public interface EdgeQuery extends GraphQuery {

    /**
     * 检索哪些顶点id的出边
     *
     * @param clazz 类类型
     * @param vertexIds 顶点id
     * @return 查询API
     */
    public EdgeQuery goFrom(Class clazz, String... vertexIds);

    /**
     * 根据方向,检索哪些顶点id的边l
     *
     * @param clazz 类类型
     * @param directionEnum 边方向
     * @param vertexIds 顶点id
     * @return 查询API
     */
    public EdgeQuery goFrom(Class clazz, EdgeDirectionEnum directionEnum, String... vertexIds);

    /**
     * 正向查询边steps步
     *
     * @param clazz 类类型
     * @param vertexIds 顶点id
     * @param steps 跳数
     * @return 查询API
     */
    public EdgeQuery goFromSteps(Class clazz, int steps, String... vertexIds);

    /**
     * 正向查询边：fromSteps-toSteps步之内
     *
     * @param clazz 类类型
     * @param vertexIds 顶点id
     * @param fromSteps 几跳开始
     * @param toSteps 到几跳
     * @return 查询API
     */
    public EdgeQuery goFromSteps(Class clazz, int fromSteps, int toSteps, String... vertexIds);


    /**
     * 根据方向,检索顶点id的stpes步内的边
     *
     * @param clazz 类类型
     * @param directionEnum 边方向
     * @param vertexIds 顶点id
     * @param steps 跳数
     * @return 查询API
     */
    public EdgeQuery goFromSteps(Class clazz, EdgeDirectionEnum directionEnum, int steps, String... vertexIds);

    /**
     * 根据方向,检索顶点id的fromSteps - toSteps跳步内的边
     *
     * @param clazz 类类型
     * @param directionEnum 边方向
     * @param vertexIds 顶点id
     * @param fromSteps 几跳开始
     * @param toSteps 到几跳
     * @return 查询API
     */
    public EdgeQuery goFromSteps(Class clazz, EdgeDirectionEnum directionEnum, int fromSteps, int toSteps, String... vertexIds);


    /**
     * 连接两个查询片段
     *
     * @param graphQuery 查询API
     * @return 查询API
     */
    @Override
    public EdgeQuery connectAdd(GraphQuery graphQuery);

    /**
     * limit
     *
     * @param size 条数
     * @return 查询API
     */
    @Override
    public EdgeQuery limit(int size);

    /**
     * limit
     *
     * @param offset 偏移量
     * @param size 条数
     * @return 查询API
     */
    @Override
    public EdgeQuery limit(int offset, int size);

    /**
     * 去重
     *
     * @return 查询PAI
     */
    @Override
    public EdgeQuery distinct();


    /**
     * 添加yield关键字
     *
     * @return 查询API
     */
    @Override
    public EdgeQuery yield();


    /**
     * 查询哪个标签的哪些属性
     *
     * @param clazz 类类型
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public EdgeQuery yield(Class clazz, String... fields);

    /**
     * 查询哪个标签的哪些属性
     *
     * @param symbol 符号
     * @param clazz 类类型
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public EdgeQuery yield(String symbol, Class clazz, String... fields);

    /**
     * 查询哪些属性
     *
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public EdgeQuery yield(String... fields);

    /**
     * 查询哪些属性
     *
     * @param fieldAlias 字段与别名映射
     * @return 查询API
     */
    @Override
    public EdgeQuery yield(Map<String, String> fieldAlias);


    /**
     * 查询哪个标签的哪些属性
     *
     * @param clazz 类类型
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public EdgeQuery yieldDistinct(Class clazz, String... fields);

    /**
     * 查询哪个标签的哪些属性
     *
     * @param prefix 前缀符号
     * @param clazz 类类型
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public EdgeQuery yieldDistinct(String prefix, Class clazz, String... fields);


    /**
     * 查询哪些属性
     *
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public EdgeQuery yieldDistinct(String... fields);


    /**
     * 查询哪些属性
     *
     * @param fieldAlias 字段与别名映射
     * @return 查询API
     */
    @Override
    public EdgeQuery yieldDistinct(Map<String, String> fieldAlias);


    /**
     * 管道分隔符
     *
     * @return 查询API
     */
    @Override
    public EdgeQuery pipe();

    /**
     * 根据某些属性分组查询
     *
     * @param clazz 类类型
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public EdgeQuery groupBy(Class clazz, String... fields);


    /**
     * 根据某些属性分组查询
     *
     * @param fields 字段
     * @return 查询API
     */
    @Override
    public EdgeQuery groupBy(String... fields);

    /**
     * count(*)
     *
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery countComma(String alias);

    /**
     * 对某字段计数并取别名
     *
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery countComma(String field, String alias);

    /**
     * 计算属性
     *
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery countComma(Class clazz, String field, String alias);

    /**
     * count条件表达式
     *
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery countComma(GraphExpression graphExpression, String alias);


    /**
     * 对某字段计数并取别名
     *
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery count(String field, String alias);

    /**
     * 对某些字段计数并取别名
     *
     * @param fieldAlias 字段与别名map
     * @return 查询API
     */
    @Override
    public EdgeQuery count(Map<String, String> fieldAlias);

    /**
     * count(*)
     *
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery count(String alias);

    /**
     * 计算属性
     *
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery count(Class clazz, String field, String alias);


    /**
     * count条件表达式
     *
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery count(GraphExpression graphExpression, String alias);

    /**
     * avg条件表达式
     *
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery avg(GraphExpression graphExpression, String alias);

    /**
     * 某个字段的平均值
     *
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery avg(String field, String alias);

    /**
     * 某个字段的平均值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery avg(Class clazz, String field, String alias);

    /**
     * avgComma条件表达式
     *
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery avgComma(GraphExpression graphExpression, String alias);

    /**
     * 某个字段的平均值，并且逗号分割
     *
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery avgComma(String field, String alias);

    /**
     * 某个字段的平均值，并且逗号分割
     *
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery avgComma(Class clazz, String field, String alias);

    /**
     * sum条件表达式
     *
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery sum(GraphExpression graphExpression, String alias);

    /**
     * 某个字段的求和
     *
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery sum(String field, String alias);

    /**
     * 某个字段的求和
     *
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery sum(Class clazz, String field, String alias);

    /**
     * sumComma条件表达式
     *
     * @param graphExpression 表达式
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery sumComma(GraphExpression graphExpression, String alias);

    /**
     * 某个字段的求和，并且逗号分割
     *
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery sumComma(String field, String alias);

    /**
     * 某个字段的求和，并且逗号分割
     *
     * @param clazz 类类型
     * @param field 字段
     * @param alias 别名
     * @return 查询API
     */
    @Override
    public EdgeQuery sumComma(Class clazz, String field, String alias);


    /**
     * 逗号分隔
     *
     * @return 查询API
     */
    @Override
    public EdgeQuery comma();

    /**
     * 条件过滤
     *
     * @param graphCondition 表达式
     * @return 查询API
     */
    @Override
    public EdgeQuery where(GraphCondition graphCondition);

}
