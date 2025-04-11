
package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.milind.config.JwtProvider;
import com.milind.model.User;
import com.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
    User user = userRepository.findByEmail(email);
    if (user == null) {
        throw new RuntimeException("User not found with email: " + email);
    }
    return user;
}


    // @Override
    // public User findUserByEmail(String email) throws Exception {
    //     return userRepository.findByEmail(email)
    //             .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    // }
}
