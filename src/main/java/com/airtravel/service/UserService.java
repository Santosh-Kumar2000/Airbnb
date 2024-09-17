package com.airtravel.service;

import com.airtravel.entity.AppUser;
import com.airtravel.payload.LoginDto;
import com.airtravel.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private AppUserRepository appUserRepository;
    private JWTService jwtService;

    public UserService(AppUserRepository appUserRepository, JWTService jwtService) {
        this.appUserRepository = appUserRepository;
        this.jwtService = jwtService;
    }

    public AppUser createUser(AppUser user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        AppUser createdUser = appUserRepository.save(user);
        return createdUser;
    }

    public String verifyLogin(LoginDto loginDto) {
        Optional<AppUser> byUsername =  appUserRepository.findByUsername(loginDto.getUsername());
        if (byUsername.isPresent()){
            AppUser appUser = byUsername.get();
            if(BCrypt.checkpw(loginDto.getPassword(), appUser.getPassword())){
                String token = jwtService.generateToken(appUser);
                return token;
            }
        }
        return null;
    }

}
