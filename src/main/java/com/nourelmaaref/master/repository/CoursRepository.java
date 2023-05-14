package com.nourelmaaref.master.repository;

import com.nourelmaaref.master.domain.Cours;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Cours entity.
 */
@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {
    default Optional<Cours> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Cours> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Cours> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct cours from Cours cours left join fetch cours.enseignerPar left join fetch cours.pourLaClasse left join fetch cours.nomMatiere",
        countQuery = "select count(distinct cours) from Cours cours"
    )
    Page<Cours> findAllWithToOneRelationships(Pageable pageable);

    @Query(
        "select distinct cours from Cours cours left join fetch cours.enseignerPar left join fetch cours.pourLaClasse left join fetch cours.nomMatiere"
    )
    List<Cours> findAllWithToOneRelationships();

    @Query(
        "select cours from Cours cours left join fetch cours.enseignerPar left join fetch cours.pourLaClasse left join fetch cours.nomMatiere where cours.id =:id"
    )
    Optional<Cours> findOneWithToOneRelationships(@Param("id") Long id);
}
