package com.nourelmaaref.master.repository;

import com.nourelmaaref.master.domain.Paiement;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Paiement entity.
 */
@Repository
public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    default Optional<Paiement> findOneWithEagerRelationships(Long id) {
        return this.findOneWithToOneRelationships(id);
    }

    default List<Paiement> findAllWithEagerRelationships() {
        return this.findAllWithToOneRelationships();
    }

    default Page<Paiement> findAllWithEagerRelationships(Pageable pageable) {
        return this.findAllWithToOneRelationships(pageable);
    }

    @Query(
        value = "select distinct paiement from Paiement paiement left join fetch paiement.matricule",
        countQuery = "select count(distinct paiement) from Paiement paiement"
    )
    Page<Paiement> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct paiement from Paiement paiement left join fetch paiement.matricule")
    List<Paiement> findAllWithToOneRelationships();

    @Query("select paiement from Paiement paiement left join fetch paiement.matricule where paiement.id =:id")
    Optional<Paiement> findOneWithToOneRelationships(@Param("id") Long id);
}
