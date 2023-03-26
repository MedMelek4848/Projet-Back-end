package tn.Louati.GestionEcole.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Louati.GestionEcole.Factory.AbsenceFactory;
import tn.Louati.GestionEcole.Factory.NoteFactory;
import tn.Louati.GestionEcole.Service.BultainService;
import tn.Louati.GestionEcole.dto.AbsenceDto;
import tn.Louati.GestionEcole.dto.NoteDto;
import tn.Louati.GestionEcole.model.Bultain;

@RestController
@RequestMapping(value = "/api")
public class BultainController {
	@Autowired
	BultainService bultainService;

	@GetMapping("/note/{ideleve}")
	public List<NoteDto> getNoteByIdEleve(@PathVariable("ideleve") Long ideleve) {
		List<Bultain> bultains = bultainService.getAllBultain();
		List<NoteDto> notes = new ArrayList<>();
		for (int i = 0; i < bultains.size(); i++) {
			try {
				if (bultains.get(i).getEleve1().getIdeleve() == ideleve) {
					notes.add(NoteFactory.bultainToNoteDto(bultains.get(i)));
				}
			} catch (Throwable t) {
				// TODO: handle exception
			}
		}
		return notes;
	}

	@PostMapping("/note")
	public NoteDto addorUpdateNote(@RequestBody Bultain bultain) throws Exception {
		System.out.println(bultain);
		if (bultain.getNbabsence() != 0) {
			throw new Exception("Could not give absence where give a note to student");
		}
		Bultain bultainExisting = null;
		try {
			bultainExisting = bultainService.getBultainById(bultain.getIdbultain());
		} catch (Throwable t) {
			List<Bultain> allBultains = bultainService.getAllBultain();
			for (Bultain bultain2 : allBultains) {
				try {
					if (bultain2.getMatiere().getIdmatiere() == bultain.getMatiere().getIdmatiere()
							&& bultain2.getEleve1().getIdeleve() == bultain.getEleve1().getIdeleve()) {
						bultain.setIdbultain(bultain2.getIdbultain());
						bultainExisting = bultain2;
						break;
					}
				} catch (Throwable t1) {
				}
			}
			if (bultain.getIdbultain() == null) {
				bultainService.addBultain(bultain);
				return NoteFactory.bultainToNoteDto(bultain);
			}

		}
		if (bultainExisting == null) {
			throw new Exception(" null value in bultain");
		}
		if (bultain.getNoteTp() != 0) {
			bultainExisting.setNoteTp(bultain.getNoteTp());
		}
		if (bultain.getNoteControle1() != 0) {
			bultainExisting.setNoteControle1(bultain.getNoteControle1());
		}
		if (bultain.getNoteControle2() != 0) {
			bultainExisting.setNoteControle2(bultain.getNoteControle2());
		}
		if (bultain.getNoteSynthese() != 0) {
			bultainExisting.setNoteSynthese(bultain.getNoteSynthese());
		}
		if (bultain.getDateExamenC1() != null) {
			bultainExisting.setDateExamenC1(bultain.getDateExamenC1());
		}
		if (bultain.getDateExamenC2() != null) {
			bultainExisting.setDateExamenC2(bultain.getDateExamenC2());
		}
		if (bultain.getDateExamen() != null) {
			bultainExisting.setDateExamen(bultain.getDateExamen());
		}
		if (bultain.getDateExamenTp() != null) {
			bultainExisting.setDateExamenTp(bultain.getDateExamenTp());
		}
		bultainService.addBultain(bultainExisting);
		return NoteFactory.bultainToNoteDto(bultainExisting);
	}

	@PostMapping("/absence")
	public AbsenceDto addOrUpdateAbsence(@RequestBody Bultain bultain) {
		Bultain bultainExisting = null;
		try {
			bultainExisting = bultainService.getBultainById(bultain.getIdbultain());
		} catch (Throwable t) {
			List<Bultain> allBultains = bultainService.getAllBultain();
			for (Bultain bultain2 : allBultains) {
				try {
					if (bultain2.getMatiere().getIdmatiere() == bultain.getMatiere().getIdmatiere()
							&& bultain2.getEleve1().getIdeleve() == bultain.getEleve1().getIdeleve()) {
						bultain.setIdbultain(bultain2.getIdbultain());
						bultainExisting = bultain2;
						break;
					}
				} catch (Throwable t1) {
				}
			}
			if (bultain.getIdbultain() == null) {
				bultainService.addBultain(bultain);
				return AbsenceFactory.bultainToAbsenceDto(bultain);
			}
		}
		if (bultain.getNbabsence() != 0) {
			bultainExisting.setNbabsence(bultain.getNbabsence());
		} else {
			bultainExisting.setNbabsence(bultainExisting.getNbabsence() + 1);
		}
		bultainService.addBultain(bultainExisting);
		return AbsenceFactory.bultainToAbsenceDto(bultainExisting);
	}
	
	@GetMapping("/abcences/{ideleve}/matiere/{idmatiere}")
	public AbsenceDto getAbsenceByIdeleveAndIdmatiere(@PathVariable(value="ideleve") Long ideleve , @PathVariable(value="idmatiere") Long idmatiere) {
		List<Bultain> allBultains = bultainService.getAllBultain();
		for (Bultain bultain2 : allBultains) {
			try {
				if (bultain2.getMatiere().getIdmatiere() == idmatiere
						&& bultain2.getEleve1().getIdeleve() == ideleve) {
					return AbsenceFactory.bultainToAbsenceDto(bultain2);
					
				}
				
			} catch (Throwable t1) {
				
			}
			
		} 
		AbsenceDto absenceDto=new AbsenceDto();
		absenceDto.setNbAbsence(0);
		return absenceDto;
	}
	
	@GetMapping("/notes/{ideleve}/matiere/{idmatiere}")
	public NoteDto getNoteByIdeleveAndIdmatiere(@PathVariable(value="ideleve") Long ideleve , @PathVariable(value="idmatiere") Long idmatiere) {
		List<Bultain> allBultains = bultainService.getAllBultain();
		for (Bultain bultain2 : allBultains) {
			try {
				if (bultain2.getMatiere().getIdmatiere() == idmatiere
						&& bultain2.getEleve1().getIdeleve() == ideleve) {
					return NoteFactory.bultainToNoteDto(bultain2);
					
				}
				
			} catch (Throwable t1) {
				
			}
			
		} 
		return null;
	}

}
