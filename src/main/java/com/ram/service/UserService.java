package com.ram.service;

import com.ram.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> list = new ArrayList<User>() {
        {
            add(new User("Ramsingh", 1));
            add(new User("shyam", 2));
            add(new User("raj", 3));
            add(new User("vikash", 4));
        }
    };

    public List<User> getUsers() {
        return list;
    }

    public Optional<User> getUser(Integer rollNo) {
        return list.stream()
                .filter(x -> x.rollNo == rollNo)
                .findAny();
    }

    public void save(User user){
        list.add(user);
    }

    public boolean update( User user) {
        Optional<User> optionalUser = list.stream()
                .filter(x -> x.rollNo == user.rollNo)
                .findAny();

        if (optionalUser.isPresent()) {
            optionalUser.get().setName(user.getName());
            return true;
        }
        return false;
    }

    public boolean delete( Integer roll) {
        Optional<User> optionalUser = list.stream()
                .filter(x -> x.rollNo == roll)
                .findAny();
        if (optionalUser.isPresent()) {
            list.remove(optionalUser.get());
            return true;
        }
        return false;
    }
}
