/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao.impl;

import io.github.anyzm.graph.ocean.annotation.GraphEdge;
import io.github.anyzm.graph.ocean.common.GraphHelper;
import io.github.anyzm.graph.ocean.dao.GraphEdgeTypeFactory;
import io.github.anyzm.graph.ocean.dao.GraphVertexTypeFactory;
import io.github.anyzm.graph.ocean.domain.impl.GraphEdgeType;
import io.github.anyzm.graph.ocean.domain.impl.GraphEdgeTypeBuilder;
import io.github.anyzm.graph.ocean.domain.impl.GraphVertexType;
import io.github.anyzm.graph.ocean.enums.ErrorEnum;
import io.github.anyzm.graph.ocean.enums.GraphDataTypeEnum;
import io.github.anyzm.graph.ocean.exception.CheckThrower;
import io.github.anyzm.graph.ocean.exception.NebulaException;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Description  DefaultGraphEdgeTypeFactory is used for
 * 默认的图边类型工厂类
 *
 * @author Anyzm
 * Date  2021/7/16 - 16:58
 * @version 1.0.0
 */
public class DefaultGraphEdgeTypeFactory implements GraphEdgeTypeFactory {

    private GraphVertexTypeFactory graphVertexTypeFactory;

    public DefaultGraphEdgeTypeFactory() {
        this.graphVertexTypeFactory = new DefaultGraphVertexTypeFactory();
    }

    @Override
    public <S, T, E> GraphEdgeType<S, T, E> buildGraphEdgeType(Class<E> clazz) throws NebulaException {
        return buildGraphEdgeType(clazz, null, null);
    }

    @Override
    public <S, T, E> GraphEdgeType<S, T, E> buildGraphEdgeType(Class<E> clazz, GraphVertexType<S> srcGraphVertexType,
                                                               GraphVertexType<T> dstGraphVertexType) throws NebulaException {
        GraphEdge graphEdge = (GraphEdge) clazz.getAnnotation(GraphEdge.class);
        CheckThrower.ifTrueThrow(graphEdge == null, ErrorEnum.PARAMETER_NOT_NULL);
        String edgeName = graphEdge.value();
        boolean srcIdAsField = graphEdge.srcIdAsField();
        boolean dstIdAsField = graphEdge.dstIdAsField();
        //字段类型
        Map<String, GraphDataTypeEnum> dataTypeMap = Maps.newHashMap();
        if (srcGraphVertexType == null) {
            Class<S> srcVertex = graphEdge.srcVertex();
            srcGraphVertexType = graphVertexTypeFactory.buildGraphVertexType(srcVertex);
        }
        if (dstGraphVertexType == null) {
            Class<T> dstVertex = graphEdge.dstVertex();
            dstGraphVertexType = graphVertexTypeFactory.buildGraphVertexType(dstVertex);
        }
        CheckThrower.ifTrueThrow(srcGraphVertexType == null || dstGraphVertexType == null, ErrorEnum.INVALID_VERTEX_TAG);
        GraphEdgeTypeBuilder builder = GraphEdgeTypeBuilder.builder();
        GraphHelper.collectGraphProperties(builder, clazz, srcIdAsField, dstIdAsField);
        return builder.srcIdAsField(srcIdAsField).dstIdAsField(dstIdAsField).graphLabelName(edgeName)
                .labelClass(clazz).srcGraphVertexType(srcGraphVertexType).dstGraphVertexType(dstGraphVertexType).build();
    }
}
