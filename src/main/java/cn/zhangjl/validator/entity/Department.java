package cn.zhangjl.validator.entity;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.List;

/**
 * id          必须是null
 * parentId    不能为空,不能为null,必须大于0
 * name        不能为空 null 和 ""，长度必须大于0
 * createTime  不是未来的时间
 *
 * @author zhangjl
 * @description 公司
 * @className Department
 * @date 2020/5/23 14:37
 */
@Data
public class Department {
    /**
     * 主键
     */
    @Null(message = "主键不可以有值")
    private Integer id;
    /**
     * 父级ID
     */
    @NotNull
    private Integer parentId;
    /**
     * 部门名称
     */
    @NotBlank
    private String name;
    /**
     * 成立时间
     */
    @NotNull
    @PastOrPresent
    private LocalDateTime createTime;
    /**
     * 一对多用户
     */
    private List<@Valid Employee> employees;

}
