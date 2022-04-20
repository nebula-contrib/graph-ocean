/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao.impl;

import io.github.anyzm.graph.ocean.annotation.GraphProperty;
import io.github.anyzm.graph.ocean.common.GraphHelper;
import io.github.anyzm.graph.ocean.dao.GraphTypeManager;
import io.github.anyzm.graph.ocean.dao.GraphVertexEntityFactory;
import io.github.anyzm.graph.ocean.domain.impl.GraphVertexEntity;
import io.github.anyzm.graph.ocean.domain.impl.GraphVertexType;
import io.github.anyzm.graph.ocean.enums.ErrorEnum;
import io.github.anyzm.graph.ocean.enums.GraphPropertyTypeEnum;
import io.github.anyzm.graph.ocean.exception.CheckThrower;
import io.github.anyzm.graph.ocean.exception.NebulaException;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
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

    private <T> String collectVertexEntityProperties(T input, Field[] declaredFields, GraphVertexType<T> graphVertexType,
                                                     Map<String, Object> propertyMap) {
        String id = null;
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
        return id;
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
        Map<String, Object> propertyMap = Maps.newHashMapWithExpectedSize(declaredFields.length);
        String tempId = collectVertexEntityProperties(input, declaredFields, graphVertexType, propertyMap);
        id = StringUtils.isNotBlank(tempId) ? tempId : id;
        Class<? super T> superclass = inputClass.getSuperclass();
        while (superclass != Object.class) {
            declaredFields = superclass.getDeclaredFields();
            tempId = collectVertexEntityProperties(input, declaredFields, graphVertexType, propertyMap);
            id = StringUtils.isNotBlank(tempId) ? tempId : id;
            superclass = superclass.getSuperclass();
        }
        CheckThrower.ifTrueThrow(StringUtils.isBlank(id), ErrorEnum.INVALID_ID);
        return new GraphVertexEntity<>(graphVertexType, id, propertyMap);
    }
}
