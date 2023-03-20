package tn.Louati.GestionEcole.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Absence;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>{

}
