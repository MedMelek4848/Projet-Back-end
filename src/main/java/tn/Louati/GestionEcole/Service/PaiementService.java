package tn.Louati.GestionEcole.Service;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Louati.GestionEcole.Repository.PaiementRepository;
import tn.Louati.GestionEcole.model.Paiement;

@Service
@Transactional
public class PaiementService {
	@Autowired
	private PaiementRepository paiementRepository;


	public Paiement addPaiement(Paiement paiement) {
		paiementRepository.save(paiement);
		return paiement;
	}

	public void deletePaiement(Long PaiementId) {
		paiementRepository.deleteById(PaiementId);
	}
}
