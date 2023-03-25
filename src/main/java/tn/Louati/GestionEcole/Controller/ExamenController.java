/*package tn.Louati.GestionEcole.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.ExamenService;
import tn.Louati.GestionEcole.model.Examen;

@RestController
@RequestMapping(value="/api")
public class ExamenController {

    @Autowired
    private ExamenService examenService;
    
    @GetMapping("/examens")
	public List<Examen> getAllExamen() {
		return examenService.getAllExamen();
	}
	
	@PostMapping("/examen")
	public Examen addExamen(@RequestBody Examen examen) {
		return examenService.addExamen(examen);
	}
	@GetMapping("/examen/eleve/{ideleve}")
	public List<Examen> getAllExamenByIdElever(@PathVariable("ideleve") Long ideleve) {
		List<Examen> examens = examenService.getAllExamen();
		List<Examen> examenById = new ArrayList<Examen>();
		for (int i = 0; i < examens.size(); i++) {
			if (examens.get(i).getEleve1() != null &&examens.get(i).getEleve1().getIdeleve() == ideleve) {
				examenById.add(examens.get(i));
			}
		}
		return examenById;
	}
	
	
	@GetMapping("/examen/matiere/{idmatiere}")
	public List<Examen> getAllExamenByIdMatiere(@PathVariable("idmatiere") Long idmatiere) {
		List<Examen> examens = examenService.getAllExamen();
		List<Examen> examenById = new ArrayList<Examen>();
		for (int i = 0; i < examens.size(); i++) {
			if (examens.get(i).getMatiere() != null &&examens.get(i).getMatiere().getIdmatiere() == idmatiere) {
				examenById.add(examens.get(i));
			}
		}
		return examenById;
	}
	
	@GetMapping("/examen/{idmatiere}/{idgroupe}")
	public List<Examen> getAllExamenByIdMatiereAndIdEleve(@PathVariable("idmatiere") Long idmatiere,@PathVariable("ideleve") Long ideleve) {
		List<Examen> examens = examenService.getAllExamen();
		List<Examen> examenById = new ArrayList<Examen>();
		for (int i = 0; i < examens.size(); i++) {
			if (examens.get(i).getMatiere() != null &&examens.get(i).getMatiere().getIdmatiere() == idmatiere) {
				if (examens.get(i).getEleve1() != null &&examens.get(i).getEleve1().getIdeleve() == ideleve) {
					examenById.add(examens.get(i));
				}
			}
		}
		return examenById;
	}
}*/
