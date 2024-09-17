package com.airtravel.controller;

import com.airtravel.entity.AppUser;
import com.airtravel.payload.LoginDto;
import com.airtravel.repository.AppUserRepository;
import com.airtravel.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserService userService;
    private AppUserRepository appUserRepository;

    public UserController(UserService userService, AppUserRepository appUserRepository) {
        this.userService = userService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping
    public ResponseEntity<?> createUser(
            @RequestBody AppUser user
    ){
        if (appUserRepository.existsByEmail(user.getEmail())){
            return new ResponseEntity<>("Email Exists", HttpStatus.BAD_REQUEST);
        }
        if (appUserRepository.existsByUsername(user.getUsername())){
            return new ResponseEntity<>("Username Exists", HttpStatus.BAD_REQUEST);
        }
        AppUser createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> verifyLogin(@RequestBody LoginDto loginDto){
        String token = userService.verifyLogin(loginDto);
        if (token != null){

            JWTTokenDto tokenDto = new JWTTokenDto();
            tokenDto.setType("JWT Token");
            tokenDto.setToken(token);

            return new ResponseEntity<>(tokenDto, HttpStatus.OK);

        }
        else{
            return new ResponseEntity<>("Invalid token", HttpStatus.OK);
        }
    }

}
