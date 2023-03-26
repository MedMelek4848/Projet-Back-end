package tn.Louati.GestionEcole.dto;

import java.util.Date;

public class NoteDto {

	int noteControle1;

	int noteControle2;

	int noteTp;

	int noteSynthese;

	Date dateExamenC1;

	Date dateExamenC2;

	Date dateExamen;

	Date dateExamenTp;
	

	public int getNoteControle1() {
		return noteControle1;
	}

	public void setNoteControle1(int noteControle1) {
		this.noteControle1 = noteControle1;
	}

	public int getNoteControle2() {
		return noteControle2;
	}

	public void setNoteControle2(int noteControle2) {
		this.noteControle2 = noteControle2;
	}

	public int getNoteTp() {
		return noteTp;
	}

	public void setNoteTp(int noteTp) {
		this.noteTp = noteTp;
	}

	public int getNoteSynthese() {
		return noteSynthese;
	}

	public void setNoteSynthese(int noteSynthese) {
		this.noteSynthese = noteSynthese;
	}

	public Date getDateExamenC1() {
		return dateExamenC1;
	}

	public void setDateExamenC1(Date dateExamenC1) {
		this.dateExamenC1 = dateExamenC1;
	}

	public Date getDateExamenC2() {
		return dateExamenC2;
	}

	public void setDateExamenC2(Date dateExamenC2) {
		this.dateExamenC2 = dateExamenC2;
	}

	public Date getDateExamen() {
		return dateExamen;
	}

	public void setDateExamen(Date dateExamen) {
		this.dateExamen = dateExamen;
	}

	public Date getDateExamenTp() {
		return dateExamenTp;
	}

	public void setDateExamenTp(Date dateExamenTp) {
		this.dateExamenTp = dateExamenTp;
	}

}
