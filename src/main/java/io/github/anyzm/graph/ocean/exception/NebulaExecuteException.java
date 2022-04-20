/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.exception;

import io.github.anyzm.graph.ocean.enums.ErrorEnum;
import lombok.Getter;

/**
 * @author Anyzm
 * @version 1.0.0
 * Description NebulaExecuteException
 * Date 2020/3/19 - 10:29
 */
public class NebulaExecuteException extends RuntimeException {

    @Getter
    private String code;

    @Getter
    private String msg;


    public NebulaExecuteException(int code, String msg) {
        super(msg);
        this.code = String.valueOf(code);
        this.msg = msg;
    }

    public NebulaExecuteException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public NebulaExecuteException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = String.valueOf(code);
        this.msg = msg;
    }

    public NebulaExecuteException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public NebulaExecuteException(ErrorEnum errorEnum) {
        super(errorEnum.getResponseMessage());
        this.code = errorEnum.getResponseCode();
        this.msg = errorEnum.getResponseMessage();
    }

    public NebulaExecuteException(ErrorEnum errorEnum, Throwable cause) {
        super(errorEnum.getResponseMessage(), cause);
        this.code = errorEnum.getResponseCode();
        this.msg = errorEnum.getResponseMessage();
    }


}
