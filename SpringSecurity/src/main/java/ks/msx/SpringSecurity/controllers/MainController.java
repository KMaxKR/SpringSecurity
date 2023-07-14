package ks.msx.SpringSecurity.controllers;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class MainController {

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
}
