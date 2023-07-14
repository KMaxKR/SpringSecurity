package ks.msx.SpringSecurity.controllers;



import ks.msx.SpringSecurity.entity.User;
import ks.msx.SpringSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final UserService service;

    @GetMapping
    public String unsecuredData(){
        return "Unsecured Data";
    }

    @GetMapping("/user")
    public String securedData(){
        return "Secured Data";
    }

    @GetMapping("/admin")
    public String adminData(){
        return "Admin Data";
    }

    @GetMapping("/info")
    public String userData(Principal principal){
        return principal.getName();
    }

    @PostMapping("/adduser")
    public void addUser(@RequestBody User user){
        service.createUser(user);
    }
}
