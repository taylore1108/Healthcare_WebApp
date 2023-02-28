package com.team11.hhs.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    public String userName;
    public String password;

    public UserDTO(String userName, String password){
        this.setPassword(password);
        this.setUserName(userName);
    }
}
