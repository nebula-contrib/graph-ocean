/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.annotation;

import io.github.anyzm.graph.ocean.dao.GraphValueFormatter;
import io.github.anyzm.graph.ocean.enums.GraphDataTypeEnum;
import io.github.anyzm.graph.ocean.enums.GraphPropertyTypeEnum;

import java.lang.annotation.*;

/**
 * 业务说明：图的属性
 *
 * @author Anyzm
 * date 2021/4/28
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface GraphProperty {

    /**
     *
     * @return 属性名称
     */
    String value();

    /**
     *
     * @return 数据类型
     */
    GraphDataTypeEnum dataType() default GraphDataTypeEnum.NULL;

    /**
     *
     * @return 是否必需
     */
    boolean required() default false;

    /**
     *
     * @return 属性类型
     */
    GraphPropertyTypeEnum propertyTypeEnum() default GraphPropertyTypeEnum.ORDINARY_PROPERTY;

    /**
     *
     * @return 属性格式化
     */
    Class<? extends GraphValueFormatter> formatter() default GraphValueFormatter.class;

}
