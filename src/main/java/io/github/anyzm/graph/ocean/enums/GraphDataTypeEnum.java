/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.enums;

/**
 * Description  GraphDataTypeEnum is used for
 * 图数据库数据类型
 *
 * @author Anyzm
 * Date  2021/7/19 - 17:13
 * @version 1.0.0
 * @update chenui
 * @date 2022/08/31
 */
public enum GraphDataTypeEnum {
    /**
     * 字符串
     */
    STRING,
    /**
     * 时间戳
     */
    TIMESTAMP,
    /**
     * 整型
     */
    INT,
    /**
     * 浮点型
     */
    DOUBLE,
    /**
     * 日期
     */
    DATE,

    DATE_TIME,
    //布尔值
    BOOLEAN
    ;

}
