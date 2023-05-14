package com.nourelmaaref.master.domain;

import com.nourelmaaref.master.domain.enumeration.typeContrat;
import com.nourelmaaref.master.domain.enumeration.typeDeGenre;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Professeur.
 */
@Entity
@Table(name = "professeur")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Professeur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "cin", unique = true)
    private String cin;

    @Column(name = "adress")
    private String adress;

    @Column(name = "ville")
    private String ville;

    @Column(name = "pays")
    private String pays;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "email")
    private String email;

    @Column(name = "grade")
    private String grade;

    @Column(name = "spetialite")
    private String spetialite;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_de_contrat")
    private typeContrat typeDeContrat;

    @Column(name = "date_contrat")
    private LocalDate dateContrat;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private typeDeGenre genre;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Professeur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public Professeur nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Professeur prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return this.cin;
    }

    public Professeur cin(String cin) {
        this.setCin(cin);
        return this;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getAdress() {
        return this.adress;
    }

    public Professeur adress(String adress) {
        this.setAdress(adress);
        return this;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getVille() {
        return this.ville;
    }

    public Professeur ville(String ville) {
        this.setVille(ville);
        return this;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return this.pays;
    }

    public Professeur pays(String pays) {
        this.setPays(pays);
        return this;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public Professeur telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return this.email;
    }

    public Professeur email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrade() {
        return this.grade;
    }

    public Professeur grade(String grade) {
        this.setGrade(grade);
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSpetialite() {
        return this.spetialite;
    }

    public Professeur spetialite(String spetialite) {
        this.setSpetialite(spetialite);
        return this;
    }

    public void setSpetialite(String spetialite) {
        this.spetialite = spetialite;
    }

    public typeContrat getTypeDeContrat() {
        return this.typeDeContrat;
    }

    public Professeur typeDeContrat(typeContrat typeDeContrat) {
        this.setTypeDeContrat(typeDeContrat);
        return this;
    }

    public void setTypeDeContrat(typeContrat typeDeContrat) {
        this.typeDeContrat = typeDeContrat;
    }

    public LocalDate getDateContrat() {
        return this.dateContrat;
    }

    public Professeur dateContrat(LocalDate dateContrat) {
        this.setDateContrat(dateContrat);
        return this;
    }

    public void setDateContrat(LocalDate dateContrat) {
        this.dateContrat = dateContrat;
    }

    public typeDeGenre getGenre() {
        return this.genre;
    }

    public Professeur genre(typeDeGenre genre) {
        this.setGenre(genre);
        return this;
    }

    public void setGenre(typeDeGenre genre) {
        this.genre = genre;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Professeur)) {
            return false;
        }
        return id != null && id.equals(((Professeur) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Professeur{" +
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
