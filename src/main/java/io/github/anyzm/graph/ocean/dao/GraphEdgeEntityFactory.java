/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao;

import io.github.anyzm.graph.ocean.domain.impl.GraphEdgeEntity;
import io.github.anyzm.graph.ocean.exception.NebulaException;

/**
 * @author Anyzm
 * description:
 * date: Created in 20:00 2021/7/18
 */
public interface GraphEdgeEntityFactory {

    /**
     *
     * @param input 边对象
     * @param <S> 起点
     * @param <T> 终点
     * @param <E> 边
     * @return GraphEdgeEntity
     * @throws NebulaException nebula异常
     */
    public <S, T, E> GraphEdgeEntity<S, T, E> buildGraphEdgeEntity(E input) throws NebulaException;

}
