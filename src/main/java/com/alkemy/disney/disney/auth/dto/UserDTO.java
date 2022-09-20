package com.alkemy.disney.disney.auth.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDTO {
    @NotNull
    @Size(min = 1, max = 256)
    @Pattern(regexp="^.+@.+\\..+$", message = "Please try with a correct email")
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;
}
