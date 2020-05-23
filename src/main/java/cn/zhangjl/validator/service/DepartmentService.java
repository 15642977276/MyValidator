package cn.zhangjl.validator.service;

import cn.zhangjl.validator.entity.Department;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * @author zhangjl
 * @description
 * @className DepartmentService
 * @date 2020/5/23 下午10:41
 */
@Validated
@Service
public class DepartmentService {

    public void add(@Valid Department department) {
        //业务逻辑
        System.out.println(department.toString());
        System.out.println("部门添加成功");
    }
}
