/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao;

import io.github.anyzm.graph.ocean.domain.impl.GraphVertexEntity;
import io.github.anyzm.graph.ocean.exception.NebulaException;

/**
 * @author: Anyzm
 * @Description:
 * @Date: Created in 19:59 2021/7/18
 */
public interface GraphVertexEntityFactory {

    /**
     * 构建GraphVertexEntity
     *
     * @param input
     * @return
     * @throws NebulaException
     */
    public <T> GraphVertexEntity<T> buildGraphVertexEntity(T input) throws NebulaException;

}
