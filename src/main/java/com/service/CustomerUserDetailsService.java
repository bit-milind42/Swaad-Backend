
package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.milind.model.USER_ROLE;
import com.repository.UserRepository;
import com.milind.model.User;  // Ensure the User model is imported correctly

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Fixed: Proper user retrieval from the repository
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email " + username);
        }

        // Fixed: Proper role retrieval and default role assignment if null
        USER_ROLE role = user.getRole();
        // if (role == null) {
        //     role = USER_ROLE.ROLE_CUSTOMER;  // Default role if null
        // }

        // Authorities list
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        // Return user details with the authorities
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
