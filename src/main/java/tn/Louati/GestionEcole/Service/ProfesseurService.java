package tn.Louati.GestionEcole.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import tn.Louati.GestionEcole.Repository.ProfesseurRepository;
import tn.Louati.GestionEcole.model.Groupe;
import tn.Louati.GestionEcole.model.Matiere;
import tn.Louati.GestionEcole.model.Professeur;

@Service
public class ProfesseurService {

    @Autowired
    private ProfesseurRepository professeurRepository;

 

    public Professeur addProfesseur(Professeur professeur) {
        return professeurRepository.save(professeur);
    }

    public Professeur getProfesseurById(Long id) {
        Optional<Professeur> professeur = professeurRepository.findById(id);
        if (professeur.isPresent()) {
            return professeur.get();
        }
        throw new EntityNotFoundException("Professeur with id " + id + " not found.");
    }

    public Professeur addMatiere(Long idProfesseur, Long idMatiere) {
        Professeur professeur = professeurRepository.findById(idProfesseur).orElse(null);
        if (professeur != null) {
            Matiere matiere = new Matiere();
            matiere.setIdmatiere(idMatiere);
            professeur.addMatiere(matiere);
            return professeurRepository.save(professeur);
        }
        return null;
    }

    public Professeur addGroupe(Long idProfesseur, Long idGroupe) {
        Professeur professeur = professeurRepository.findById(idProfesseur).orElse(null);
        if (professeur != null) {
            Groupe groupe = new Groupe();
            groupe.setIdGroupe(idGroupe);
            professeur.addGroupe(groupe);
            return professeurRepository.save(professeur);
        }
        return null;
    }

}
