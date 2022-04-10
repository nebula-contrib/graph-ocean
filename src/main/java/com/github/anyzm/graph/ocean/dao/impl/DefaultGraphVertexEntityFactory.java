/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package com.github.anyzm.graph.ocean.dao.impl;

import com.github.anyzm.graph.ocean.annotation.GraphProperty;
import com.github.anyzm.graph.ocean.common.GraphHelper;
import com.github.anyzm.graph.ocean.dao.GraphTypeManager;
import com.github.anyzm.graph.ocean.dao.GraphVertexEntityFactory;
import com.github.anyzm.graph.ocean.domain.impl.GraphVertexEntity;
import com.github.anyzm.graph.ocean.domain.impl.GraphVertexType;
import com.github.anyzm.graph.ocean.enums.ErrorEnum;
import com.github.anyzm.graph.ocean.enums.GraphPropertyTypeEnum;
import com.github.anyzm.graph.ocean.exception.CheckThrower;
import com.github.anyzm.graph.ocean.exception.NebulaException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Anyzm
 * created by ZhaoLai Huang on 2021/7/18
 */
@Slf4j
public class DefaultGraphVertexEntityFactory implements GraphVertexEntityFactory {

    private GraphTypeManager graphTypeManager;

    public DefaultGraphVertexEntityFactory(GraphTypeManager graphTypeManager) {
        this.graphTypeManager = graphTypeManager;
    }

    public DefaultGraphVertexEntityFactory() {
        this.graphTypeManager = new DefaultGraphTypeManager();
    }

    @Override
    public <T> GraphVertexEntity<T> buildGraphVertexEntity(T input) throws NebulaException {
        if (input == null) {
            return null;
        }
        Class<T> inputClass = (Class<T>) input.getClass();
        GraphVertexType<T> graphVertexType = graphTypeManager.getGraphVertexType(inputClass);
        if (graphVertexType == null) {
            return null;
        }
        Field[] declaredFields = inputClass.getDeclaredFields();
        String id = null;
        Map<String, Object> propertyMap = new HashMap<>();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            GraphProperty graphProperty = declaredField.getAnnotation(GraphProperty.class);
            if (graphProperty == null) {
                continue;
            }
            Object value = GraphHelper.formatFieldValue(declaredField, graphProperty, input, graphVertexType);
            if (graphProperty.propertyTypeEnum().equals(GraphPropertyTypeEnum.GRAPH_VERTEX_ID)) {
                id = (String) value;
                if (!graphVertexType.isIdAsField()) {
                    continue;
                }
            }
            if (value != null) {
                propertyMap.put(graphProperty.value(), value);
            }
        }
        CheckThrower.ifTrueThrow(StringUtils.isBlank(id), ErrorEnum.INVALID_ID);
        return new GraphVertexEntity<>(graphVertexType, id, propertyMap);
    }
}
