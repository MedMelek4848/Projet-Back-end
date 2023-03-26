package tn.Louati.GestionEcole.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import tn.Louati.GestionEcole.Repository.BultainRepository;
import tn.Louati.GestionEcole.model.Bultain;

@Service
@Transactional
public class BultainService {

	@Autowired
	BultainRepository bultainRepository;

	public List<Bultain> getAllBultain() {
		return (List<Bultain>) bultainRepository.findAll();
	}

	public Bultain addBultain(Bultain bultain) {
		return bultainRepository.save(bultain);
	}
	
	public Bultain getBultainById(Long id) {
		return bultainRepository.findById(id).get();
	}
	
}
