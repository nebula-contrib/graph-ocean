/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.annotation;

import java.lang.annotation.*;

/**
 * 业务说明：标注边类型
 *
 * @author Anyzm
 * date 2021/4/28
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GraphEdge {

    /**
     *
     * @return 边名称
     */
    String value();

    /**
     *
     * @return 边起点类
     */
    Class srcVertex();

    /**
     *
     * @return 边终点类
     */
    Class dstVertex();

    /**
     *
     * @return 起点id是否作为字段
     */
    boolean srcIdAsField() default true;

    /**
     *
     * @return 末尾id是否作为字段
     */
    boolean dstIdAsField() default true;

}
