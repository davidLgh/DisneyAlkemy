package com.alkemy.disney.disney.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "characters")
@Getter
@Setter
@SQLDelete(sql= "UPDATE characters SET deleted =true WHERE id=?")
@Where(clause = "deleted =false")

public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String img;
    @NotNull(message = "Debe ingresar un nombre")
    private String name;
    private String age;
    private String weight; //kg
    @Size(max = 500)
    private String history;
    private boolean deleted = Boolean.FALSE;

    @ManyToMany (mappedBy = "characters", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<MovieEntity> movies = new HashSet<>();

}
