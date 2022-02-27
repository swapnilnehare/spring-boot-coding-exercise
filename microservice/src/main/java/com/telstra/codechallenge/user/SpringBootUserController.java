package com.telstra.codechallenge.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/git")
public class SpringBootUserController {

    private SpringBootUserService springBootUserService;

    @Autowired
    public SpringBootUserController(
            SpringBootUserService springBootUserService) {
        this.springBootUserService = springBootUserService;
    }

    @GetMapping("/user/no-followers")
    public Users getHottest(@RequestParam(value ="noOfUser",defaultValue = "10") Integer noOfUser) {
        return springBootUserService.getOldestUserWithZeroFollower(noOfUser);
    }

}
