package cn.zhangjl.validator.enums;

/**
 * ResultVo 的错误码和错误信息
 *
 * @author zhangjl
 * @description
 * @className ErrorCode
 * @date 2020/5/23 下午9:31
 */
public enum ErrorCode {
    PARAM_ERROR("1000","参数错误！")
    ;

    private String code;

    private String msg;

    ErrorCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
