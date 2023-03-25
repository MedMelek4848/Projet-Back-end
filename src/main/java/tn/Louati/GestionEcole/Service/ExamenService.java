package tn.Louati.GestionEcole.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.Louati.GestionEcole.Repository.ExamenRepository;
import tn.Louati.GestionEcole.model.Examen;

@Service
@Transactional
public class ExamenService {

	@Autowired
	ExamenRepository examenRepository;

	public List<Examen> getAllExamen() {
		return (List<Examen>) examenRepository.findAll();
	}

	public Examen addExamen(Examen examen) {
		return examenRepository.save(examen);
	}

	public Examen getExamenById(Long idexamen) {
		return examenRepository.findById(idexamen).get();
	}
}
