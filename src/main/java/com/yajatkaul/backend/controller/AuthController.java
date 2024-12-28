package com.yajatkaul.backend;

import com.yajatkaul.backend.model.User;
import com.yajatkaul.backend.reqModel.LoginRequest;
import com.yajatkaul.backend.reqModel.SignupRequest;
import com.yajatkaul.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {

    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupRequest request){
        if(!request.getPassword().equals(request.getConfirmPassword())){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Passwords do not match");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        User user = new User();

        user.setUserName(request.getUserName());
        user.setPassword(request.getPassword());

        userService.addUser(user);

        Map<String, String> successResponse = new HashMap<>();
        successResponse.put("result", "Account Created");
        return ResponseEntity.status(HttpStatus.CREATED).body(successResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request){
        final Optional<User> user = userService.getUser(request.userName);

        if (user.isEmpty()) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        User foundUser = user.get();
        if(request.password.equals(foundUser.getPassword())){
            Map<String, String> successResponse = new HashMap<>();
            successResponse.put("result", "Logged in");

            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Invalid username or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/{name}")
    public String dynamicPath(@PathVariable String name){
        return "Hello World" + name;
    }
}