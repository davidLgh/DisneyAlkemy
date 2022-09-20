package com.alkemy.disney.disney.service.imp;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.mapper.GenreMapper;
import com.alkemy.disney.disney.repository.GenreRepository;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GenreServiceImp implements GenreService {


    @Autowired
    private GenreRepository genreRepository;
    @Autowired
    private GenreMapper genreMapper;

    @Override
    public GenreDTO save(GenreDTO dto) {
        GenreEntity entity = genreMapper.genreDtoToEntity(dto);
        GenreEntity entitySaved = genreRepository.save(entity);
        GenreDTO result = genreMapper.genreEntityToDto(entitySaved);
        return result;
    }

    public Set<GenreDTO> getAllgenres() {
        Set<GenreEntity> entities = (Set<GenreEntity>) genreRepository.findAll();
        Set<GenreDTO> result = genreMapper.genreEntityListToDTOList(entities);
        return result;
    }
}
