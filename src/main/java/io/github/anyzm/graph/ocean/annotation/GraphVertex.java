/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.annotation;


import io.github.anyzm.graph.ocean.enums.GraphKeyPolicy;

import java.lang.annotation.*;

/**
 * 业务说明：标注顶点类型
 *
 * @author Anyzm
 * @date 2021/4/28
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GraphVertex {

    /**
     * 顶点名称
     *
     * @return
     */
    String value();

    /**
     * 主键生成方法
     *
     * @return
     */
    GraphKeyPolicy keyPolicy();

    /**
     * 顶点id是否作为属性
     *
     * @return
     */
    boolean idAsField() default true;

}
