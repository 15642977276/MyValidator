package cn.zhangjl.validator.controller;

import cn.zhangjl.validator.entity.Department;
import cn.zhangjl.validator.service.DepartmentService;
import cn.zhangjl.validator.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangjl
 * @description
 * @className DepartmentController
 * @date 2020/5/23 14:35
 */
//对类中的方法开启验证功能
//@Validated
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public ResultVo add(@RequestBody
//                            @Valid
                                    Department department) {
        departmentService.add(department);
        return ResultVo.success();
    }


}
