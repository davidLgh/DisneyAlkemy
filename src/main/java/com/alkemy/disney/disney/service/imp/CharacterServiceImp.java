package com.alkemy.disney.disney.service.imp;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTObasic;
import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.specifications.CharacterSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Service
public class CharacterServiceImp implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterSpecification characterSpecification;
    @Autowired
    private CharacterMapper characterMapper;



    @Override
    public Set<CharacterDTObasic> getAllcharacters() {
        Set<CharacterEntity> entities = new HashSet<>(characterRepository.findAll());//solucion error casteo
        Set<CharacterDTObasic> result = characterMapper.characterEntityListToDtoList(entities, false);
        return result;
    }

    @Override
    public CharacterDTO getCharacterDetails(Long id) {
        Optional<CharacterEntity> entityToGet = characterRepository.findById(id);
        CharacterEntity entity = entityToGet.get();
        CharacterDTO result = characterMapper.characterEntityToDto(entity, true);
        return result;
    }

    @Override
    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.characterDtoToEntity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntityToDto(entitySaved, false);
        return result;
    }

    @Override
    public CharacterDTO update(Long id, CharacterDTO dto) {
        Optional<CharacterEntity> entityToSet = characterRepository.findById(id);
        CharacterEntity entityUpdated = characterMapper.characterSetEntity(entityToSet,dto);
        CharacterEntity entitySaved = characterRepository.save(entityUpdated);
        CharacterDTO result = characterMapper.characterEntityToDto(entitySaved, true);
        return result;
    }

    @Override
    public void delete(Long id) {
        characterRepository.deleteById(id);
    }

    @Override
    public Set<CharacterDTObasic> getAllByFilter(String name, String age, String weight, Set<Long> idMovies) {
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, weight, idMovies);
        Set <CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        Set <CharacterDTObasic> dtos = characterMapper.characterEntityListToDtoList(entities, true);
        return dtos;
    }


}
