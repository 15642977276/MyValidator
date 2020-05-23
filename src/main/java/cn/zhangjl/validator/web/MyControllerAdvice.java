package cn.zhangjl.validator.web;

import cn.zhangjl.validator.enums.ErrorCode;
import cn.zhangjl.validator.vo.ResultVo;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangjl
 * @description
 * @className ControllerAdvice
 * @date 2020/5/23 下午9:55
 */
@RestControllerAdvice(basePackages = "cn.zhangjl.validator.controller")
public class MyControllerAdvice {

    @ExceptionHandler
    public ResultVo exceptionHandler(MethodArgumentNotValidException e) {
        Map<String, String> collect = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        return ResultVo.fail(ErrorCode.PARAM_ERROR, collect);
    }

    @ExceptionHandler
    public ResultVo exceptionHandler(ConstraintViolationException e) {
        Map<Path, String> collect = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(ConstraintViolation::getPropertyPath, ConstraintViolation::getMessage));
        return ResultVo.fail(ErrorCode.PARAM_ERROR, collect);
    }
}
