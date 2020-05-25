package cn.zhangjl.validator.validation;

import cn.zhangjl.validator.validation.validator.MultipleOfThreeForInteger;
import cn.zhangjl.validator.validation.validator.MultipleOfThreeForList;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author zhangjl
 * @description 自定义注解   FIELD:只对字段使用， RUNTIME 运行时可获得
 * @className MultipleOfThree
 * @date 2020/5/25 11:23
 */
@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {MultipleOfThreeForInteger.class, MultipleOfThreeForList.class})
public @interface MultipleOfThree {

    String message() default "必须是3的倍数";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
