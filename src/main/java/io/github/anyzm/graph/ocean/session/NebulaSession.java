/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.session;

import io.github.anyzm.graph.ocean.domain.Session;
import io.github.anyzm.graph.ocean.exception.NebulaExecuteException;
import com.vesoft.nebula.client.graph.data.ResultSet;

/**
 * Description  NebulaSession is used for
 *
 * @author Anyzm
 * Date  2021/7/15 - 18:26
 * @version 1.0.0
 */
public interface NebulaSession extends Session {


    /**
     * 执行查询
     *
     * @param statement 语句
     * @return 查询结果
     * @throws NebulaExecuteException nebula执行异常
     */
    public ResultSet executeQuery(String statement) throws NebulaExecuteException;


}
