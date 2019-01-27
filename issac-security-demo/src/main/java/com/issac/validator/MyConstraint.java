package com.issac.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * author:  ywy
 * date:    2019-01-23
 * desc:
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {

    String message() default "自定义验证";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
