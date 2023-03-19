package tn.Louati.GestionEcole.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.PaiementService;
import tn.Louati.GestionEcole.model.Paiement;

@RestController
@RequestMapping(value="/api")
public class PaiementController {
	@Autowired
	PaiementService paiementservice;
	@PostMapping("/paiements")
	public Paiement addPaiement(@RequestBody Paiement paiement)   {
		paiementservice.addPaiement(paiement);
		return paiement;
	}
	@DeleteMapping("/paiements/{id}")
	public void deletePaiement(@PathVariable(value = "id") Long paiementId) {
		paiementservice.deletePaiement(paiementId);
	}
}
