package ks.msx.SpringSecurity.controllers;



import ks.msx.SpringSecurity.entity.User;
import ks.msx.SpringSecurity.service.JpaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final JpaUserService service;
    @GetMapping
    public String unsecuredData(){
        return "Unsecured Data";
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('READ' , 'WRITE')")
    public String securedData(){
        return "Secured Data";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('WRITE')")
    public String adminData(){
        return "Admin Data";
    }

    @GetMapping("/info")
    @PreAuthorize("hasAuthority('WRITE')")
    public String userData(Principal principal){
        return principal.getName();
    }

    @GetMapping("/auth")
    @PreAuthorize("hasAuthority('WRITE')")
    public String getAuth(){
        var u = SecurityContextHolder.getContext().getAuthentication();
        u.getAuthorities().forEach(System.out::println);
        return "auth";
    }

    @PostMapping("/adduser")
    @PreAuthorize("hasAuthority('WRITE')")
    public void addUser(@RequestBody User user){
        service.createUser(user);
    }
}
