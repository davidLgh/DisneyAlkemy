package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class MovieDTObasic {
    private String img;
    private String title;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate premiereDate;
}
