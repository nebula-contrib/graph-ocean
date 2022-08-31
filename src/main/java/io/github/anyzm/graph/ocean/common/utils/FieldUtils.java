package io.github.anyzm.graph.ocean.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author chenrui
 * @date 2022/08/31
 */
public class FieldUtils {
    //列出所属类的所有属性
    public static List<Field> listFields(Class clazz) {
        List<Field> fieldsList = new ArrayList<>();
        Class<?> c = clazz;
        while (!Object.class.equals(c)) {  // 遍历所有父类字节码对象
            Field[] declaredFields = clazz.getDeclaredFields();
            fieldsList.addAll(Arrays.asList(declaredFields));
            c = c.getSuperclass();
        }
        return fieldsList;
    }
}
