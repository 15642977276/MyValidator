package cn.zhangjl.validator.validation.validator;

import cn.zhangjl.validator.validation.MultipleOfThree;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author zhangjl
 * @description
 * @className MultipleOfThreeForList
 * @date 2020/5/25 11:33
 */
public class MultipleOfThreeForList implements ConstraintValidator<MultipleOfThree, List> {

    @Override
    public void initialize(MultipleOfThree constraintAnnotation) {

    }

    @Override
    public boolean isValid(List value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.size() % 3 == 0;
    }

}
