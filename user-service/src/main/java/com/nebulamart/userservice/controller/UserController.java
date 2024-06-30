package com.nebulamart.userservice.controller;

import com.nebulamart.userservice.service.UserService;
import com.nebulamart.userservice.template.ChangePassword;
import com.nebulamart.userservice.template.ChangePasswordResponse;
import com.nebulamart.userservice.template.StatusResponse;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-out")
    public StatusResponse signOut(@PathParam("accessToken") String accessToken) {
        return userService.signOut(accessToken);
    }

    @PostMapping("/change-password")
    public ResponseEntity<ChangePasswordResponse> changePassword(@RequestHeader("Authorization") String accessToken, @RequestBody ChangePassword changePassword) {
        if (!changePassword.isValid()) {
            return ResponseEntity.status(400).body(new ChangePasswordResponse(false, "Missing old or new password"));
        }
        try {
            ResponseEntity<ChangePasswordResponse> responseEntity = userService.changeTempPassword(accessToken, changePassword.getOldPassword(), changePassword.getNewPassword());
            if (responseEntity == null) {
                return ResponseEntity.status(400).body(new ChangePasswordResponse(false, "Failed to change password"));
            }
            return responseEntity;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(new ChangePasswordResponse(false, e.getMessage()));
        }
    }

}
