package tn.Louati.GestionEcole.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.Louati.GestionEcole.Service.EleveService;
import tn.Louati.GestionEcole.model.Eleve1;

@RestController
@RequestMapping(value="/api")
public class EleveController {
	@Autowired
	EleveService eleveService;
	@PostMapping("/eleves")
	public Eleve1 addEleve(@RequestBody Eleve1 e)   {
		eleveService.addEleve(e);
		return e;
	}
	@DeleteMapping("/eleves/{id}")
	public void deleteEleve(@PathVariable(value = "id") Long eleveId) {
		eleveService.deleteEleve(eleveId);
	}
	@GetMapping("/eleves/{id}")
	public Eleve1 getAllEleve(@PathVariable(value = "id")Long EleveId) {
		return eleveService.getAllEleve(EleveId);
	}
}
