/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.exception;

import io.github.anyzm.graph.ocean.common.ResponseService;

/**
 * Description  NebulaException is used for
 *
 * @author Anyzm
 * Date  2021/7/15 - 15:16
 * @version 1.0.0
 */
public class NebulaException extends RuntimeException {

    private String code;

    public NebulaException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public NebulaException(Throwable throwable) {
        super(throwable);
        this.code = throwable.getMessage();
    }

    public NebulaException(ResponseService responseService) {
        super(responseService.getResponseMessage());
        this.code = responseService.getResponseCode();
    }

}
