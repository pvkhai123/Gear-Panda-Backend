package com.pvkhai.gearpandabackend.controllers;


import com.pvkhai.gearpandabackend.dto.UserRegistrationDTO;
import com.pvkhai.gearpandabackend.dto.UserUpdateDTO;
import com.pvkhai.gearpandabackend.models.ResponseObject;
import com.pvkhai.gearpandabackend.models.User;
import com.pvkhai.gearpandabackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
//@CrossOrigin("http://localhost:3000")
@RequestMapping("api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;


    // Get all users - ADMIN
    @GetMapping()
    public ResponseEntity<ResponseObject> getAllUsers() {
        return userService.getAllUsers();
    }

    // Register new user - USER/ADMIN
    @PostMapping("/signup")
    public ResponseEntity<ResponseObject> addNewUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        return userService.addNewUser(userRegistrationDTO);
    }

    // Get user details - USER/ADMIN
    @GetMapping("/account/profile/{id}")
    public ResponseEntity<ResponseObject> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Update user details - USER
    @PutMapping("/account/profile/{id}")
    public ResponseEntity<ResponseObject> updateUser(@RequestBody UserUpdateDTO userUpdateDTO, @PathVariable Long id) {
        return userService.updateUserDetail(userUpdateDTO, id);
    }

    // Update user details - ADMIN
    @PutMapping("/admin/account/{id}")
    public ResponseEntity<ResponseObject> editUserInfo(@RequestBody User user, @PathVariable Long id) {
        return userService.editUserInfo(user, id);
    }

    // Delete User - ADMIN
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }

}
