package com.nourelmaaref.master.repository;

import com.nourelmaaref.master.domain.Professeur;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ProfesseurRepositoryWithBagRelationshipsImpl implements ProfesseurRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Professeur> fetchBagRelationships(Optional<Professeur> professeur) {
        return professeur.map(this::fetchNomClasses).map(this::fetchNomMatieres);
    }

    @Override
    public Page<Professeur> fetchBagRelationships(Page<Professeur> professeurs) {
        return new PageImpl<>(fetchBagRelationships(professeurs.getContent()), professeurs.getPageable(), professeurs.getTotalElements());
    }

    @Override
    public List<Professeur> fetchBagRelationships(List<Professeur> professeurs) {
        return Optional.of(professeurs).map(this::fetchNomClasses).map(this::fetchNomMatieres).orElse(Collections.emptyList());
    }

    Professeur fetchNomClasses(Professeur result) {
        return entityManager
            .createQuery(
                "select professeur from Professeur professeur left join fetch professeur.nomClasses where professeur is :professeur",
                Professeur.class
            )
            .setParameter("professeur", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Professeur> fetchNomClasses(List<Professeur> professeurs) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, professeurs.size()).forEach(index -> order.put(professeurs.get(index).getId(), index));
        List<Professeur> result = entityManager
            .createQuery(
                "select distinct professeur from Professeur professeur left join fetch professeur.nomClasses where professeur in :professeurs",
                Professeur.class
            )
            .setParameter("professeurs", professeurs)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Professeur fetchNomMatieres(Professeur result) {
        return entityManager
            .createQuery(
                "select professeur from Professeur professeur left join fetch professeur.nomMatieres where professeur is :professeur",
                Professeur.class
            )
            .setParameter("professeur", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Professeur> fetchNomMatieres(List<Professeur> professeurs) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, professeurs.size()).forEach(index -> order.put(professeurs.get(index).getId(), index));
        List<Professeur> result = entityManager
            .createQuery(
                "select distinct professeur from Professeur professeur left join fetch professeur.nomMatieres where professeur in :professeurs",
                Professeur.class
            )
            .setParameter("professeurs", professeurs)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
