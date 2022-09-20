package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.CharacterEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieDTO {
    private Long id;
    private String img;
    private String title;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate premiereDate;
    private float score;
    private Long genreId;
    private Set <CharacterEntity> characters;


}
