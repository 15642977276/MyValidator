package cn.zhangjl.validator.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author zhangjl
 * @description
 * @className Job
 * @date 2020/5/24 下午8:57
 */
@Data
public class Job {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 姓名
     */
    @Size(min = 1)
    private String name;
    

    /**
     * 标签
     */
    @NotNull
    @Size(min = 1)
    private List<String> lables;
}
