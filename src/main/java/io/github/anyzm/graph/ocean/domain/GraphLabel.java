/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.domain;

import io.github.anyzm.graph.ocean.enums.GraphDataTypeEnum;

import java.util.Collection;
import java.util.List;

/**
 * Description  GraphLabel is used for
 *
 * @author Anyzm
 * Date  2021/8/10 - 11:18
 * @version 1.0.0
 */
public interface GraphLabel {

    /**
     * 是否Tag
     *
     * @return 是否顶点tag
     */
    public boolean isTag();

    /**
     * 是否关系(边)
     *
     * @return 是否边
     */
    public boolean isEdge();

    /**
     * 获取标签名称
     *
     * @return 标签名
     */
    public String getName();

    /**
     * 获取必要字段
     *
     * @return 必须字段
     */
    public List<String> getMustFields();

    /**
     * 获取所有字段
     *
     * @return 所有字段名
     */
    public Collection<String> getAllFields();

    /**
     * 格式化属性值
     *
     * @param field 字段
     * @param originalValue 原始值
     * @return 格式化后的值
     */
    public Object formatValue(String field, Object originalValue);

    /**
     * 反转格式化属性值
     *
     * @param field 字段
     * @param databaseValue 数据库的值
     * @return 反格式化后的值
     */
    public Object reformatValue(String field, Object databaseValue);

    /**
     * 获取字段名
     *
     * @param property 属性
     * @return 字段名
     */
    public String getFieldName(String property);

    /**
     * 获取属性名
     *
     * @param field  字段
     * @return 属性名
     */
    public String getPropertyName(String field);

    /**
     * 获取字段的数据类型
     *
     * @param field 字段
     * @return 数据类型
     */
    public GraphDataTypeEnum getFieldDataType(String field);

}
