package com.nourelmaaref.master.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.nourelmaaref.master.domain.Cours} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CoursDTO implements Serializable {

    private Long id;

    private Integer numeroCours;

    private LocalDate dateCour;

    private ProfesseurDTO enseignerPar;

    private ClasseDTO pourLaClasse;

    private MatiereDTO nomMatiere;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroCours() {
        return numeroCours;
    }

    public void setNumeroCours(Integer numeroCours) {
        this.numeroCours = numeroCours;
    }

    public LocalDate getDateCour() {
        return dateCour;
    }

    public void setDateCour(LocalDate dateCour) {
        this.dateCour = dateCour;
    }

    public ProfesseurDTO getEnseignerPar() {
        return enseignerPar;
    }

    public void setEnseignerPar(ProfesseurDTO enseignerPar) {
        this.enseignerPar = enseignerPar;
    }

    public ClasseDTO getPourLaClasse() {
        return pourLaClasse;
    }

    public void setPourLaClasse(ClasseDTO pourLaClasse) {
        this.pourLaClasse = pourLaClasse;
    }

    public MatiereDTO getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(MatiereDTO nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoursDTO)) {
            return false;
        }

        CoursDTO coursDTO = (CoursDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, coursDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CoursDTO{" +
            "id=" + getId() +
            ", numeroCours=" + getNumeroCours() +
            ", dateCour='" + getDateCour() + "'" +
            ", enseignerPar=" + getEnseignerPar() +
            ", pourLaClasse=" + getPourLaClasse() +
            ", nomMatiere=" + getNomMatiere() +
            "}";
    }
}
