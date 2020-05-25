package cn.zhangjl.validator.validation.validator;

import cn.zhangjl.validator.validation.MultipleOfThree;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author zhangjl
 * @description 针对int进行校验
 * @className MultipleOfThreeForInteger
 * @date 2020/5/25 11:28
 */
public class MultipleOfThreeForInteger implements ConstraintValidator<MultipleOfThree, Integer> {


    @Override
    public void initialize(MultipleOfThree constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        //不对null的值生效， 这个是默认的根据其他的规则来的，可以自己去进行定义，这样不容易搞乱
        if (value == null) {
            return true;
        }
        return value % 3 == 0;
    }


}
