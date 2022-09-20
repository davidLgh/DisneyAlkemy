package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Getter
@Setter
@SQLDelete(sql= "UPDATE movies SET deleted =true WHERE id=?")
@Where(clause = "deleted =false")

public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @NotNull(message = "Debe ingresar al menos una imagen.")
    private String img;

    @NotNull (message = "La película debe tener un nombre.")
    @Size(max = 150)
    private String title;

    @Column (name = "premiere_date")
    @NotNull
    @DateTimeFormat (pattern = "yyyy/MM/dd")
    private LocalDate premiereDate;
    
    @NotNull
    private float score;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private GenreEntity genre;

    @Column (name = "genre_id", nullable = false)
    private Long genreId;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "characters_movies",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<CharacterEntity> characters = new HashSet<>();

    private boolean deleted = Boolean.FALSE;

}
