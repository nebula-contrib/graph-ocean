/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package com.github.anyzm.graph.ocean.exception;


import com.github.anyzm.graph.ocean.enums.ErrorEnum;

/**
 * Description  NebulaVersionConflictException is used for
 *
 * @author Anyzm
 * Date  2021/8/9 - 10:54
 * @version 1.0.0
 */
public class NebulaVersionConflictException extends NebulaExecuteException {


    public NebulaVersionConflictException(int code, String msg) {
        super(code, msg);
    }

    public NebulaVersionConflictException(String code, String msg) {
        super(code, msg);
    }

    public NebulaVersionConflictException(int code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public NebulaVersionConflictException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

    public NebulaVersionConflictException(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public NebulaVersionConflictException(ErrorEnum errorEnum, Throwable cause) {
        super(errorEnum, cause);
    }

}
