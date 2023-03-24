package tn.Louati.GestionEcole.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.ExamenService;
import tn.Louati.GestionEcole.Service.MatiereService;
import tn.Louati.GestionEcole.model.Examen;
import tn.Louati.GestionEcole.model.Matiere;

@RestController
@RequestMapping("/api")
public class ExamenController {

    @Autowired
    private ExamenService examenService;
    
    @Autowired
    private MatiereService matiereService;

    
    @PostMapping("/examens")
	public Examen addExamen(@RequestBody Examen examen)   {
    	examenService.addExamen(examen);
		return examen;
	}
	
    
    
    // Récupérer tous les examens d'une matière
    @GetMapping("examens/matieres/{matiereId}")
    public ResponseEntity<List<Examen>> getExamensByMatiereId(@PathVariable Long matiereId) {
        List<Examen> examens = examenService.getExamensByMatiereId(matiereId);
        if (examens.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(examens, HttpStatus.OK);
    }

    // Récupérer un examen par son identifiant
    @GetMapping("examen/{examenId}")
    public ResponseEntity<Examen> getExamenById(@PathVariable Long examenId) {
        Optional<Examen> examen = examenService.getExamenById(examenId);
        if (examen.isPresent()) {
            return new ResponseEntity<>(examen.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Ajouter un examen à une matière
    @PostMapping("examens/matieres/{matiereId}")
    public ResponseEntity<Examen> ajouterExamenAMatiere(@PathVariable Long idmatiere, @RequestBody Examen examen) {
    	Matiere matiere = matiereService.getMatiereById(idmatiere);
    	if (matiere != null) {
    	    Examen examenAjoute = examenService.ajouterExamenAMatiere(examen, matiere);
    	    return new ResponseEntity<>(examenAjoute, HttpStatus.CREATED);
    	} else {
    	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }

}
