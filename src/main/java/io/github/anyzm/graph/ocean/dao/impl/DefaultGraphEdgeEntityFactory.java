/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao.impl;

import io.github.anyzm.graph.ocean.annotation.GraphProperty;
import io.github.anyzm.graph.ocean.common.GraphHelper;
import io.github.anyzm.graph.ocean.dao.GraphEdgeEntityFactory;
import io.github.anyzm.graph.ocean.dao.GraphTypeManager;
import io.github.anyzm.graph.ocean.domain.impl.GraphEdgeEntity;
import io.github.anyzm.graph.ocean.domain.impl.GraphEdgeType;
import io.github.anyzm.graph.ocean.domain.impl.GraphVertexType;
import io.github.anyzm.graph.ocean.enums.ErrorEnum;
import io.github.anyzm.graph.ocean.enums.GraphPropertyTypeEnum;
import io.github.anyzm.graph.ocean.exception.CheckThrower;
import io.github.anyzm.graph.ocean.exception.NebulaException;
import com.google.common.collect.Maps;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author Anyzm
 * created by ZhaoLai Huang on 2021/7/18
 */
@Slf4j
public class DefaultGraphEdgeEntityFactory implements GraphEdgeEntityFactory {

    private GraphTypeManager graphTypeManager;

    public DefaultGraphEdgeEntityFactory() {
        this.graphTypeManager = new DefaultGraphTypeManager();
    }

    public DefaultGraphEdgeEntityFactory(GraphTypeManager graphTypeManager) {
        this.graphTypeManager = graphTypeManager;
    }

    private <S, T, E> Pair<String, String> collectEdgeEntityProperties(E input, Field[] declaredFields,
                                                                       GraphEdgeType<S, T, E> graphEdgeType,
                                                                       Map<String, Object> propertyMap) {
        String srcId = null;
        String dstId = null;
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            GraphProperty graphProperty = declaredField.getAnnotation(GraphProperty.class);
            if (graphProperty == null) {
                continue;
            }
            Object value = GraphHelper.formatFieldValue(declaredField, graphProperty, input, graphEdgeType);
            if (graphProperty.propertyTypeEnum().equals(GraphPropertyTypeEnum.GRAPH_EDGE_SRC_ID)) {
                srcId = (String) value;
                if (!graphEdgeType.isSrcIdAsField()) {
                    continue;
                }
            }
            if (graphProperty.propertyTypeEnum().equals(GraphPropertyTypeEnum.GRAPH_EDGE_DST_ID)) {
                dstId = (String) value;
                if (!graphEdgeType.isDstIdAsField()) {
                    continue;
                }
            }
            if (value != null) {
                propertyMap.put(graphProperty.value(), value);
            }
        }
        return new Pair<String, String>(srcId, dstId);
    }

    @Override
    public <S, T, E> GraphEdgeEntity<S, T, E> buildGraphEdgeEntity(E input) throws NebulaException {
        if (input == null) {
            return null;
        }
        Class<E> inputClass = (Class<E>) input.getClass();
        GraphEdgeType<S, T, E> graphEdgeType = graphTypeManager.getGraphEdgeType(inputClass);
        if (graphEdgeType == null) {
            return null;
        }
        //起点类型
        GraphVertexType<?> srcVertexType = graphEdgeType.getSrcVertexType();
        //终点类型
        GraphVertexType<?> dstVertexType = graphEdgeType.getDstVertexType();
        Field[] declaredFields = inputClass.getDeclaredFields();
        String srcId = null;
        String dstId = null;
        //所有属性与值
        Map<String, Object> propertyMap = Maps.newHashMapWithExpectedSize(declaredFields.length);
        Pair<String, String> idPair = collectEdgeEntityProperties(input, declaredFields, graphEdgeType, propertyMap);
        srcId = StringUtils.isNotBlank(idPair.getKey()) ? idPair.getKey() : srcId;
        dstId = StringUtils.isNotBlank(idPair.getValue()) ? idPair.getValue() : dstId;
        Class<? super E> superclass = inputClass.getSuperclass();
        while (superclass != Object.class) {
            declaredFields = superclass.getDeclaredFields();
            idPair = collectEdgeEntityProperties(input, declaredFields, graphEdgeType, propertyMap);
            srcId = StringUtils.isNotBlank(idPair.getKey()) ? idPair.getKey() : srcId;
            dstId = StringUtils.isNotBlank(idPair.getValue()) ? idPair.getValue() : dstId;
            superclass = superclass.getSuperclass();
        }
        CheckThrower.ifTrueThrow(StringUtils.isBlank(srcId) || StringUtils.isBlank(dstId),
                ErrorEnum.INVALID_ID);
        return new GraphEdgeEntity(graphEdgeType, srcId, dstId, srcVertexType, dstVertexType, propertyMap);
    }
}
