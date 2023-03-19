package tn.Louati.GestionEcole.Service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Louati.GestionEcole.Repository.EleveRepository;
import tn.Louati.GestionEcole.model.Eleve1;

@Service
@Transactional
public class EleveService {
	@Autowired
	private EleveRepository eleveRepository;


	public Eleve1 addEleve(Eleve1 e) {
		eleveRepository.save(e);
		return e;
	}

	public void deleteEleve(Long EleveId) {
		eleveRepository.deleteById(EleveId);
	}
	
	public Eleve1 getAllEleve(Long EleveId) {
		Eleve1 eleve =eleveRepository.findById(EleveId).get();
		return (eleve);
	}
}
