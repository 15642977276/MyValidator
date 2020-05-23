package cn.zhangjl.validator.vo;

import cn.zhangjl.validator.enums.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 简单返回对象
 *
 * @author zhangjl
 * @description
 * @className ResultVo
 * @date 2020/5/23 下午9:19
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo {

    /**
     * 是否处理成功
     */
    private boolean success;
    /**
     * 状态码
     */
    private String code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 返回值
     */
    private Object data;

    public static ResultVo success() {
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(true);
        return resultVo;
    }

    public static ResultVo success(Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(true);
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo fail(ErrorCode errorCode, Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(errorCode.getCode());
        resultVo.setMsg(errorCode.getMsg());
        resultVo.setSuccess(false);
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo fail(ErrorCode errorCode) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(errorCode.getCode());
        resultVo.setMsg(errorCode.getMsg());
        resultVo.setSuccess(false);
        return resultVo;
    }

}
