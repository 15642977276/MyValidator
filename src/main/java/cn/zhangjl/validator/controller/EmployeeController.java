package cn.zhangjl.validator.controller;

import cn.zhangjl.validator.entity.Employee;
import cn.zhangjl.validator.vo.ResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhangjl
 * @description
 * @className EmployeeController
 * @date 2020/5/23 下午10:06
 */
@Validated
@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @PostMapping
    public ResultVo add(@RequestBody @Valid Employee employee) {
        return ResultVo.success();
    }

}
