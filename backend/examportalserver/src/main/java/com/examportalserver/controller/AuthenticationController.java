package com.examportalserver.controller;

import com.examportalserver.models.User;
import com.examportalserver.payloads.requests.JwtRequest;
import com.examportalserver.payloads.responses.JwtResponse;
import com.examportalserver.services.impl.UserServiceDetailsImpl;
import com.examportalserver.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("authentication")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthenticationController {
    private UserServiceDetailsImpl userServiceDetails;
    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;
    private PasswordEncoder bCryptPasswordEncoder ;
    @Autowired
    public AuthenticationController(UserServiceDetailsImpl userServiceDetails, AuthenticationManager authenticationManager, JwtUtils jwtUtils,PasswordEncoder bCryptPasswordEncoder) {
        this.userServiceDetails = userServiceDetails;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            String cryptPassword=bCryptPasswordEncoder.encode(jwtRequest.getPassword());
            authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (UsernameNotFoundException unfe){
            unfe.printStackTrace();
            System.out.println("Username not found");
        }
        UserDetails userDetails= this.userServiceDetails.loadUserByUsername(jwtRequest.getUsername());
        String token=jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(username,password)));
        }catch (DisabledException de){
            throw new Exception("User Disabled "+ de.getMessage());
        }catch (BadCredentialsException bce){
            throw new Exception("Invalid Credentials "+ bce.getMessage());
        }
    }

    @GetMapping("/current-user")
    public User currentUser(Principal principal){
        String name =principal.getName();
         return  ( (User) this.userServiceDetails.loadUserByUsername(principal.getName()));
    }
}
