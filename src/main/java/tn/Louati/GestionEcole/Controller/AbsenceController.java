package tn.Louati.GestionEcole.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.AbsenceService;
import tn.Louati.GestionEcole.model.Absence;

@RestController
@RequestMapping(value = "/api")
public class AbsenceController {
	@Autowired
	AbsenceService absenceService;

	@GetMapping("/absences")
	public List<Absence> getAllAbsence() {
		return absenceService.getAllAbsence();
	}
	
	@PostMapping("/absence")
	public Absence addAbsence(@RequestBody Absence absence) {
		return absenceService.addAbsence(absence);
	}
	
	@GetMapping("/absence/eleve/{ideleve}")
	public List<Absence> getAllAbsenceByIdElever(@PathVariable("ideleve") Long ideleve) {
		List<Absence> absences = absenceService.getAllAbsence();
		List<Absence> absenceById = new ArrayList<Absence>();
		for (int i = 0; i < absences.size(); i++) {
			if (absences.get(i).getEleve1() != null &&absences.get(i).getEleve1().getIdeleve() == ideleve) {
				absenceById.add(absences.get(i));
			}
		}
		return absenceById;
	}
	
	@GetMapping("/absence/matiere/{idmatiere}")
	public List<Absence> getAllAbsenceByIdMatiere(@PathVariable("idmatiere") Long idmatiere) {
		List<Absence> absences = absenceService.getAllAbsence();
		List<Absence> absenceById = new ArrayList<Absence>();
		for (int i = 0; i < absences.size(); i++) {
			if (absences.get(i).getMatiere() != null &&absences.get(i).getMatiere().getIdmatiere() == idmatiere) {
				absenceById.add(absences.get(i));
			}
		}
		return absenceById;
	}
	
	@GetMapping("/absence/{idmatiere}/{ideleve}")
	public List<Absence> getAllAbsenceByIdMatiereAndIdEleve(@PathVariable("idmatiere") Long idmatiere,@PathVariable("ideleve") Long ideleve) {
		List<Absence> absences = absenceService.getAllAbsence();
		List<Absence> absenceById = new ArrayList<Absence>();
		for (int i = 0; i < absences.size(); i++) {
			if (absences.get(i).getMatiere() != null &&absences.get(i).getMatiere().getIdmatiere() == idmatiere) {
				if (absences.get(i).getEleve1() != null &&absences.get(i).getEleve1().getIdeleve() == ideleve) {
						absenceById.add(absences.get(i));
				}
			}
		}
		return absenceById;
	}
}
