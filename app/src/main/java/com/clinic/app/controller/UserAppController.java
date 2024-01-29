package com.clinic.app.controller;

import com.clinic.app.model.UserApp;
import com.clinic.app.service.UserAppService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@Api(value="/usersApp",tags ="UsersApp")
@RequestMapping(value = "/usersApp")
public class UserAppController {
    private final UserAppService userAppService;

    public UserAppController(UserAppService userAppService){
        this.userAppService = userAppService;
    }

    @GetMapping("/current")
    public UserApp user(Principal user){
        if(user!= null){
            return this.userAppService.findbyEmail(user.getName());
        }else{
            return  null;
        }
    }
}
