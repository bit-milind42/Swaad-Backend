// package com.service;

// import com.milind.model.User;
// import jdk.jshell.spi.ExecutionControl;

// public class UserService {

//     public User findUserByJwtToken(String jwt) throws Exception;

//     public User findUserByEmail(String email) throws Exception;

// }


package com.service;

import com.milind.model.User;

public interface UserService {
    
    User findUserByJwtToken(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

}
