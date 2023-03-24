package tn.Louati.GestionEcole.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.ProfesseurService;
import tn.Louati.GestionEcole.model.Professeur;

@RestController
@RequestMapping("/api")
public class ProfesseurController {

    @Autowired
    private ProfesseurService professeurService;

    @PostMapping("/professeurs")
    public ResponseEntity<Professeur> addProfesseur(@RequestBody Professeur professeur) {
        Professeur newProfesseur = professeurService.addProfesseur(professeur);
        return ResponseEntity.created(URI.create("/professeurs/" + newProfesseur.getIdprofesseur())).body(newProfesseur);
    }

    @PostMapping("professeurs/{idProfesseur}/matieres/{idMatiere}")
    public ResponseEntity<Professeur> addProfesseurToMatiere(@PathVariable Long idProfesseur, @PathVariable Long idMatiere) {
        Professeur professeur = professeurService.addMatiere(idProfesseur, idMatiere);
        if (professeur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professeur);
    }

    @PostMapping("professeurs/{idProfesseur}/groupes/{idGroupe}")
    public ResponseEntity<Professeur> addProfesseurToGroupe(@PathVariable Long idProfesseur, @PathVariable Long idGroupe) {
        Professeur professeur = professeurService.addGroupe(idProfesseur, idGroupe);
        if (professeur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(professeur);
    }

    
    @GetMapping("professeurs/{id}")
    public ResponseEntity<Professeur> getProfesseurById(@PathVariable Long id) {
        Professeur professeur = professeurService.getProfesseurById(id);
        return ResponseEntity.ok().body(professeur);
    }
    


}
