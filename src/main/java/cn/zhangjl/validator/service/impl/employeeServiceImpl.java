package cn.zhangjl.validator.service.impl;

import cn.zhangjl.validator.entity.Employee;
import cn.zhangjl.validator.service.IemployeeService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author zhangjl
 * @description
 * @className employeeServiceImpl
 * @date 2020/5/23 下午10:37
 */
@Service
@Validated
public class employeeServiceImpl implements IemployeeService {

    @Override
    public void add(Employee employee) {
        //业务逻辑
        System.out.println(employee.toString());
        System.out.println("员工添加成功");
    }

    @Override
    public @NotNull Employee findById(@NotNull Integer id) {
        return null;
    }
}
