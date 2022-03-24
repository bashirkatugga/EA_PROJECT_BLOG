package com.blog.userservice.Controllers;

import com.blog.userservice.Domain.User;
import com.blog.userservice.Services.UserService;
import com.blog.userservice.VO.ResponseTemplateVO;
import com.blog.userservice.models.AuthenticationRequest;
import com.blog.userservice.models.AuthenticationResponse;
import com.blog.userservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Resource
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public AuthenticationResponse authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(authenticationRequest.getUsername());

        final String token =
                jwtUtil.generateToken(userDetails);

        return  new AuthenticationResponse(token);
    }


    @GetMapping("/users")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/users/{id}")
    public ResponseTemplateVO getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    @PostMapping("/users")
    public RedirectView addUser(@Valid @RequestBody User user){
        userService.add(user);
        return new RedirectView("/users");
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable Long id, @Valid @RequestBody User user){
        userService.update(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id){
        userService.delete(id);
    }







}
