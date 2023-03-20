package tn.Louati.GestionEcole.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import tn.Louati.GestionEcole.Repository.AbsenceRepository;
import tn.Louati.GestionEcole.model.Absence;

@Service
@Transactional
public class AbsenceService {
	
	@Autowired
	AbsenceRepository absenceRepository;
	
	public List<Absence> getAllAbsence(){
		return (List<Absence>) absenceRepository.findAll();
	}
	public Absence addAbsence(Absence absence) {
		return absenceRepository.save(absence);
	}
}
