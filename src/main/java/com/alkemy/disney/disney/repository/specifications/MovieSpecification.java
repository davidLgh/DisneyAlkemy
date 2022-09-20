package com.alkemy.disney.disney.repository.specifications;

import com.alkemy.disney.disney.dto.MovieFiltersDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.HashSet;
import java.util.Set;

@Component
public class MovieSpecification {
    public Specification<MovieEntity> getByFilters(MovieFiltersDTO movieFiltersDTO){
        return (root, query, criteriaBuilder) -> {
            Set<Predicate> predicates = new HashSet<>();

            if(StringUtils.hasLength(movieFiltersDTO.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("title")),
                                "%" + movieFiltersDTO.getName() + "%"
                        )
                );
            }

            if(StringUtils.hasLength(movieFiltersDTO.getGenre())){
                predicates.add(
                        criteriaBuilder.equal(root.get("genreId"),movieFiltersDTO.getGenre())
                );
            }

            //Remove duplicates
            query.distinct(true);

            //Order resolver
            String orderByField = "premiereDate";
            query.orderBy(
                    movieFiltersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                                criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
