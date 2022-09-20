package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GenreDTO;

import java.util.Set;

public interface GenreService {
    GenreDTO save(GenreDTO dto);

    Set<GenreDTO> getAllgenres();
}
