/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ufps.demo.controller;
import com.ufps.demo.model.User;
import com.ufps.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {
        @Autowired
    UserRepository userRepo;

    @GetMapping
    public List<User> getUserAll() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public User getUsersbyId(@PathVariable Integer id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        return null;
    }

    @PostMapping
    public User postUsers(@PathVariable String username,@PathVariable String pass,@RequestBody User user) {
        Optional<User> userCurrent = userRepo.findByUsername(username);
        if(userCurrent.isPresent()){
            User userReturn = userCurrent.get();
            if(userReturn.getPass().equals(pass)){
              return userReturn;}
        }
        return null;
    }
    
    @PostMapping("/login")
    public User loginUsers(@RequestBody User user) {
        userRepo.save(user);
        return user;
    }


    @PutMapping("/{id}")
    public User putUsersbyId(@PathVariable Integer id, @RequestBody User user) {
        Optional<User> userCurrent = userRepo.findById(id);

        if (userCurrent.isPresent()) {
            User userReturn = userCurrent.get();
            userReturn.setUsername(user.getUsername());
            userReturn.setPass(user.getPass());
            userReturn.setEmail(user.getEmail());

            userRepo.save(userReturn);
            return userReturn;
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public User deleteUsersbyId(@PathVariable Integer id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isPresent()) {
            User userReturn = user.get();
            userRepo.deleteById(id);
            return userReturn;
        }

        return null;
    }
}
