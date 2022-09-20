package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.MovieEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class GenreDTO {
    private Long id;
    private String name;
    private String img;
    private Set<MovieEntity> moviesList;
}
