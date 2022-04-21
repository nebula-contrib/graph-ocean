/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.domain;

import io.github.anyzm.graph.ocean.domain.impl.GraphEdgeEntity;
import io.github.anyzm.graph.ocean.domain.impl.GraphVertexType;

import java.util.List;

/**
 * @author Anyzm
 * date 2020/5/1
 */
public interface GraphRelation<S, T, E> {
    /**
     * 获取 srcId
     *
     * @return 起点id
     */
    String getSrcId();

    /**
     * 获取 endId
     *
     * @return 终点id
     */
    String getDstId();

    /**
     * 获取 VertexType
     *
     * @return 起点顶点类型
     */
    GraphVertexType<S> getSrcVertexType();

    /**
     * 获取 VertexType
     *
     * @return 终点顶点类型
     */
    GraphVertexType<E> getDstVertexType();

    /**
     * 获取关联的顶点
     *
     * @return
     */
    List<GraphVertexType> getVertices();

    /**
     * 获取关联的边
     *
     * @return 获取关联的边实体列表
     */
    List<GraphEdgeEntity<S, T, E>> getEdges();

    /**
     * 是否不考虑方向
     * @return 是否不考虑方向
     */
    boolean isIgnoreDirect();

    /**
     * 设置值
     * @param ignoreDirect 是否忽略方向
     */
    void setIgnoreDirect(boolean ignoreDirect);

    /**
     * 必须提供hash code
     *
     * @return 获取hash
     */
    int getHashCode();

    /**
     * 判断关系是否相等
     *
     * @param o 判断是否相等的对象
     * @return 是否相等
     */
    boolean isEquals(Object o);

    /**
     * 设置层级
     *
     * @param level 层级
     */
    void setLevel(int level);

    /**
     * 获取层级
     *
     * @return 获取层级
     */
    int getLevel();

}
