package com.nourelmaaref.master.service.dto;

import com.nourelmaaref.master.domain.enumeration.Egenre;
import com.nourelmaaref.master.domain.enumeration.typeDePaiement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.nourelmaaref.master.domain.Eleve} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class EleveDTO implements Serializable {

    private Long id;

    private String matricule;

    private String nom;

    private String prenom;

    private String adress;

    private String ville;

    private String pays;

    private String telephone;

    private String email;

    private LocalDate dateDeNaissance;

    private Egenre genre;

    private typeDePaiement typeDePaiement;

    private ClasseDTO appartientA;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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

    public LocalDate getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(LocalDate dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public Egenre getGenre() {
        return genre;
    }

    public void setGenre(Egenre genre) {
        this.genre = genre;
    }

    public typeDePaiement getTypeDePaiement() {
        return typeDePaiement;
    }

    public void setTypeDePaiement(typeDePaiement typeDePaiement) {
        this.typeDePaiement = typeDePaiement;
    }

    public ClasseDTO getAppartientA() {
        return appartientA;
    }

    public void setAppartientA(ClasseDTO appartientA) {
        this.appartientA = appartientA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EleveDTO)) {
            return false;
        }

        EleveDTO eleveDTO = (EleveDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, eleveDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EleveDTO{" +
            "id=" + getId() +
            ", matricule='" + getMatricule() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", adress='" + getAdress() + "'" +
            ", ville='" + getVille() + "'" +
            ", pays='" + getPays() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", email='" + getEmail() + "'" +
            ", dateDeNaissance='" + getDateDeNaissance() + "'" +
            ", genre='" + getGenre() + "'" +
            ", typeDePaiement='" + getTypeDePaiement() + "'" +
            ", appartientA=" + getAppartientA() +
            "}";
    }
}
