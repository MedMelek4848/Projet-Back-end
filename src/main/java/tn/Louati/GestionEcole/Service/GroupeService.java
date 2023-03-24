package tn.Louati.GestionEcole.Service;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Louati.GestionEcole.Repository.GroupeRepository;
import tn.Louati.GestionEcole.model.Eleve1;
import tn.Louati.GestionEcole.model.Groupe;

@Service
@Transactional
public class GroupeService {
	@Autowired
	private GroupeRepository groupeRepository;


	public Groupe addGroupe(Groupe groupe) {
		groupeRepository.save(groupe);
		return groupe;
	}

	public void deleteGroupe(Long GroupeId) {
		groupeRepository.deleteById(GroupeId);
	}
	public Groupe getGroupeById(Long idGroupe) {
		return groupeRepository.findById(idGroupe).get();
	}
	public List<Eleve1> getAllElevesByGroupeId(Long id) {
	    Optional<Groupe> optionalGroupe = groupeRepository.findById(id);
	    if (optionalGroupe.isPresent()) {
	        Groupe groupe = optionalGroupe.get();
	        return groupe.getEleves();
	    } else {
	        throw new EntityNotFoundException("Groupe with id " + id + " not found");
	    }
	}
	

	public Groupe ajouterEleve(Long idGroupe, Eleve1 eleve) {
        Optional<Groupe> optionalGroupe = groupeRepository.findById(idGroupe);
        if (optionalGroupe.isPresent()) {
            Groupe groupe = optionalGroupe.get();
            eleve.setGroupe(groupe); // association de l'élève avec le groupe
            groupe.getEleves().add(eleve); // association du groupe avec l'élève
            groupeRepository.save(groupe); // sauvegarde du groupe avec l'élève associé
            return groupe;
        } else {
            return null;
        }
    }
}
