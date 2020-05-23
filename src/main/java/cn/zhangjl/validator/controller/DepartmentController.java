package cn.zhangjl.validator.controller;

import cn.zhangjl.validator.entity.Department;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author zhangjl
 * @description
 * @className DepartmentController
 * @date 2020/5/23 14:35
 */
@Validated //对类中的方法开启验证功能
@RestController
@RequestMapping("/department")
public class DepartmentController {

    /**
     * 添加部门
     * addRoot parent_id=0
     * add
     * <p>
     * id          必须是null
     * parentId    不能为空,不能为null,必须大于0
     * name        不能为空 null 和 ""，长度必须大于0
     * createTime  不是未来的时间
     */
    @PostMapping
    public String add(@RequestBody @Valid Department department) {
        return "ok";
    }
}
