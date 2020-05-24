package cn.zhangjl.validator.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
     * 如果指定了验证的组，那么该参数只会属于指定的组
     * 如果没有指定组， 那么属于默认组，需要在校验时同时加上默认组 javax.validation.groups.Default.class
     */
    public interface Add {
    }

    public interface Modify {
    }

    /**
     * 主键ID
     */
    @Null(groups = {Add.class})       // 在添加时生效
    @NotNull(groups = {Modify.class}) // 在修改时生效
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
