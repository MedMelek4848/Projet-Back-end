package tn.Louati.GestionEcole.Factory;

import tn.Louati.GestionEcole.dto.AbsenceDto;
import tn.Louati.GestionEcole.model.Bultain;

public class AbsenceFactory {
	public static AbsenceDto bultainToAbsenceDto(Bultain bultain) {
		AbsenceDto absenceDto = new AbsenceDto();
		 absenceDto.setNbAbsence(bultain.getNbabsence());

		 return absenceDto;
	}

}
