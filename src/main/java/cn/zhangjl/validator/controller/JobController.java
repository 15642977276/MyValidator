package cn.zhangjl.validator.controller;

import cn.zhangjl.validator.entity.Job;
import cn.zhangjl.validator.vo.ResultVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhangjl
 * @description
 * @className JobController
 * @date 2020/5/24 下午8:59
 */
@Validated
@RestController
@RequestMapping("/job")
public class JobController {

    @PostMapping
    public ResultVo add(@RequestBody @Valid Job job) {
        return ResultVo.success();
    }

}
