package tn.Louati.GestionEcole.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import tn.Louati.GestionEcole.Repository.MatiereRepository;
import tn.Louati.GestionEcole.model.Matiere;

@Service
@Transactional
public class MatiereService {
	@Autowired
	MatiereRepository matiereRepository;
	
	public Matiere getMatiereById(Long idmatiere) {
		return matiereRepository.findById(idmatiere).get();
	}
	
	public ArrayList<Matiere> getAllMatiere() {
		return (ArrayList<Matiere>) matiereRepository.findAll();
		}
	public Matiere addMatiere(Matiere matiere) {
		return matiereRepository.save(matiere);
	}

}
