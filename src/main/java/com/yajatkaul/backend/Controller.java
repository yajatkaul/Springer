package com.yajatkaul.backend;

import com.yajatkaul.backend.model.User;
import com.yajatkaul.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/{name}")
    public String dynamicPath(@PathVariable String name){
        return "Hello World" + name;
    }

    @PostMapping("/login")
    public String postEx(@RequestBody User user){
        return "Post World" + user.getUserName();
    }
}