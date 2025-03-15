// package com.controller;

// import java.util.Collection;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.milind.config.JwtProvider;
// import com.milind.model.Cart;
// import com.milind.model.USER_ROLE;
// import com.milind.model.User;
// import com.repository.CartRepository;
// import com.repository.UserRepository;
// import com.request.LoginRequest;
// import com.response.AuthResponse;
// import com.service.CustomerUserDetailsService;

// @RestController
// @RequestMapping("/auth")

// public class AuthController {
//     @Autowired
//     private UserRepository userRepository;
//     @Autowired
//     private PasswordEncoder passwordEncoder;
//     @Autowired
//     private JwtProvider jwtProvider;
//     @Autowired
//     private CustomerUserDetailsService customerUserDetailsService;
//     @Autowired
//     private CartRepository cartRepository;

//     @PostMapping("/signup")
//     public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user){

//         User isEmailExist = userRepository.findByEmail(user.getEmail());
//         if(isEmailExist!=null){
//             throw new Exception("Email is already used with another account");
//         }

//         User createdUser=new User();
//         createdUser.setEmail(user.getEmail());
//         createdUser.setFullName(user.getFullName());
//         createdUser.setRole(user.getRole());
//         createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
//         User savedUser = userRepository.save(createdUser);

//         Cart cart= new Cart();
//         cart.setCustomer(savedUser);
//         cartRepository.save(cart);

//         Authentication authentication=new UsernamePasswordAuthenticationToken(user.getEmail, user.getPassword());
//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         String jwt=jwtProvider.generateToken(authentication);

//         AuthResponse authResponse=new AuthResponse();
//         authResponse.setJwt(jwt);
//         authResponse.setMessage("Register success");
//         authResponse.setRole(savedUser.getRole());


//         return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
//     }


//     @PostMapping("/signin")

//     public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req){

//         String username=req.getEmail();
//         String password = req.getPassword();

//         Authentication authentication = authenticate(username, password);

//         Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
//         String role=authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
//         String jwt=jwtProvider.generateToken(authentication);

//         AuthResponse authResponse=new AuthResponse();
//         authResponse.setJwt(jwt);
//         authResponse.setMessage("Register success");
//         authResponse.setRole(USER_ROLE.valueOf(role));


//         return new ResponseEntity<>(authResponse, HttpStatus.OK);

//         return null;
//     }

//     private Authentication authenticate(String username, String password){
//         UserDetails userDetails=customerUserDetailsService.loadUserByUsername(username);

//         if(userDetails==null){
//             throw new BadCredentialsException("Invalid Username");

//         }
//         if(!passwordEncoder.matches(password,userDetails.getPassword())){
//             throw new BadCredentialsException("Invalid password");

//         }
//         // return new org.springframework.security.core.userdetails.User(username, null,userDetails.getAuthorities());
//         return new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities()); 
//     }
// }




// package com.controller;

// import java.util.Collection;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.server.ResponseStatusException;

// import com.milind.config.JwtProvider;
// import com.milind.model.Cart;
// import com.milind.model.USER_ROLE;
// import com.milind.model.User;
// import com.repository.CartRepository;
// import com.repository.UserRepository;
// import com.request.LoginRequest;
// import com.response.AuthResponse;
// import com.service.CustomerUserDetailsService;

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     private JwtProvider jwtProvider;

//     @Autowired
//     private CustomerUserDetailsService customerUserDetailsService;

//     @Autowired
//     private CartRepository cartRepository;

//     @PostMapping("/signup")
//     public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) {

//         // Check if the email is already registered
//         User isEmailExist = userRepository.findByEmail(user.getEmail());
//         if (isEmailExist != null) {
//             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already used with another account");
//         }

//         // Save user with encrypted password
//         User createdUser = new User();
//         createdUser.setEmail(user.getEmail());
//         createdUser.setFullName(user.getFullName());
//         createdUser.setRole(user.getRole());
//         createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
//         User savedUser = userRepository.save(createdUser);

//         // Create a cart for the user
//         Cart cart = new Cart();
//         cart.setCustomer(savedUser);
//         cartRepository.save(cart);

//         // Authenticate user
//         Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
//         SecurityContextHolder.getContext().setAuthentication(authentication);

//         // Generate JWT
//         String jwt = jwtProvider.generateToken(authentication);

//         // Prepare response
//         AuthResponse authResponse = new AuthResponse();
//         authResponse.setJwt(jwt);
//         authResponse.setMessage("Register success");
//         authResponse.setRole(savedUser.getRole());

//         return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
//     }

//     @PostMapping("/signin")
//     public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req) {

//         String username = req.getEmail();
//         String password = req.getPassword();

//         Authentication authentication = authenticate(username, password);

//         Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//         String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
//         String jwt = jwtProvider.generateToken(authentication);

//         // Prepare response
//         AuthResponse authResponse = new AuthResponse();
//         authResponse.setJwt(jwt);
//         authResponse.setMessage("Login success");
//         authResponse.setRole(USER_ROLE.valueOf(role));

//         return new ResponseEntity<>(authResponse, HttpStatus.OK);
//     }

//     private Authentication authenticate(String username, String password) {
//         UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

//         if (userDetails == null) {
//             throw new BadCredentialsException("Invalid Username");
//         }
//         if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//             throw new BadCredentialsException("Invalid password");
//         }

//         return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//     }
// }



package com.controller;  // Make sure this matches your other package structure

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.milind.config.JwtProvider;
import com.milind.model.Cart;
import com.milind.model.USER_ROLE;
import com.milind.model.User;
import com.repository.CartRepository;
import com.repository.UserRepository;
import com.request.LoginRequest;
import com.response.AuthResponse;
import com.service.CustomerUserDetailsService;
@CrossOrigin(origins = "http://localhost:5173")

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/signup")
    // public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) {
    public ResponseEntity<?> signup(@RequestBody User user) {

        // Check if the email is already registered
        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if (isEmailExist != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already used with another account");
        }

        // Save user with encrypted password
        User createdUser = new User();
        createdUser.setEmail(user.getEmail());
        createdUser.setFullName(user.getFullName());
        createdUser.setRole(user.getRole());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(createdUser);

        // Create a cart for the user
        Cart cart = new Cart();
        cart.setCustomer(savedUser);
        cartRepository.save(cart);

        // Authenticate user
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT
        String jwt = jwtProvider.generateToken(authentication);

        // Prepare response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Register success");
        authResponse.setRole(savedUser.getRole());

        // return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
        return ResponseEntity.ok("User registered successfully");

    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req) {

        String username = req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(username, password);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();
        String jwt = jwtProvider.generateToken(authentication);

        // Prepare response
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login success");
        authResponse.setRole(USER_ROLE.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
