/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao;

/**
 * 业务说明：
 *
 * @author Anyzm
 * date 2021/7/1
 **/
public interface GraphValueFormatter {

    /**
     *
     * @param oldValue 旧值
     * @return 格式化对象
     */
    public Object format(Object oldValue);


    /**
     * nebula属性值反转为javaBean值
     *
     * @param nebulaValue 数据库的值
     * @return 反转的值
     */
    public default Object reformat(Object nebulaValue) {
        return nebulaValue;
    }

}
