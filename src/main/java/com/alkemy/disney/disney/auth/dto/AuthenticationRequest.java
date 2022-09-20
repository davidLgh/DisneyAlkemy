package com.alkemy.disney.disney.auth.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AuthenticationRequest {

    private String username;
    private String password;
}
