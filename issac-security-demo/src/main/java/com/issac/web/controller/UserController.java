package com.issac.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.issac.dto.User;
import com.issac.dto.UserQueryCondition;
import com.issac.exceptions.UserNotExistException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * author:  ywy
 * date:    2019-01-22
 * desc:
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails details, Authentication authentication) {
//        return SecurityContextHolder.getContext().getAuthentication();
//        return authentication;
        return details;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(
//                @RequestParam(value = "username", required = false, defaultValue = "tom") String username,
            UserQueryCondition condition, @PageableDefault(size = 10,page = 1,sort = {"age,desc"}) Pageable pageable) {
        log.info(ReflectionToStringBuilder.toString(condition));
        log.info("{},{},{}",pageable.getPageSize(),pageable.getPageNumber(),pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam("用户id") @PathVariable String id){

//        throw new UserNotExistException(id);
        log.info("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    public User create(@RequestBody @Valid User user, BindingResult errors) {

        if(errors.hasErrors()) {

            errors.getAllErrors().stream().forEach(error -> log.info(error.getDefaultMessage()));
        }

        log.info("{},{},{},{}",user.getUsername(),user.getPassword(),user.getId(),user.getBirthDay());
        user.setId(1);
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@RequestBody @Valid User user, BindingResult errors) {

        if(errors.hasErrors()) {

            errors.getAllErrors().stream().forEach(error -> {
//                FieldError fieldError = (FieldError)error;
//                String message = fieldError.getField() + " " + error.getDefaultMessage();
                log.info(error.getDefaultMessage());
            });
        }

        log.info("{},{},{},{}",user.getUsername(),user.getPassword(),user.getId(),user.getBirthDay());
        user.setId(1);
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        log.info(id);
    }

}
