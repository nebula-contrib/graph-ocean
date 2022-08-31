/* Copyright (c) 2022 com.github.anyzm. All rights reserved.
 *
 * This source code is licensed under Apache 2.0 License,
 * attached with Common Clause Condition 1.0, found in the LICENSES directory.
 */
package io.github.anyzm.graph.ocean.session;

import io.github.anyzm.graph.ocean.domain.impl.QueryResult;
import io.github.anyzm.graph.ocean.enums.ErrorEnum;
import io.github.anyzm.graph.ocean.exception.CheckThrower;
import io.github.anyzm.graph.ocean.exception.NebulaException;
import io.github.anyzm.graph.ocean.exception.NebulaExecuteException;
import io.github.anyzm.graph.ocean.exception.NebulaVersionConflictException;
import com.vesoft.nebula.client.graph.data.ResultSet;
import com.vesoft.nebula.client.graph.net.Session;
import com.vesoft.nebula.graph.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Description  NebulaSessionWrapper is used for
 *
 * @author Anyzm
 * Date  2021/7/15 - 16:49
 * nebula-session包装类，区别读写执行，加强返回结果的封装
 * @version 1.0.0
 */
@Slf4j
public class NebulaSessionWrapper implements NebulaSession {

    private Session session;

    private static final String E_DATA_CONFLICT_ERROR = "E_DATA_CONFLICT_ERROR";

    public NebulaSessionWrapper(Session session) throws NebulaExecuteException, NebulaException {
        CheckThrower.ifTrueThrow(session == null, ErrorEnum.SESSION_LACK);
        this.session = session;
    }

    @Override
    public int execute(String statement) throws NebulaExecuteException {
        ResultSet resultSet = null;
        try {
            log.debug("execute执行nebula,ngql={}", statement);
            resultSet = this.session.execute(statement);
        } catch (Exception e) {
            log.error("更新nebula异常 Thrift rpc call failed: {}", e.getMessage());
            throw new NebulaExecuteException(ErrorCode.E_RPC_FAILURE, e.getMessage(), e);
        }
        if (resultSet.getErrorCode() == ErrorCode.SUCCEEDED) {
            return ErrorCode.SUCCEEDED;
        }
        if (resultSet.getErrorCode() == ErrorCode.E_EXECUTION_ERROR
                && resultSet.getErrorMessage().contains(E_DATA_CONFLICT_ERROR)) {
            //版本冲突，session内部不再打印错误日志，直接抛出自定义的版本异常
            throw new NebulaVersionConflictException(resultSet.getErrorCode(), resultSet.getErrorMessage());
        }
        log.error("更新nebula异常 code:{}, msg:{}, nGql:{} ",
                resultSet.getErrorCode(), resultSet.getErrorMessage(), statement);
        throw new NebulaExecuteException(resultSet.getErrorCode(), resultSet.getErrorMessage());
    }

    @Override
    public ResultSet executeQuery(String statement) throws NebulaExecuteException {
        ResultSet resultSet = null;
        try {
            log.debug("executeQuery执行nebula,ngql={}", statement);
            resultSet = this.session.execute(statement);

        } catch (Exception e) {
            log.error("查询nebula异常 code:{}, msg:{}, nGql:{} ", ErrorCode.E_RPC_FAILURE, e.getMessage(), statement);
            throw new NebulaExecuteException(ErrorEnum.QUERY_NEBULA_EROR, e);
        }
        if (resultSet != null && resultSet.getErrorCode() != ErrorCode.SUCCEEDED) {
            log.error("查询nebula异常:{},{},nGql:{}", resultSet.getErrorCode(), resultSet.getErrorMessage(), statement);
            throw new NebulaExecuteException(ErrorEnum.QUERY_NEBULA_EROR);
        }
        return resultSet;
    }

    @Override
    public QueryResult executeQueryDefined(String statement) throws NebulaExecuteException {
        ResultSet resultSet = executeQuery(statement);
        if (!resultSet.isSucceeded()) {
            log.warn("executeQueryDefined execute fail,sql:"+statement);
            return new QueryResult();
        }
        return new QueryResult(IntStream.range(0,resultSet.rowsSize()).mapToObj(i->resultSet.rowValues(i)).collect(Collectors.toList()));
    }

    @Override
    public void release() {
        this.session.release();
    }

    @Override
    public boolean ping() {
        return this.session.ping();
    }
}
