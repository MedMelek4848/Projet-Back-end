package tn.Louati.GestionEcole.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.MatiereService;
import tn.Louati.GestionEcole.model.Matiere;

@RestController
@RequestMapping(value="/api")
public class MatiereController {
	@Autowired
	MatiereService matiereService;
	@PostMapping("/matieres")
	public Matiere addMatiere(@RequestBody Matiere matiere)   {
		matiereService.addMatiere(matiere);
		return matiere;
	}
	@DeleteMapping("/matieres/{id}")
	public void deleteMatiere(@PathVariable(value = "id") Long matiereId) {
		matiereService.deleteMatiere(matiereId);
	}
}
