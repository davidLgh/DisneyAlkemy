package com.alkemy.disney.disney.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String jwt;

}
