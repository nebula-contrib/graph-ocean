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
     * 根据类类型获取顶点类型
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> GraphVertexType<T> getGraphVertexType(Class<T> clazz);


    /**
     * 保存图顶点type缓存
     *
     * @param clazz
     * @param graphVertexType
     * @param <T>
     */
    public <T> void putGraphVertexType(Class<T> clazz, GraphVertexType<T> graphVertexType);


    /**
     * 根据类类型获取顶点类型
     *
     * @param clazz
     * @return
     */
    public GraphEdgeType getGraphEdgeType(Class clazz);

    /**
     * 保存图边类型的缓存
     *
     * @param clazz
     * @param graphEdgeType
     */
    public void putGraphEdgeType(Class clazz, GraphEdgeType graphEdgeType);

}
