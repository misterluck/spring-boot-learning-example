package org.example.antdvue.entity;

import org.example.antdvue.constant.CodeConstant;

import java.io.Serializable;

public class Result<T> implements Serializable {
    private boolean success = true;
    private long timestamp;
    private String code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
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

    public Result<T> success(String message) {
        this.message = message;
        this.code = CodeConstant.SUCCESS_CODE_200;
        this.success = true;
        return this;
    }

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

    public static Result<Object> error(String code, String msg) {
        Result<Object> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public Result<T> error500(String message) {
        this.message = message;
        this.code = CodeConstant.INTERNAL_SERVER_ERROR_500;
        this.success = false;
        return this;
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
