package tn.Louati.GestionEcole.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Examen;

@Repository
public interface ExamenRepository extends JpaRepository<Examen, Long> {

}
