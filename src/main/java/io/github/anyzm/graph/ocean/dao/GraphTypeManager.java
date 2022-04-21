/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao;

import io.github.anyzm.graph.ocean.domain.GraphLabel;
import io.github.anyzm.graph.ocean.domain.impl.GraphEdgeType;
import io.github.anyzm.graph.ocean.domain.impl.GraphVertexType;
import io.github.anyzm.graph.ocean.exception.NebulaException;

/**
 * Description  GraphTypeManager is used for
 *
 * @author Anyzm
 * Date  2021/7/16 - 18:06
 * @version 1.0.0
 */
public interface GraphTypeManager {
    /**
     *
     * @param clazz 类类型
     * @param <T> 顶点
     * @return 顶点类型
     * @throws NebulaException 构造异常
     */
    public <T> GraphVertexType<T> getGraphVertexType(Class<T> clazz) throws NebulaException;


    /**
     *
     * @param clazz 类类型
     * @return 顶点类型
     * @throws NebulaException 构造异常
     */
    public <S, T, E> GraphEdgeType<S, T, E> getGraphEdgeType(Class<E> clazz) throws NebulaException;

    /**
     * @param clazz 类类型
     * @return 图标签
     * @throws NebulaException nebula异常
     */
    public GraphLabel getGraphLabel(Class clazz) throws NebulaException;

}
