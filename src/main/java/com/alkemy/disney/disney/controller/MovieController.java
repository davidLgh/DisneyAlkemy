package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieDTObasic;
import com.alkemy.disney.disney.exception.BadRequestException;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    //get all movieBasic by filter
    @GetMapping
    public ResponseEntity<Set<MovieDTObasic>> getAllBasicByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "0") String genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        Set<MovieDTObasic> moviesBasic = movieService.getAllByFilter(name, genre, order);
        return ResponseEntity.ok().body(moviesBasic);
    }

    //get movieDetails by id
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieDetails(@PathVariable Long id) {
        MovieDTO movieDetails = movieService.getMovieById(id);
        return ResponseEntity.ok().body(movieDetails);
    }

    //create new movie with characters
    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movie) {
        MovieDTO movieSaved = movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @Valid @RequestBody MovieDTO movie) {
        MovieDTO movieDTO = movieService.update(id, movie);
        return ResponseEntity.ok().body(movieDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/characters")
    public ResponseEntity<MovieDTO> saveNewMovieCharacter(@PathVariable Long id, @RequestBody MovieDTO movie) {
        MovieDTO movieDTO = movieService.updateCharacters(id, movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDTO);
    }

    @DeleteMapping("/{id}/characters/{idCharacter}")
    public ResponseEntity<MovieDTO> deleteMovieCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {
        MovieDTO movieDTO = movieService.deleteCharacter(id, idCharacter);
        return ResponseEntity.ok().body(movieDTO);
    }

}
