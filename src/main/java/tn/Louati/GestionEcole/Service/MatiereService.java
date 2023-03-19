package tn.Louati.GestionEcole.Service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Louati.GestionEcole.Repository.MatiereRepository;
import tn.Louati.GestionEcole.model.Matiere;
@Service
@Transactional
public class MatiereService {
	@Autowired
	private MatiereRepository matiereRepository;


	public Matiere addMatiere(Matiere matiere) {
		matiereRepository.save(matiere);
		return matiere;
	}

	public void deleteMatiere(Long MatiereId) {
		matiereRepository.deleteById(MatiereId);
	}
}
