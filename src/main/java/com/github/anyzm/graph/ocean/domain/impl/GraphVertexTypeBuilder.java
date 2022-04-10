/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package com.github.anyzm.graph.ocean.domain.impl;

import com.github.anyzm.graph.ocean.dao.GraphValueFormatter;
import com.github.anyzm.graph.ocean.domain.GraphLabelBuilder;
import com.github.anyzm.graph.ocean.enums.GraphDataTypeEnum;
import com.github.anyzm.graph.ocean.enums.GraphKeyPolicy;

import java.util.List;
import java.util.Map;

/**
 * Description  GraphVertexTypeBuilder is used for
 *
 * @author Anyzm
 * Date  2021/9/13 - 16:15
 * @version 1.0.0
 */
public class GraphVertexTypeBuilder implements GraphLabelBuilder {

    private GraphVertexType graphVertexType;

    private GraphVertexTypeBuilder() {
        this.graphVertexType = new GraphVertexType();
    }


    public static GraphVertexTypeBuilder builder() {
        return new GraphVertexTypeBuilder();
    }

    @Override
    public GraphVertexTypeBuilder graphLabelName(String graphLabelName) {
        this.graphVertexType.setVertexName(graphLabelName);
        return this;
    }

    @Override
    public GraphVertexTypeBuilder labelClass(Class labelClass) {
        this.graphVertexType.setTypeClass(labelClass);
        return this;
    }

    @Override
    public GraphVertexTypeBuilder propertyFormatMap(Map<String, GraphValueFormatter> propertyFormatMap) {
        this.graphVertexType.setPropertyFormatMap(propertyFormatMap);
        return this;
    }

    @Override
    public GraphVertexTypeBuilder dataTypeMap(Map<String, GraphDataTypeEnum> dataTypeMap) {
        this.graphVertexType.setDataTypeMap(dataTypeMap);
        return this;
    }

    @Override
    public GraphVertexTypeBuilder mustProps(List<String> mustProps) {
        this.graphVertexType.setMustFields(mustProps);
        return this;
    }

    @Override
    public GraphVertexTypeBuilder propertyFieldMap(Map<String, String> propertyFieldMap) {
        this.graphVertexType.setPropertyFieldMap(propertyFieldMap);
        return this;
    }

    @Override
    public GraphVertexTypeBuilder graphKeyPolicy(GraphKeyPolicy graphKeyPolicy) {
        this.graphVertexType.setGraphKeyPolicy(graphKeyPolicy);
        return this;
    }

    @Override
    public GraphVertexTypeBuilder idAsField(boolean idAsField) {
        this.graphVertexType.setIdAsField(idAsField);
        return this;
    }

    @Override
    public GraphVertexTypeBuilder idValueFormatter(GraphValueFormatter idValueFormatter) {
        this.graphVertexType.setIdValueFormatter(idValueFormatter);
        return this;
    }

    @Override
    public GraphVertexTypeBuilder srcIdAsField(boolean srcIdAsField) {
        return this;
    }

    @Override
    public GraphVertexTypeBuilder dstIdAsField(boolean dstIdAsField) {
        return this;
    }

    @Override
    public GraphVertexTypeBuilder srcIdValueFormatter(GraphValueFormatter srcIdValueFormatter) {
        return this;
    }

    @Override
    public GraphVertexTypeBuilder dstIdValueFormatter(GraphValueFormatter dstIdValueFormatter) {
        return this;
    }

    @Override
    public GraphVertexTypeBuilder srcGraphVertexType(GraphVertexType srcGraphVertexType) {
        return this;
    }

    @Override
    public GraphVertexTypeBuilder dstGraphVertexType(GraphVertexType dstGraphVertexType) {
        return this;
    }

    @Override
    public GraphVertexType build() {
        return this.graphVertexType;
    }
}
