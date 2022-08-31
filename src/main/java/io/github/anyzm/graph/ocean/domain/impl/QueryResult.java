/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.domain.impl;

import com.vesoft.nebula.client.graph.data.*;
import lombok.Getter;
import lombok.ToString;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
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

    public <T> List<T> getEntities(Class<T> clazz) {
        if(this.data==null||this.data.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return this.data.stream().map(record -> parseResult(record,clazz)).collect(Collectors.toList());
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

    //解析nebula结果成java bean格式
    private <T> T parseResult(ResultSet.Record record, Class<T> clazz) {
        T obj;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        List<Field> fieldsList = new ArrayList<>();
        Class<?> c = clazz;
        while (!Object.class.equals(c)) {  // 遍历所有父类字节码对象
            Field[] declaredFields = clazz.getDeclaredFields();
            fieldsList.addAll(Arrays.asList(declaredFields));
            c = c.getSuperclass();
        }
        for(Field field : fieldsList) {
            String key = field.getName();
            if(record.contains(key)) {
                ValueWrapper valueWrapper = record.get(key);
                if(!valueWrapper.isNull()) {
                    field.setAccessible(true);
                    try {
                        if(valueWrapper.isLong()&&Long.class.equals(field.getType())) {
                            field.set(obj,valueWrapper.asLong());
                        } else if(valueWrapper.isBoolean()&&Boolean.class.equals(field.getType())) {
                            field.set(obj,valueWrapper.asBoolean());
                        } else if(valueWrapper.isDouble()&&Double.class.equals(field.getType())) {
                            field.set(obj,valueWrapper.asDouble());
                        } else if(valueWrapper.isDate()&& DateWrapper.class.equals(field.getType())) {
                            field.set(obj,valueWrapper.asDate());
                        } else if(valueWrapper.isDateTime()&& DateTimeWrapper.class.equals(field.getType())) {
                            field.set(obj,valueWrapper.asDateTime());
                        } else if(valueWrapper.isTime()&& TimeWrapper.class.equals(field.getType())) {
                            field.set(obj,valueWrapper.asTime());
                        } else if(valueWrapper.isString()&&String.class.equals(field.getType())) {
                            field.set(obj,valueWrapper.asString());
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return obj;
    }

}
