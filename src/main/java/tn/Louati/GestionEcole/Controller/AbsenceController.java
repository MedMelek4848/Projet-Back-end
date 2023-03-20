package tn.Louati.GestionEcole.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
