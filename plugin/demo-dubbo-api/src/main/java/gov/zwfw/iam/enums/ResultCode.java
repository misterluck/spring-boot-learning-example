package gov.zwfw.iam.enums;

/**
 * 操作码枚举类
 * @author zhaolei
 */
public enum ResultCode implements IResultCode {
    SUCCESS("200", "操作成功"),
    FAILED("500", "操作失败"),
    UNAUTHORIZED("401", "未授权请求"),
    FORBIDDEN("403", "拒绝请求"),
    VALIDATE_FAILED("404", "参数检验失败"),
    ;

    private String code;
    private String message;

    private ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
