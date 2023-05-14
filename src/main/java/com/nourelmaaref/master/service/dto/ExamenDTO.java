package com.nourelmaaref.master.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.nourelmaaref.master.domain.Examen} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ExamenDTO implements Serializable {

    private Long id;

    private Integer numExamen;

    private Double pourcentage;

    private Boolean valide;

    private LocalDate dateExamen;

    private Integer duree;

    private String nomExamen;

    private MatiereDTO nomMatiere;

    private ClasseDTO nomClasse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumExamen() {
        return numExamen;
    }

    public void setNumExamen(Integer numExamen) {
        this.numExamen = numExamen;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public LocalDate getDateExamen() {
        return dateExamen;
    }

    public void setDateExamen(LocalDate dateExamen) {
        this.dateExamen = dateExamen;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public String getNomExamen() {
        return nomExamen;
    }

    public void setNomExamen(String nomExamen) {
        this.nomExamen = nomExamen;
    }

    public MatiereDTO getNomMatiere() {
        return nomMatiere;
    }

    public void setNomMatiere(MatiereDTO nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public ClasseDTO getNomClasse() {
        return nomClasse;
    }

    public void setNomClasse(ClasseDTO nomClasse) {
        this.nomClasse = nomClasse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ExamenDTO)) {
            return false;
        }

        ExamenDTO examenDTO = (ExamenDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, examenDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ExamenDTO{" +
            "id=" + getId() +
            ", numExamen=" + getNumExamen() +
            ", pourcentage=" + getPourcentage() +
            ", valide='" + getValide() + "'" +
            ", dateExamen='" + getDateExamen() + "'" +
            ", duree=" + getDuree() +
            ", nomExamen='" + getNomExamen() + "'" +
            ", nomMatiere=" + getNomMatiere() +
            ", nomClasse=" + getNomClasse() +
            "}";
    }
}
