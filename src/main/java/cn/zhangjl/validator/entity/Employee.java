package cn.zhangjl.validator.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

/**
 * @author zhangjl
 * @description 员工
 * @className Emploeey
 * @date 2020/5/23 下午10:03
 */
@Data
public class Employee {
    /**
     * 主键ID
     */
    @Null
    private Integer id;
    /**
     * 姓名
     */
    @NotEmpty
    private String name;
    /**
     * 所属部门
     */
    @Valid
    private Department department;

}
