package com.ram.controller;

import com.ram.model.User;
import com.ram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @EventListener
    public void obserbLoginAttempt(InteractiveAuthenticationSuccessEvent event){
        UserDetails userDetails=(org.springframework.security.core.userdetails.User)event.getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername()+" logged in successfully");
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userService.getUsers();
    }

    @GetMapping("/users/{rollNo}")
    public User getUser(@PathVariable Integer rollNo) {
        Optional<User> optionalUser = userService.getUser(rollNo);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else
            throw new RuntimeException("User not found");
    }

    @PostMapping("/users")
    public String save(@RequestBody User user) {
        userService.save(user);
        return "user saved successfully";
    }

    @PutMapping("/users")
    public String update(@RequestBody User user) {
        return userService.update(user)?"Updated successfully":"User not found";
    }

    @PreAuthorize("hasAnyAuthority('Admin')")
    @DeleteMapping("/users/{roll}")
    public String delete(@PathVariable Integer roll) {
        return userService.delete(roll)?"Deleted successfully":"User not found";
    }
}