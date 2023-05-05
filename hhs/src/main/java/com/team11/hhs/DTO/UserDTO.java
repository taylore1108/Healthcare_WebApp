package com.team11.hhs.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty(message = "Username should not be empty")
    private String username;
    @NotEmpty(message = "Password should not be empty")
    private String password;

//    @NotEmpty(message = "Password should not be empty")
    private String password2;

    //TODO make role variable here but with Patient defualt
    private String role = "ROLE_PATIENT";

//    public UserDTO(String userName, String password){
//        this.setPassword(password);
//        this.setUserName(userName);
//    }
}
