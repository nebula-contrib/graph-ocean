/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package com.github.anyzm.graph.ocean.dao;

import com.github.anyzm.graph.ocean.domain.impl.GraphEdgeEntity;
import com.github.anyzm.graph.ocean.domain.impl.GraphEdgeType;

import java.util.List;

/**
 * Description  EdgeUpdateEngine is used for
 * 目前边的更新只适合单类型的边，类似mysql的单表操作
 *
 * @author Anyzm
 * Date  2021/7/16 - 17:08
 * @version 1.0.0
 */
public interface EdgeUpdateEngine<S, T, E> extends GraphUpdateEngine {

    /**
     * 获取边实体
     *
     * @return
     */
    public List<GraphEdgeEntity<S, T, E>> getGraphEdgeEntityList();

    /**
     * 获取边类型
     *
     * @return
     */
    public GraphEdgeType<S, T, E> getGraphEdgeType();


}
