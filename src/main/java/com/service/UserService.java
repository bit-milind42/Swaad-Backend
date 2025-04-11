
package com.service;

import com.milind.model.User;

public interface UserService {
    
    User findUserByJwtToken(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

}
