/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.domain;

import java.util.Collection;

/**
 * Description  GraphCondition is used for
 *
 * @author Anyzm
 * Date  2021/8/10 - 16:02
 * @version 1.0.0
 */
public interface GraphCondition extends GraphExpression {

    /**
     * 将已有的条件过滤括号起来
     *
     * @return 条件表达式
     */
    public GraphCondition bracket();

    /**
     * 并关系连接
      * @param graphCondition 表达式
     * @return 条件表达式
     */
    public GraphCondition and(GraphCondition graphCondition);

    /**
     * in过滤
     *
     * @param field 字段
     * @param collection 值集合
     * @return 条件表达式
     */
    public <T> GraphCondition andIn(String field, Collection<T> collection);

    /**
     * in过滤
     *
     * @param clazz 类类型
     * @param field 字段
     * @param collection 集合
     * @return 表达式
     */
    public <T> GraphCondition andIn(Class clazz, String field, Collection<T> collection);

    /**
     * not in过滤
     *
     * @param field 字段
     * @param collection 集合
     * @return 表达式
     */
    public <T> GraphCondition andNotIn(String field, Collection<T> collection);

    /**
     * not in过滤
     *
     * @param clazz 类类型
     * @param field 字段
     * @param collection 集合
     * @return 表达式
     */
    public <T> GraphCondition andNotIn(Class clazz, String field, Collection<T> collection);

    /**
     * 最真实的等于条件过滤
     *
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andEquals(String field, Object value);

    /**
     * 自定义且条件l
     *
     * @param field 字段
     * @param symbol 比较符号
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andSymbol(String field, String symbol, Object value);

    /**
     * 查询过滤，用真实的过滤值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andEqualsWithFinallyValue(Class clazz, String field, Object value);


    /**
     * 查询过滤，用代加工的值
     * 当加工器不存在时效果等同于 andEqualsWithFinallyValue
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andEqualsWithOriginalValue(Class clazz, String field, Object value);

    /**
     * 最真实的大于等于
     *
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andBiggerEquals(String field, Object value);

    /**
     * 大于等于最终值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andBiggerEqualsWithFinallyValue(Class clazz, String field, Object value);


    /**
     * 大于等于源值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andBiggerEqualsWithOriginalValue(Class clazz, String field, Object value);

    /**
     * 最真实的大于
     *
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andBigger(String field, Object value);

    /**
     * 大于源值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andBiggerWithOriginalValue(Class clazz, String field, Object value);


    /**
     * 大于最终值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andBiggerWithFinallyValue(Class clazz, String field, Object value);

    /**
     * 最真实的小于等于
     *
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andLessEquals(String field, Object value);

    /**
     * 小于等于最终值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 最终值
     * @return 表达式
     */
    public GraphCondition andLessEqualsWithFinallyValue(Class clazz, String field, Object value);

    /**
     * 小于等于源值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andLessEqualsWithOriginalValue(Class clazz, String field, Object value);

    /**
     * 最真实的小于
     *
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andLess(String field, Object value);

    /**
     * 小于源值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andLessWithOriginalValue(Class clazz, String field, Object value);


    /**
     * 小于最终值
     *
     * @param clazz 类类型
     * @param field 字段
     * @param value 值
     * @return 表达式
     */
    public GraphCondition andLessWithFinallyValue(Class clazz, String field, Object value);


}
