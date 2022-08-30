package io.github.anyzm.graph.ocean.common.utils;

import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.data.ValueWrapper;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 解析nebula结果
 * @author chenrui
 * @date 2022-08-30
 */
public class NebulaUtils {

    public static <T> List<T> resultToObj(ResultSet resultSet, Class<T> clazz) {
        return IntStream.range(0,resultSet.rowsSize()).mapToObj(i->{
            ResultSet.Record value = resultSet.rowValues(i);
            return parseResult(value,clazz);
        }).collect(Collectors.toList());
    }

    //解析nebula结果成java bean格式
    public static <T> T parseResult(ResultSet.Record record, Class<T> clazz) {
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields) {
            String key = field.getName();
            if(record.contains(key)) {
                ValueWrapper valueWrapper = record.get(key);
                if(!valueWrapper.isNull()) {
                    field.setAccessible(true);
                    try {
                        if(valueWrapper.isLong()) {
                            Long l = valueWrapper.asLong();
                            if(Long.class.equals(field.getType())) {
                                field.set(obj, l);
                            }
                        } else if(valueWrapper.isDouble()) {
                            double d = valueWrapper.asDouble();
                            if(Double.class.equals(field.getType())) {
                                field.set(obj,d);
                            }
                        } else if(valueWrapper.isBoolean()) {
                            boolean b = valueWrapper.asBoolean();
                            if(Boolean.class.equals(field.getType())) {
                                field.set(obj,b);
                            }
                        } else {
                            String s = valueWrapper.asString();
                            Object v;
                            if(Integer.class.equals(field.getType())) {
                                v = Integer.parseInt(s);
                            }else if(Long.class.equals(field.getType())) {
                                v = Long.parseLong(s);
                            }else if(String.class.equals(field.getType())) {
                                v = s;
                            }else if(Date.class.equals(field.getType())) {
                                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                v = s.contains("-")?simpleDateFormat.parse(s):new Date(Long.parseLong(s));
                            }else {
                                continue;
                            }
                            field.set(obj, v);
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
