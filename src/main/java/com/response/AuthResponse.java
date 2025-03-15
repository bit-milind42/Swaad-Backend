// package com.response;

// import com.milind.model.USER_ROLE;

// public class AuthResponse {
//     private String jwt;
//     private String message;
//     private USER_ROLE role;
// }

package com.response;

import com.milind.model.USER_ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
