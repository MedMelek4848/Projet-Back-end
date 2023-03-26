package tn.Louati.GestionEcole.Factory;

import tn.Louati.GestionEcole.dto.NoteDto;
import tn.Louati.GestionEcole.model.Bultain;

public class NoteFactory {
	
	public static NoteDto bultainToNoteDto(Bultain bultain) {
		NoteDto noteDto = new NoteDto();
		noteDto.setNoteControle1(bultain.getNoteControle1());
		noteDto.setNoteControle2(bultain.getNoteControle2());
		noteDto.setNoteSynthese(bultain.getNoteSynthese());
		noteDto.setNoteTp(bultain.getNoteTp());
		noteDto.setDateExamenC1(bultain.getDateExamenC1());
		noteDto.setDateExamenC2(bultain.getDateExamenC2());
		noteDto.setDateExamen(bultain.getDateExamen());
		noteDto.setNomMatiere(bultain.getMatiere().getNom());
		return noteDto; 

	}

}
