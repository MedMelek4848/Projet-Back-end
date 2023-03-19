package tn.Louati.GestionEcole.Service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 import tn.Louati.GestionEcole.Repository.InscriptionRepository;
import tn.Louati.GestionEcole.model.Inscription;

@Service
@Transactional
public class InscriptionService {
	@Autowired
	private InscriptionRepository inscriptionRepository;


	public Inscription addInscription(Inscription inscription) {
		inscriptionRepository.save(inscription);
		return inscription;
	}

	public void deleteInscription(Long InscriptionId) {
		inscriptionRepository.deleteById(InscriptionId);
	}
}
