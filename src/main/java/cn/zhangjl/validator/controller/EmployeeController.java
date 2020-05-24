package cn.zhangjl.validator.controller;

import cn.zhangjl.validator.entity.Employee;
import cn.zhangjl.validator.vo.ResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.groups.Default;

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
//
//    @Autowired
//    private IemployeeService employeeService;

    @PostMapping
    public ResultVo add(@RequestBody @Validated(value = {Employee.Add.class, Default.class}) Employee employee) {
//        employeeService.add(employee);
        return ResultVo.success();
    }

    @PutMapping
    public ResultVo modify(@RequestBody @Validated(value = {Employee.Modify.class, Default.class}) Employee employee) {
        return ResultVo.success();
    }

    @GetMapping("{id:\\d}")
    public ResultVo findById(@PathVariable @Min(1) Integer id) {
//        Employee byId = employeeService.findById(id);
        return ResultVo.success();
    }

}
