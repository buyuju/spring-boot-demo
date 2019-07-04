package com.example.demo.controller;

import com.example.demo.model.base.ResultJson;
import com.example.demo.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author limeng
 * @Description 用户管理API
 * @Date @Date: 2019/2/13 10:37
 * @Modified by :
 **/
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "登录")
    @GetMapping(value= "/api/{version}/demo/user-login/")
    public ResultJson login(@RequestParam("access_key") String accessKey, @RequestParam("access_secret") String accessSecret) {
        userService.login(accessKey, accessSecret);
        return ResultJson.success();
    }

}
