package com.issac.validator;

import com.issac.dto.User;
import com.issac.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * author:  ywy
 * date:    2019-01-23
 * desc:
 */
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {

    @Autowired
    HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        log.info("my validator init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        helloService.greeting();
        log.info("{}",value);
        return true;
    }
}
