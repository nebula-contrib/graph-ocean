/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.domain.impl;

import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.data.ValueWrapper;
import io.github.anyzm.graph.ocean.annotation.GraphProperty;
import io.github.anyzm.graph.ocean.common.utils.FieldUtils;
import io.github.anyzm.graph.ocean.domain.GraphLabel;
import io.github.anyzm.graph.ocean.enums.GraphDataTypeEnum;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Anyzm
 * @version 1.0.0
 * description QueryResult is used for
 * date 2020/3/27 - 10:13
 * @update chenrui
 * @date 2020/08/30
 */
@ToString
public class QueryResult implements Iterable<ResultSet.Record>, Serializable {

    @Getter
    private List<ResultSet.Record> data = new ArrayList<>();

    public QueryResult() {
    }

    public QueryResult(List<ResultSet.Record> data) {
        this.data = data;
    }

    /**
     * 将查询结果合并
     *
     * @param queryResult
     * @return
     */
    public QueryResult mergeQueryResult(QueryResult queryResult) {
        if (queryResult == null || queryResult.isEmpty()) {
            return this;
        }
        if (this.isEmpty()) {
            this.data = queryResult.getData();
        } else {
            this.data.addAll(queryResult.getData());
        }
        return this;
    }

    public <T> List<T> getEntities(GraphLabel graphLabel, Class<T> clazz) throws IllegalAccessException, InstantiationException, UnsupportedEncodingException {
        if (this.data == null || this.data.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List<T> list = new ArrayList<>(this.data.size());
        for (ResultSet.Record record : this.data) {
            list.add(parseResult(record, graphLabel, clazz));
        }
        return list;
    }

    public int size() {
        return this.data.size();
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean isNotEmpty() {
        return this.size() != 0;
    }

    @Override
    public Iterator<ResultSet.Record> iterator() {
        return this.data.iterator();
    }

    public Stream<ResultSet.Record> stream() {
        Iterable<ResultSet.Record> iterable = this::iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }

    private <T> void dealFieldReformat(GraphLabel graphLabel, String key, Field field, T obj, Object databaseValue) throws IllegalAccessException {
        Object value = graphLabel != null ? graphLabel.reformatValue(key, databaseValue) : databaseValue;
        field.set(obj, value);
    }

    //解析nebula结果成java bean格式
    private <T> T parseResult(ResultSet.Record record, GraphLabel graphLabel, Class<T> clazz) throws IllegalAccessException, InstantiationException, UnsupportedEncodingException {
        T obj = clazz.newInstance();
        List<Field> fieldsList = FieldUtils.listFields(clazz);
        for (Field field : fieldsList) {
            GraphProperty annotation = field.getAnnotation(GraphProperty.class);
            String key = annotation != null ? annotation.value() : field.getName();
            if (record.contains(key)) {
                ValueWrapper valueWrapper = record.get(key);
                if (!valueWrapper.isNull()) {
                    field.setAccessible(true);
                    if (annotation != null && !GraphDataTypeEnum.NULL.equals(annotation.dataType())) {
                        switch (annotation.dataType()) {
                            case INT:
                                dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asLong());
                                break;
                            case STRING:
                                dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asString());
                                break;
                            case DATE:
                                dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asDate());
                                break;
                            case DATE_TIME:
                                dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asDateTime());
                                break;
                            case BOOLEAN:
                                dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asBoolean());
                                break;
                            case TIMESTAMP:
                                dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asTime());
                                break;
                            case DOUBLE:
                                dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asDouble());
                                break;
                            default:
                        }
                        continue;
                    }
                    if (valueWrapper.isLong()) {
                        dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asLong());
                    } else if (valueWrapper.isBoolean()) {
                        dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asBoolean());
                    } else if (valueWrapper.isDouble()) {
                        dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asDouble());
                    } else if (valueWrapper.isDate()) {
                        dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asDouble());
                    } else if (valueWrapper.isDateTime()) {
                        dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asDateTime());
                    } else if (valueWrapper.isTime()) {
                        dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asTime());
                    } else if (valueWrapper.isString()) {
                        dealFieldReformat(graphLabel, key, field, obj, valueWrapper.asString());
                    }
                }
            }
        }
        return obj;
    }

}
