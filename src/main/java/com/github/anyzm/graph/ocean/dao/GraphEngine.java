/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package com.github.anyzm.graph.ocean.dao;

import com.github.anyzm.graph.ocean.domain.GraphLabel;
import java.util.List;

/**
 * Description  GraphEngine is used for
 *
 * @author Anyzm
 * Date  2021/8/10 - 11:16
 * @version 1.0.0
 */
public interface GraphEngine extends BatchSql {

    /**
     * 获取操作标签(TAG || 关系(边))
     *
     * @return
     */
    public List<GraphLabel> getLabels();

}
