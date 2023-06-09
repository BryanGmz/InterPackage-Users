package com.interpackage.users.controller;

import com.interpackage.users.model.Response;
import com.interpackage.users.producers.UserProducer;
import com.interpackage.users.service.EventService;
import com.interpackage.users.util.CommonParams;
import com.interpackage.users.util.Constants;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.interpackage.users.model.User;
import com.interpackage.users.service.UserService;
import com.interpackage.users.aspect.RequiredRole;


@RestController
@RequestMapping ("/api/users/v1/users")
public class UserController {
    private final UserService userService;
    private final EventService eventService;

    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/{name}")
    @RequiredRole({"Admin", "Client"})
    public ResponseEntity<User> getUserByName(@PathVariable String name) {
        User user = userService.getUserByName(name);
        return ResponseEntity.ok(user);
    }

    @GetMapping()
    @RequiredRole({"Admin", "role test"})
    public ResponseEntity<Page<User>> getAllUsers(CommonParams commonParams) {
        return ResponseEntity.ok(userService.getAllUser(commonParams.getPage(),commonParams.getMax(), commonParams.isPagination()));
    }

    @PostMapping()
    @RequiredRole({"Admin", "role test"})
    public ResponseEntity<?> createUser(@Valid @RequestBody User usr){
        User user = null;
        try {
            user = userService.createUser(usr);
            eventService.sendNotification(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(user);
    }

    @PutMapping("/{name}")
    @RequiredRole({"Admin", "role test"})
    public ResponseEntity<?> updateUser(@RequestBody User usr, @PathVariable String name){
        User user = null;
        try {
            user = userService.updateUser(usr,name);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok(user);
    }

    @DeleteMapping("/{name}")
    @RequiredRole({"Admin", "role test"})
    public ResponseEntity<?> deleteUser(@PathVariable String name){
        try {
            userService.deleteUser(name);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/getByDPI/{dpi}")
    @RequiredRole({
            Constants.ROLE_ADMIN,
            Constants.ROLE_RECEPTIONIST
    })
    public ResponseEntity<Response> getClientByDPI(@PathVariable String dpi) {
        return this.userService.getClientByDpi(dpi);
    }
}
