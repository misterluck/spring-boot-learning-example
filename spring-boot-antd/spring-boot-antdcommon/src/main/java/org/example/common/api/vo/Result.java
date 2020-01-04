package org.example.common.api.vo;

import org.example.common.constant.CodeConstant;

import java.io.Serializable;

/**
 * 接口返回对象
 * @param <T>
 */
public class Result<T> implements Serializable {
    private boolean success = true;
    private long timestamp;
    private long code;
    private String message;
    private T result;

    public Result() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    /* 以下为静态方法掉用后直接返回通用返回对象 */

    public static Result<Object> ok() {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(CodeConstant.SUCCESS_CODE_200);
        r.setMessage(CodeConstant.SUCCESS_CODE_200_MSG);
        return r;
    }

    public static Result<Object> ok(String msg) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(CodeConstant.SUCCESS_CODE_200);
        r.setMessage(msg);
        return r;
    }

    public static Result<Object> ok(Object data) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(CodeConstant.SUCCESS_CODE_200);
        r.setMessage(CodeConstant.SUCCESS_CODE_200_MSG);
        r.setResult(data);
        return r;
    }

    public static Result<Object> error(String msg) {
        return error(CodeConstant.INTERNAL_SERVER_ERROR_500, msg);
    }

    public static Result<Object> error(long code, String msg) {
        Result<Object> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    /**
     * 无权访问返回结果
     * @param msg
     * @return
     */
    public static Result<Object> noAuth(String msg) {
        return error(CodeConstant.UNAUTHORIZED_401, msg);
    }

}
