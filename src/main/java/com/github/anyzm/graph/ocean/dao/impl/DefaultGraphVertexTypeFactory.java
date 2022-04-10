/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package com.github.anyzm.graph.ocean.dao.impl;


import com.github.anyzm.graph.ocean.annotation.GraphVertex;
import com.github.anyzm.graph.ocean.common.GraphHelper;
import com.github.anyzm.graph.ocean.dao.GraphVertexTypeFactory;
import com.github.anyzm.graph.ocean.domain.impl.GraphVertexType;
import com.github.anyzm.graph.ocean.domain.impl.GraphVertexTypeBuilder;
import com.github.anyzm.graph.ocean.enums.GraphKeyPolicy;
import com.github.anyzm.graph.ocean.exception.NebulaException;

/**
 * Description  NebulaGraphVertexTypeFactory is used for
 *
 * @author Anyzm
 * Date  2021/7/16 - 16:41
 * @version 1.0.0
 */
public class DefaultGraphVertexTypeFactory implements GraphVertexTypeFactory {

    @Override
    public <T> GraphVertexType<T> buildGraphVertexType(Class<T> clazz) throws NebulaException {
        GraphVertex graphVertex = clazz.getAnnotation(GraphVertex.class);
        if (graphVertex == null) {
            return null;
        }
        String vertexName = graphVertex.value();
        //主键策略：hash uuid string
        GraphKeyPolicy graphKeyPolicy = graphVertex.keyPolicy();
        boolean idAsField = graphVertex.idAsField();
        GraphVertexTypeBuilder builder = GraphVertexTypeBuilder.builder();
        GraphHelper.collectGraphProperties(builder, clazz, idAsField, idAsField);
        return builder.graphKeyPolicy(graphKeyPolicy).idAsField(idAsField).graphLabelName(vertexName).labelClass(clazz).build();
    }

}
