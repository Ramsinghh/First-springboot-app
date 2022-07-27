package com.ram;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {

    List<User> list = new ArrayList<User>() {
        {
            add(new User("ramu", 1));
            add(new User("shyam", 2));
            add(new User("raj", 3));
            add(new User("vikash", 4));
        }
    };

    @GetMapping("/users")
    public List<User> getAllUser(@RequestParam(defaultValue = "") String name) {
        return list.stream()
                .filter(x -> x.getName().contains(name))
                .collect(Collectors.toList());
    }
    @GetMapping("/users/{rollNo}")
    public User getUser(@PathVariable Integer rollNo) {
        Optional<User> optionalUser = list.stream()
                .filter(x -> x.rollNo == rollNo)
                .findAny();

        if (optionalUser.isPresent()) {
            return optionalUser.get();
            }
        else {
            throw new RuntimeException("User not found");
        }
    }

    @PostMapping("/users")
    public String save(@RequestBody User user) {
        list.add(user);
        return "user saved successfully";
    }

    @PutMapping("/users")
    public String update(@RequestBody User user) {
        Optional<User> optionalUser = list.stream()
                .filter(x -> x.rollNo == user.rollNo)
                .findAny();

        if (optionalUser.isPresent()) {
            optionalUser.get().setName(user.getName());
            return "Updated successfully";
        }
        return "User not found";
    }

    @DeleteMapping("/users/{roll}")
    public String delete(@PathVariable Integer roll) {
        Optional<User> optionalUser = list.stream()
                .filter(x -> x.rollNo == roll)
                .findAny();
        if (optionalUser.isPresent()) {
            list.remove(optionalUser.get());
            return "Deleted successfully";
        }
        return "User not found";
    }
}