package com.nourelmaaref.master.repository;

import com.nourelmaaref.master.domain.Matiere;
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
public class MatiereRepositoryWithBagRelationshipsImpl implements MatiereRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Matiere> fetchBagRelationships(Optional<Matiere> matiere) {
        return matiere.map(this::fetchCins);
    }

    @Override
    public Page<Matiere> fetchBagRelationships(Page<Matiere> matieres) {
        return new PageImpl<>(fetchBagRelationships(matieres.getContent()), matieres.getPageable(), matieres.getTotalElements());
    }

    @Override
    public List<Matiere> fetchBagRelationships(List<Matiere> matieres) {
        return Optional.of(matieres).map(this::fetchCins).orElse(Collections.emptyList());
    }

    Matiere fetchCins(Matiere result) {
        return entityManager
            .createQuery("select matiere from Matiere matiere left join fetch matiere.cins where matiere is :matiere", Matiere.class)
            .setParameter("matiere", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Matiere> fetchCins(List<Matiere> matieres) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, matieres.size()).forEach(index -> order.put(matieres.get(index).getId(), index));
        List<Matiere> result = entityManager
            .createQuery(
                "select distinct matiere from Matiere matiere left join fetch matiere.cins where matiere in :matieres",
                Matiere.class
            )
            .setParameter("matieres", matieres)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
