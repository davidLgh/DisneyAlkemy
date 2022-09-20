package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@Setter
public class GenreEntity {
    @OneToMany
    @JoinColumn(name = "genre_id")
    @Autowired
    public Set<MovieEntity> moviesList;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String img;

  /*  @OneToMany(targetEntity = MovieEntity , cascade = CascadeType.PERSIST, mappedBy ="genre")
    private Set<MovieEntity> moviesList;
*/
}
