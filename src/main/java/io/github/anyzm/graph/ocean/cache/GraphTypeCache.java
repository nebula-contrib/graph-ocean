/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.cache;


import io.github.anyzm.graph.ocean.domain.impl.GraphEdgeType;
import io.github.anyzm.graph.ocean.domain.impl.GraphVertexType;

/**
 * Description  GraphTypeCache is used for
 *
 * @author Anyzm
 * 图类型缓存，可自行扩展
 * Date  2021/7/16 - 19:41
 * @version 1.0.0
 */
public interface GraphTypeCache {

    /**
     *
     * @param clazz 类类型
     * @param <T> 顶点
     * @return 顶点类型
     */
    public <T> GraphVertexType<T> getGraphVertexType(Class<T> clazz);


    /**
     *
     * @param clazz 类类型
     * @param graphVertexType 顶点type
     * @param <T> 顶点
     */
    public <T> void putGraphVertexType(Class<T> clazz, GraphVertexType<T> graphVertexType);


    /**
     *
     * @param clazz 类类型
     * @return 顶点类型
     */
    public GraphEdgeType getGraphEdgeType(Class clazz);

    /**
     *
     * @param clazz 类类型
     * @param graphEdgeType 边类型
     */
    public void putGraphEdgeType(Class clazz, GraphEdgeType graphEdgeType);

}
