package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTObasic;
import com.alkemy.disney.disney.dto.MovieDTObasic;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private MovieService movieService;

    public CharacterEntity characterDtoToEntity(CharacterDTObasic dto){
        CharacterEntity characterEntity= new CharacterEntity();
        characterEntity.setName(dto.getName());
        characterEntity.setImg(dto.getImg());

        return characterEntity;
    }
    public CharacterDTObasic characterBasicEntityToDto(CharacterEntity entity, boolean loadMovies) {
        CharacterDTObasic dtos = new CharacterDTObasic();
        dtos.setName(entity.getName());
        dtos.setImg(entity.getImg());
        if (loadMovies){
            Set<MovieDTObasic> moviesDTO = new HashSet<>(movieMapper.movieEntityListToDto(entity.getMovies(), false));
            dtos.setMovies(moviesDTO);
        }
        return dtos;
    }

//To use in GET ALL
    public Set<CharacterDTObasic> characterEntityListToDtoList(Set<CharacterEntity> entities, boolean loadMovies) {
        Set<CharacterDTObasic> dtos = new HashSet<>();
        for (CharacterEntity entity : entities){
            dtos.add(characterBasicEntityToDto(entity, loadMovies));
        }
        return dtos;
    }

//To use in POST
    public CharacterEntity characterDtoToEntity(CharacterDTO dto){
        CharacterEntity entity = new CharacterEntity();
        entity.setImg(dto.getImg());
        entity.setName(dto.getName());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setHistory(dto.getHistory());
       // entity.setMovies(movieService.getMovieById()));
        return entity;
    }
//To use in GET / UPDATE
    public CharacterDTO characterEntityToDto(CharacterEntity entity, boolean loadMovies) {
        CharacterDTO dtos = new CharacterDTO();
        dtos.setId(entity.getId());
        dtos.setImg(entity.getImg());
        dtos.setName(entity.getName());
        dtos.setAge(entity.getAge());
        dtos.setHistory(entity.getHistory());
        dtos.setWeight(entity.getWeight());

        //TODO: crear un boolean que frene el bucle de traer la lista de movies con characters
        if (loadMovies){
            Set<MovieDTObasic> movieDTOS = new HashSet<>(movieMapper.movieEntityListToDto(entity.getMovies(), false));
            dtos.setMovies(movieDTOS);
        }

        return dtos;
    }


    public CharacterEntity characterSetEntity(Optional<CharacterEntity> entityToSet, CharacterDTO dto) {
        CharacterEntity entity = entityToSet.get();
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setName(dto.getName());
        entity.setImg(dto.getImg());
        entity.setHistory(dto.getHistory());
        //entity.setMovies(entity.getMovies());
        return entity;
    }

    public Set<CharacterEntity> characterDtoListToEntities(Set<CharacterEntity> characters) {
        Set <CharacterEntity> entities = new HashSet<>();
        for (CharacterEntity character : characters) {
            entities.add(character);
        }
        return entities ;
    }
}
