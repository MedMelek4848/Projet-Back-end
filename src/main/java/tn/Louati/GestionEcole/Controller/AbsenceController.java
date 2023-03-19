package tn.Louati.GestionEcole.Controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Service.AbsenceService;
import tn.Louati.GestionEcole.model.Absence;
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/api/absence")
public class AbsenceController {
    
    @Autowired
    private AbsenceService absenceService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Absence> addAbsence(@RequestBody Absence absence) {
    	System.out.println(absence);
    	Absence createdAbsence = absenceService.addAbsence(absence);
        
        return ResponseEntity.ok(createdAbsence);
        
    }
    
/*
    @PostMapping
    public ResponseEntity<Void> createAbsence(@RequestParam("date") Date date, @RequestParam("idEleve") Long idEleve , @RequestParam("idMatiere") Long idMatiere) {
    	absenceService.createAbsenceByeleveAndmatiere(date, idEleve, idMatiere);
        return ResponseEntity.ok().build();
    }
   */ 
    @GetMapping("/matieres/{matiereId}")
    public ResponseEntity<List<Absence>> getAbsencesByMatiereId(@PathVariable Long matiereId) {
        List<Absence> absences = absenceService.getAbsencesByMatiereId(matiereId);
        return ResponseEntity.ok(absences);
    }
    
    /*@GetMapping("/eleves/{eleveId}")
    public ResponseEntity<List<Absence>> getAbsencesByEleveId(@PathVariable Long eleveId) {
        List<Absence> absences = absenceService.getAbsencesByEleveId(eleveId);
        return ResponseEntity.ok(absences);
    }*/
    
    @GetMapping("/{absenceId}")
    public ResponseEntity<Optional<Absence>> getAbsenceById(@PathVariable Long absenceId) {
        Optional<Absence> absence = absenceService.getAbsenceById(absenceId);
        if (absence.isPresent()) {
            return ResponseEntity.ok(absence);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
  
}
