package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class GenreMapper {
    public GenreEntity genreDtoToEntity(GenreDTO dto){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImg(dto.getImg());
        genreEntity.setName(dto.getName());
        return genreEntity;
    }

    public GenreDTO genreEntityToDto(GenreEntity entity){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setImg(entity.getImg());
        dto.setName(entity.getName());
        return dto;
    }

    public Set<GenreDTO> genreEntityListToDTOList(Set<GenreEntity> entities) {
        Set<GenreDTO> dtos = new HashSet<>();
        for (GenreEntity entity : entities){
            dtos.add(genreEntityToDto(entity));
        }
        return dtos;

    }
}
