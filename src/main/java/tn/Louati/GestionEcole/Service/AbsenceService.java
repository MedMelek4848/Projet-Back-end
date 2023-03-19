package tn.Louati.GestionEcole.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.Louati.GestionEcole.Repository.AbsenceRepository;
import tn.Louati.GestionEcole.Repository.EleveRepository;
import tn.Louati.GestionEcole.model.Absence;
import tn.Louati.GestionEcole.model.Eleve1;
import tn.Louati.GestionEcole.model.Matiere;

@Service
@Transactional
public class AbsenceService {
	@Autowired
	private AbsenceRepository absenceRepository;
	@Autowired
	private EleveRepository eleveRepository;
	
	public Eleve1 ajouterAbsences(Long idEleve, Matiere matiere, Date dateAbsence) {
	    Optional<Eleve1> optionalEleve = eleveRepository.findById(idEleve);
	    if (optionalEleve.isPresent()) {
	        Eleve1 eleve = optionalEleve.get();
	        eleve.ajouterAbsences(matiere, dateAbsence);
	        eleveRepository.save(eleve);
	        return eleve;
	    } else {
	        return null;
	    }
	}


  /*// méthode pour ajouter une absence
    public Absence addAbsence(Absence absence) {
        return absenceRepository.save(absence);
    }
    public void createAbsenceByeleveAndmatiere(Date date,Long idEleve,Long idMatiere) {
         absenceRepository.createAbsenceByeleveAndmatiere(date,idEleve,idMatiere);
    }*/ 
   /* // méthode pour récupérer une liste d'absences par matière
    public List<Absence> getAbsencesByMatiereId(Long matiereId) {
        return absenceRepository.findBymatiere(matiereId);
    }*/
    
    /*// méthode pour récupérer une liste d'absences par élève
    public List<Absence> getAbsencesByEleveId(Long eleveId) {
        return absenceRepository.findByEleveId(eleveId);
    }*/
    
    // méthode pour récupérer une absence par ID
    public Optional<Absence> getAbsenceById(Long absenceId) {
        return absenceRepository.findById(absenceId);
    }

}
