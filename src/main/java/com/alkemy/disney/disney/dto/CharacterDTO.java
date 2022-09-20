package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CharacterDTO {
    private Long id;
    private String img;
    private String name;
    private String age;
    private String weight; //kg
    private String history;
    private Set<MovieDTObasic> movies;
}
