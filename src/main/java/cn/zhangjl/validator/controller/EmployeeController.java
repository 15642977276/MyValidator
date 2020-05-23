package cn.zhangjl.validator.controller;

import cn.zhangjl.validator.entity.Employee;
import cn.zhangjl.validator.service.IemployeeService;
import cn.zhangjl.validator.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangjl
 * @description
 * @className EmployeeController
 * @date 2020/5/23 下午10:06
 */
//@Validated
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IemployeeService employeeService;

    @PostMapping
    public ResultVo add(@RequestBody
//                            @Valid
                                Employee employee) {
        employeeService.add(employee);
        return ResultVo.success();
    }

    @GetMapping("{id:\\d}")
    public ResultVo findById(@PathVariable Integer id){
        Employee byId = employeeService.findById(id);
        return ResultVo.success(byId);
    }

}
