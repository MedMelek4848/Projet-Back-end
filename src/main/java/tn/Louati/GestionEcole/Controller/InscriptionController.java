package tn.Louati.GestionEcole.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.InscriptionService;
import tn.Louati.GestionEcole.model.Inscription;

@RestController
@RequestMapping(value="/api")
public class InscriptionController {
	@Autowired
	InscriptionService inscriptionservice;
	@PostMapping("/inscriptions")
	public Inscription addInscription(@RequestBody Inscription inscription)   {
		inscriptionservice.addInscription(inscription);
		return inscription;
	}
	@DeleteMapping("/inscriptions/{id}")
	public void deleteInscription(@PathVariable(value = "id") Long inscriptionId) {
		inscriptionservice.deleteInscription(inscriptionId);
	}
}
