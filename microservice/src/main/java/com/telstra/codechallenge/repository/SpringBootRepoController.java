package com.telstra.codechallenge.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/git")
public class SpringBootRepoController {

    private SpringBootRepoService springBootRepoService;

    @Autowired
    public SpringBootRepoController(
            SpringBootRepoService springBootRepoService) {
        this.springBootRepoService = springBootRepoService;
    }

    @GetMapping("/repository/hottest")
    public Repositories getHottest(@RequestParam(value = "noOfSets",defaultValue = "10") Integer noOfSets) {
        return springBootRepoService.getHottest(noOfSets);
    }

}
