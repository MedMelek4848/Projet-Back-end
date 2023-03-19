package tn.Louati.GestionEcole.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.GroupeService;
import tn.Louati.GestionEcole.model.Eleve1;
import tn.Louati.GestionEcole.model.Groupe;

@RestController
@RequestMapping(value="/api")
public class GroupeController {
	@Autowired
	GroupeService groupeservice;
	@PostMapping("/groupes")
	public Groupe addGroupe(@RequestBody Groupe groupe)   {
		groupeservice.addGroupe(groupe);
		return groupe;
	}
	@DeleteMapping("/groupes/{id}")
	public void deleteGroupe(@PathVariable(value = "id") Long GroupeId) {
		groupeservice.deleteGroupe(GroupeId);
	}
	@GetMapping("/groupes/{id}/eleves")
	public ResponseEntity<List<Eleve1>> getAllElevesByGroupeId(@PathVariable Long id) {
	    List<Eleve1> eleves = groupeservice.getAllElevesByGroupeId(id);
	    return new ResponseEntity<>(eleves, HttpStatus.OK);
	}

	@PostMapping("groupes/{idGroupe}/eleves")
	public ResponseEntity<Groupe> ajouterEleveAuGroupe(@PathVariable Long idGroupe, @RequestBody Eleve1 eleve) {
	    Groupe groupe = groupeservice.ajouterEleve(idGroupe, eleve);
	    if (groupe != null) {
	        return ResponseEntity.ok(groupe);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}

}
