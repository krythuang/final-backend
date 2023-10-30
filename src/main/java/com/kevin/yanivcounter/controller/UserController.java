package com.kevin.yanivcounter.controller;

import com.kevin.yanivcounter.model.User;
import com.kevin.yanivcounter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        userService.saveUser(user);
        return "Added new user";
    }

    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("updateScore/{userId}")
    public ResponseEntity<String> updateScore(@PathVariable int userId, @RequestParam int toAdd) {
        try {
            User user = userService.getUserById(userId);
            if (user == null) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            int updatedScore = user.getScore() + toAdd;

            if (updatedScore == 50 || updatedScore == 100 || updatedScore == 150 || updatedScore == 200)
            {
                updatedScore /= 2;
            }

            user.setScore(updatedScore);
            user.setToAdd(toAdd);
            userService.saveUser(user);

            return new ResponseEntity<>("Score updated successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error updating score", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId) {
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error deleting user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
