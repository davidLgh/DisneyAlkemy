package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterDTObasic;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @GetMapping
    public ResponseEntity<Set<CharacterDTObasic>> getAllBasicByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) String weight,
            @RequestParam(required = false) Set<Long> idMovies
    ) {
        Set<CharacterDTObasic> charactersBasic = characterService.getAllByFilter(name, age, weight, idMovies);
        return ResponseEntity.ok().body(charactersBasic);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getDetailById(@PathVariable Long id) {
        CharacterDTO characterDetail = characterService.getCharacterDetails(id);
        return ResponseEntity.ok().body(characterDetail);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        CharacterDTO characterSaved = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(
            @PathVariable Long id,
            @RequestBody CharacterDTO character) {
        CharacterDTO result = characterService.update(id, character);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
