package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTObasic;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.dto.MovieDTObasic;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MovieMapper {
    @Autowired
    @Lazy
    private CharacterMapper characterMapper;
    @Lazy
    @Autowired
    private CharacterService characterService;
    @Lazy
    @Autowired
    private CharacterRepository characterRepository;

    public MovieEntity movieDtoToEntity(MovieDTO dto, boolean loadCharacters) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImg(dto.getImg());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setPremiereDate(dto.getPremiereDate());
        movieEntity.setScore(dto.getScore());
        movieEntity.setGenreId(dto.getGenreId());
        if (loadCharacters) {
            Set<CharacterEntity> characterEntityList = this.characterMapper.characterDtoListToEntities(dto.getCharacters());
            movieEntity.setCharacters(new HashSet<>(characterEntityList));
        }
        return movieEntity;
    }

    public MovieDTO movieEntityToDTO(MovieEntity entity, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImg(entity.getImg());
        dto.setTitle(entity.getTitle());
        dto.setPremiereDate(entity.getPremiereDate());
        dto.setScore(entity.getScore());
        dto.setGenreId(entity.getGenreId());
        if (loadCharacters) {
            Set<CharacterDTObasic> characterDtoList = this.characterMapper.characterEntityListToDtoList(entity.getCharacters(), false);
            dto.setCharacters(new HashSet(characterDtoList));
        }
        return dto;
    }

    public MovieDTObasic movieEntityToDTObasic(MovieEntity entity, boolean loadCharacters) {
        MovieDTObasic dto = new MovieDTObasic();
        dto.setImg(entity.getImg());
        dto.setTitle(entity.getTitle());
        dto.setPremiereDate(entity.getPremiereDate());
        return dto;
    }

    public Set<MovieDTObasic> movieEntityListToDto(Set<MovieEntity> entities, boolean loadCharacters) {
        Set<MovieDTObasic> dtos = new HashSet<>();

        for (MovieEntity entity : entities) {
            dtos.add(movieEntityToDTObasic(entity, loadCharacters));
        }
        return dtos;
    }

    public MovieEntity refreshEntity(Optional<MovieEntity> entityToSet, MovieDTO movie) {
        MovieEntity entity = entityToSet.get();
        entity.setTitle(movie.getTitle());
        entity.setScore(movie.getScore());
        entity.setImg(movie.getImg());
        entity.setPremiereDate(movie.getPremiereDate());
        entity.setGenreId(movie.getGenreId());

        //Update del personaje o creacion si no existe
/*        Set <CharacterEntity> movieCharacters = movie.getCharacters();
         for (CharacterEntity character : movieCharacters){
             if (character.getId() != null){
                 CharacterDTO dto = characterService.update(character.getId(), characterMapper.characterEntityToDto(character, false));
                 movieCharacters.add(characterMapper.characterDtoToEntity(dto));
             }else {
                 CharacterDTO newCharacter = characterMapper.characterEntityToDto(character, false);
                 characterService.save(newCharacter);
             }
         }

         entity.setCharacters(movieCharacters);
*/
        return entity;
    }

    public MovieEntity movieDtoBasicToEntity(MovieDTObasic dto) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImg(dto.getImg());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setPremiereDate(dto.getPremiereDate());
        return movieEntity;
    }

    public List<MovieEntity> movieDtoListToEntity(List<MovieDTObasic> movies) {
        List<MovieEntity> entities = new ArrayList<>();

        for (MovieDTObasic movie : movies) {
            entities.add(movieDtoBasicToEntity(movie));
        }
        return entities;
    }

    public MovieEntity setNewCharacters(Optional<MovieEntity> entityToSet, MovieDTO movieCharactersDto) {
        MovieEntity entity = entityToSet.get();
        Set<CharacterEntity> characterNewEntities = movieCharactersDto.getCharacters();
        Set<CharacterEntity> characterEntities = entity.getCharacters();
        for (CharacterEntity character : characterEntities) {
            characterNewEntities.add(character);
        }
        for (CharacterEntity character : characterNewEntities) {
            CharacterDTO newCharacter = characterMapper.characterEntityToDto(character, false);
            characterService.save(newCharacter);
        }
        entity.setCharacters(characterNewEntities);
        return entity;
    }

}
