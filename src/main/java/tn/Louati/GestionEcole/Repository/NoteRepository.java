package tn.Louati.GestionEcole.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.Louati.GestionEcole.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

}
