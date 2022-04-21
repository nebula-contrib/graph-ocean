/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.dao;


import io.github.anyzm.graph.ocean.exception.NebulaException;

import java.util.List;

public interface BatchSql {
    /**
     *
     * @return 获取批量执行的 sql 列表
     * @throws NebulaException nebula异常
     */
    public List<String> getSqlList() throws NebulaException;
}
