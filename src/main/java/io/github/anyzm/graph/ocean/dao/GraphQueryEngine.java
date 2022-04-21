/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao;

import io.github.anyzm.graph.ocean.domain.GraphLabel;

/**
 * Description  GraphQueryEngine is used for
 *
 * @author Anyzm
 * Date  2021/8/10 - 11:09
 * @version 1.0.0
 */
public interface GraphQueryEngine extends GraphEngine {

    /**
     *
     * @return 是否包含多标签操作
     */
    public boolean containsMultiLabel();

    /**
     *
     * @return 获取查询的图标签
     */
    public GraphLabel getGraphLabel();

}
