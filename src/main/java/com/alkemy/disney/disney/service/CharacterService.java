package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTObasic;
import com.alkemy.disney.disney.dto.MovieDTO;

import java.util.Set;

public interface CharacterService {

    Set<CharacterDTObasic> getAllcharacters();

    CharacterDTO getCharacterDetails(Long id);

    CharacterDTO save(CharacterDTO character);

    CharacterDTO update(Long id, CharacterDTO character);

    void delete(Long id);


    Set<CharacterDTObasic> getAllByFilter(String name, String age, String weight, Set<Long> idMovies);
}
