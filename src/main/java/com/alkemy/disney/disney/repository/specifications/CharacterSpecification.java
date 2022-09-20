package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.CharacterFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.HashSet;
import java.util.Set;

@Component
public class CharacterSpecification {
    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO characterFiltersDTO){
        return (root, query, criteriaBuilder) -> {
            Set<Predicate> predicates = new HashSet<>();

            if(StringUtils.hasLength(characterFiltersDTO.getAge())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("age")),
                                "%" + characterFiltersDTO.getAge() + "%"
                        )
                );
            }

            if(StringUtils.hasLength(characterFiltersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + characterFiltersDTO.getName() + "%"
                        )
                );
            }

            if(StringUtils.hasLength(characterFiltersDTO.getWeight())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("weight")),
                                "%" + characterFiltersDTO.getWeight() + "%"
                        )
                );
            }

            if(!CollectionUtils.isEmpty(characterFiltersDTO.getIdMovies())){
                Join<MovieEntity, CharacterEntity> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(characterFiltersDTO.getIdMovies()));
            }

            //Remove duplicates
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));


        };
    }
}
