package tn.Louati.GestionEcole.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("/matiere/{idmatiere}")
	public Matiere getMatiereById(@PathVariable(value = "idmatiere") Long idmatiere) {
		return matiereService.getMatiereById(idmatiere);
	}
	@GetMapping("/matieres")
	public ArrayList<Matiere> getAllMatiere() {
		return  matiereService.getAllMatiere();
		}
	@PostMapping("/matiere")
	public Matiere addMaitere(@RequestBody Matiere matiere) {
		return matiereService.addMatiere(matiere);
	}

}
