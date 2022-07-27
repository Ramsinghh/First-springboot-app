package com.ram;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    List<User> list=new ArrayList<User>(){
        {
            add(new User("ramu",1));
            add(new User("shyam",2));
            add(new User("raj",3));
            add(new User("vikash",4));
        }};

    @GetMapping("/users")
    public List<User> getAllUser(@RequestParam(defaultValue = "0") String name){
        return list.stream()
                .filter(x->x.getName().contains(name))
                .collect(Collectors.toList());
    }

    //json - javascript object notation
}

class User{
    private String name;
    public Integer rollNo;

    public User(String name, Integer rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRollNo() {
        return rollNo;
    }

    public void setRollNo(Integer rollNo) {
        this.rollNo = rollNo;
    }
}
