package ru.geekbrains.boot_practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.geekbrains.boot_practice.security.UserAuthService;

@RequestMapping("/login")
@Controller
public class AuthController {
//    private UserAuthService userAuthService;

//    @Autowired
//    public AuthController(UserAuthService userAuthService) {
//        this.userAuthService = userAuthService;
//    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }

}
