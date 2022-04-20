/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao;


import io.github.anyzm.graph.ocean.domain.impl.GraphEdgeEntity;
import io.github.anyzm.graph.ocean.domain.impl.GraphVertexEntity;
import io.github.anyzm.graph.ocean.exception.NebulaException;

import java.util.List;

/**
 * Description  GraphUpdateEdgeEngineFactory is used for
 *
 * @author Anyzm
 * Date  2021/7/19 - 10:27
 * @version 1.0.0
 */
public interface GraphUpdateEdgeEngineFactory {

    /**
     * 构造图边更新引擎
     *
     * @param graphEdgeEntities
     * @param <S>
     * @param <E>
     * @return
     * @throws NebulaException
     */
    public <S, T, E> EdgeUpdateEngine<S, T, E> build(List<GraphEdgeEntity<S, T, E>> graphEdgeEntities) throws NebulaException;


    /**
     * 构造图边更新引擎
     *
     * @param graphEdgeEntities
     * @param srcGraphVertexEntities
     * @param dstGraphVertexEntities
     * @param <S>
     * @param <E>
     * @return
     * @throws NebulaException
     */
    public <S, T, E> EdgeUpdateEngine<S, T, E> build(List<GraphEdgeEntity<S, T, E>> graphEdgeEntities,
                                                     List<GraphVertexEntity<S>> srcGraphVertexEntities,
                                                     List<GraphVertexEntity<T>> dstGraphVertexEntities) throws NebulaException;

}
