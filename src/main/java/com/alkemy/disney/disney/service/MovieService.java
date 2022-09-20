package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieDTObasic;

import java.util.Set;

public interface MovieService{

    MovieDTO save(MovieDTO dto);

    Set<MovieDTObasic> getAllMovies();

    MovieDTO getMovieById(Long id);

    MovieDTO update(Long id, MovieDTO movie);

    void delete(Long id);

    Set<MovieDTObasic> getAllByFilter(String name, String genre, String order);

    MovieDTO updateCharacters(Long id, MovieDTO movie);

    MovieDTO deleteCharacter(Long id, Long idCharacter);

}
