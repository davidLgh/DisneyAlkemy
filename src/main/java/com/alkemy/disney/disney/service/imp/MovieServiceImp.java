package com.alkemy.disney.disney.service.imp;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieDTObasic;
import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.specifications.MovieSpecification;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class MovieServiceImp implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieSpecification movieSpecification;
    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public MovieDTO save(MovieDTO dto) {
        MovieEntity entity = movieMapper.movieDtoToEntity(dto, true);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntityToDTO(entitySaved, true);
        return result;
    }

    @Override
    public Set<MovieDTObasic> getAllMovies() {
        Set<MovieEntity> entities = (Set<MovieEntity>) movieRepository.findAll();
        Set<MovieDTObasic> result = movieMapper.movieEntityListToDto(entities, false);
        return result;
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        Optional<MovieEntity> entityToGet = movieRepository.findById(id);
        MovieEntity entity = entityToGet.get();
        MovieDTO result = movieMapper.movieEntityToDTO(entity, true);
        return result;
    }

    @Override
    public MovieDTO update(Long id, MovieDTO movie) {
        Optional<MovieEntity> entityToSet = movieRepository.findById(id);
        MovieEntity entityUpdated = movieMapper.refreshEntity(entityToSet, movie);
        MovieEntity entitySaved = movieRepository.save(entityUpdated);
        MovieDTO result = movieMapper.movieEntityToDTO(entitySaved, true);
        return result;
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Set<MovieDTObasic> getAllByFilter(String name, String genre, String order) {
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(name, genre, order);
        Set<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        Set<MovieDTObasic> dtos = movieMapper.movieEntityListToDto(entities, true);
        return dtos;
    }

    @Override
    public MovieDTO updateCharacters(Long id, MovieDTO movieCharactersDto) {
        Optional<MovieEntity> entityToSet = movieRepository.findById(id);
        MovieEntity entityUpdated = movieMapper.setNewCharacters(entityToSet, movieCharactersDto);
        MovieEntity entitySaved = movieRepository.save(entityUpdated);
        MovieDTO result = movieMapper.movieEntityToDTO(entitySaved, true);
        return result;
    }

    @Override
    public MovieDTO deleteCharacter(Long movieId, Long idCharacter) {
        Optional<MovieEntity> entityToSet = movieRepository.findById(movieId);
        characterRepository.deleteById(idCharacter);
        MovieEntity entity = entityToSet.get();
        MovieDTO result = movieMapper.movieEntityToDTO(entity, true);
        return result;
    }


}


