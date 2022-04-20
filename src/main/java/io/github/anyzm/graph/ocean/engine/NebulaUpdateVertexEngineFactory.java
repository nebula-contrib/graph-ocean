/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.engine;

import io.github.anyzm.graph.ocean.dao.GraphUpdateVertexEngineFactory;
import io.github.anyzm.graph.ocean.dao.VertexUpdateEngine;
import io.github.anyzm.graph.ocean.domain.impl.GraphVertexEntity;
import io.github.anyzm.graph.ocean.exception.NebulaException;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * Description  NebulaUpdateVertexEngineFactory is used for
 *
 * @author Anyzm
 * Date  2021/7/19 - 10:52
 * @version 1.0.0
 */
@Slf4j
public class NebulaUpdateVertexEngineFactory implements GraphUpdateVertexEngineFactory {

    @Override
    public <T> VertexUpdateEngine build(List<GraphVertexEntity<T>> graphVertexEntities) throws NebulaException {
        return new NebulaBatchVertexUpdate<>(graphVertexEntities);
    }

}
