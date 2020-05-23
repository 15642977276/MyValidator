package cn.zhangjl.validator.service;

import cn.zhangjl.validator.entity.Employee;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author zhangjl
 * @description
 * @className IemployeeService
 * @date 2020/5/23 下午10:37
 */

public interface IemployeeService {

    void add(@Valid Employee employee);

    Employee findById(@NotNull Integer id);
}
