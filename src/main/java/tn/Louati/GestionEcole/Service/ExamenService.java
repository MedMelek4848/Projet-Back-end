package tn.Louati.GestionEcole.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import tn.Louati.GestionEcole.Repository.ExamenRepository;
import tn.Louati.GestionEcole.model.Examen;
import tn.Louati.GestionEcole.model.Matiere;

@Service
public class ExamenService {

    @Autowired
    private ExamenRepository examenRepository;
    @PersistenceContext
    private EntityManager entityManager;
    // Autres injections de dépendances et méthodes de la classe

    public Examen addExamen(Examen examen) {
    	examenRepository.save(examen);
		return examen;
	}
    public List<Examen> getExamensByMatiereId(Long matiereId) {
        String jpql = "SELECT e FROM Examen e WHERE e.matiere.id = :matiereId";
        TypedQuery<Examen> query = entityManager.createQuery(jpql, Examen.class);
        query.setParameter("matiereId", matiereId);
        return query.getResultList();
    }
    public Optional<Examen> getExamenById(Long examenId) {
        return examenRepository.findById(examenId);
    }
    
    public Examen ajouterExamenAMatiere(Examen examen, Matiere matiere) {
        examen.setMatiere(matiere);
        return examenRepository.save(examen);
    }
    
    // Autres méthodes de la classe
}
