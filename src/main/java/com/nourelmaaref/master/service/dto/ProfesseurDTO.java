package com.nourelmaaref.master.service.dto;

import com.nourelmaaref.master.domain.enumeration.typeContrat;
import com.nourelmaaref.master.domain.enumeration.typeDeGenre;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.nourelmaaref.master.domain.Professeur} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProfesseurDTO implements Serializable {

    private Long id;

    private String nom;

    private String prenom;

    private String cin;

    private String adress;

    private String ville;

    private String pays;

    private String telephone;

    private String email;

    private String grade;

    private String spetialite;

    private typeContrat typeDeContrat;

    private LocalDate dateContrat;

    private typeDeGenre genre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSpetialite() {
        return spetialite;
    }

    public void setSpetialite(String spetialite) {
        this.spetialite = spetialite;
    }

    public typeContrat getTypeDeContrat() {
        return typeDeContrat;
    }

    public void setTypeDeContrat(typeContrat typeDeContrat) {
        this.typeDeContrat = typeDeContrat;
    }

    public LocalDate getDateContrat() {
        return dateContrat;
    }

    public void setDateContrat(LocalDate dateContrat) {
        this.dateContrat = dateContrat;
    }

    public typeDeGenre getGenre() {
        return genre;
    }

    public void setGenre(typeDeGenre genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProfesseurDTO)) {
            return false;
        }

        ProfesseurDTO professeurDTO = (ProfesseurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, professeurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProfesseurDTO{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", cin='" + getCin() + "'" +
            ", adress='" + getAdress() + "'" +
            ", ville='" + getVille() + "'" +
            ", pays='" + getPays() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", grade='" + getGrade() + "'" +
            ", spetialite='" + getSpetialite() + "'" +
            ", typeDeContrat='" + getTypeDeContrat() + "'" +
            ", dateContrat='" + getDateContrat() + "'" +
            ", genre='" + getGenre() + "'" +
            "}";
    }
}
