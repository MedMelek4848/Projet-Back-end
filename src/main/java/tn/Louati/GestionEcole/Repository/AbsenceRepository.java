package tn.Louati.GestionEcole.Repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.persistence.NamedNativeQuery;
import jakarta.transaction.Transactional;
import tn.Louati.GestionEcole.model.Absence;
import tn.Louati.GestionEcole.model.Eleve1;
@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long>{
	/* @Query("SELECT a FROM Absence a WHERE a.matiere.idmatiere = :matiereId")
	List<Absence> findBymatiere(@Param("matiereId") Long matiereId);
	
	
@Modifying
	    @Transactional
	    @Query(value = "INSERT INTO Absence(date_absence,eleve_id, idmatiere) VALUES (:date, :idEleve ,:idMatiere)")
	    void createAbsenceByeleveAndmatiere(@Param("date") Date date, @Param("idEleve") Long idEleve , @Param("idMatiere") Long idMatiere);
*/}




