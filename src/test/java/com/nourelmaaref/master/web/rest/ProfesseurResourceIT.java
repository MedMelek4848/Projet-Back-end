package com.nourelmaaref.master.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nourelmaaref.master.IntegrationTest;
import com.nourelmaaref.master.domain.Professeur;
import com.nourelmaaref.master.domain.enumeration.typeContrat;
import com.nourelmaaref.master.domain.enumeration.typeDeGenre;
import com.nourelmaaref.master.repository.ProfesseurRepository;
import com.nourelmaaref.master.service.dto.ProfesseurDTO;
import com.nourelmaaref.master.service.mapper.ProfesseurMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ProfesseurResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProfesseurResourceIT {

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_CIN = "AAAAAAAAAA";
    private static final String UPDATED_CIN = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADRESS = "BBBBBBBBBB";

    private static final String DEFAULT_VILLE = "AAAAAAAAAA";
    private static final String UPDATED_VILLE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYS = "AAAAAAAAAA";
    private static final String UPDATED_PAYS = "BBBBBBBBBB";

    private static final String DEFAULT_TELEPHONE = "AAAAAAAAAA";
    private static final String UPDATED_TELEPHONE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_GRADE = "BBBBBBBBBB";

    private static final String DEFAULT_SPETIALITE = "AAAAAAAAAA";
    private static final String UPDATED_SPETIALITE = "BBBBBBBBBB";

    private static final typeContrat DEFAULT_TYPE_DE_CONTRAT = typeContrat.CDD;
    private static final typeContrat UPDATED_TYPE_DE_CONTRAT = typeContrat.CDI;

    private static final LocalDate DEFAULT_DATE_CONTRAT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CONTRAT = LocalDate.now(ZoneId.systemDefault());

    private static final typeDeGenre DEFAULT_GENRE = typeDeGenre.HOMME;
    private static final typeDeGenre UPDATED_GENRE = typeDeGenre.FEMME;

    private static final String ENTITY_API_URL = "/api/professeurs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ProfesseurRepository professeurRepository;

    @Autowired
    private ProfesseurMapper professeurMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProfesseurMockMvc;

    private Professeur professeur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Professeur createEntity(EntityManager em) {
        Professeur professeur = new Professeur()
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .cin(DEFAULT_CIN)
            .adress(DEFAULT_ADRESS)
            .ville(DEFAULT_VILLE)
            .pays(DEFAULT_PAYS)
            .telephone(DEFAULT_TELEPHONE)
            .email(DEFAULT_EMAIL)
            .grade(DEFAULT_GRADE)
            .spetialite(DEFAULT_SPETIALITE)
            .typeDeContrat(DEFAULT_TYPE_DE_CONTRAT)
            .dateContrat(DEFAULT_DATE_CONTRAT)
            .genre(DEFAULT_GENRE);
        return professeur;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Professeur createUpdatedEntity(EntityManager em) {
        Professeur professeur = new Professeur()
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .cin(UPDATED_CIN)
            .adress(UPDATED_ADRESS)
            .ville(UPDATED_VILLE)
            .pays(UPDATED_PAYS)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL)
            .grade(UPDATED_GRADE)
            .spetialite(UPDATED_SPETIALITE)
            .typeDeContrat(UPDATED_TYPE_DE_CONTRAT)
            .dateContrat(UPDATED_DATE_CONTRAT)
            .genre(UPDATED_GENRE);
        return professeur;
    }

    @BeforeEach
    public void initTest() {
        professeur = createEntity(em);
    }

    @Test
    @Transactional
    void createProfesseur() throws Exception {
        int databaseSizeBeforeCreate = professeurRepository.findAll().size();
        // Create the Professeur
        ProfesseurDTO professeurDTO = professeurMapper.toDto(professeur);
        restProfesseurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(professeurDTO)))
            .andExpect(status().isCreated());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeCreate + 1);
        Professeur testProfesseur = professeurList.get(professeurList.size() - 1);
        assertThat(testProfesseur.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testProfesseur.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testProfesseur.getCin()).isEqualTo(DEFAULT_CIN);
        assertThat(testProfesseur.getAdress()).isEqualTo(DEFAULT_ADRESS);
        assertThat(testProfesseur.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testProfesseur.getPays()).isEqualTo(DEFAULT_PAYS);
        assertThat(testProfesseur.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testProfesseur.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testProfesseur.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testProfesseur.getSpetialite()).isEqualTo(DEFAULT_SPETIALITE);
        assertThat(testProfesseur.getTypeDeContrat()).isEqualTo(DEFAULT_TYPE_DE_CONTRAT);
        assertThat(testProfesseur.getDateContrat()).isEqualTo(DEFAULT_DATE_CONTRAT);
        assertThat(testProfesseur.getGenre()).isEqualTo(DEFAULT_GENRE);
    }

    @Test
    @Transactional
    void createProfesseurWithExistingId() throws Exception {
        // Create the Professeur with an existing ID
        professeur.setId(1L);
        ProfesseurDTO professeurDTO = professeurMapper.toDto(professeur);

        int databaseSizeBeforeCreate = professeurRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProfesseurMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(professeurDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProfesseurs() throws Exception {
        // Initialize the database
        professeurRepository.saveAndFlush(professeur);

        // Get all the professeurList
        restProfesseurMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(professeur.getId().intValue())))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].cin").value(hasItem(DEFAULT_CIN)))
            .andExpect(jsonPath("$.[*].adress").value(hasItem(DEFAULT_ADRESS)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].spetialite").value(hasItem(DEFAULT_SPETIALITE)))
            .andExpect(jsonPath("$.[*].typeDeContrat").value(hasItem(DEFAULT_TYPE_DE_CONTRAT.toString())))
            .andExpect(jsonPath("$.[*].dateContrat").value(hasItem(DEFAULT_DATE_CONTRAT.toString())))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE.toString())));
    }

    @Test
    @Transactional
    void getProfesseur() throws Exception {
        // Initialize the database
        professeurRepository.saveAndFlush(professeur);

        // Get the professeur
        restProfesseurMockMvc
            .perform(get(ENTITY_API_URL_ID, professeur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(professeur.getId().intValue()))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.cin").value(DEFAULT_CIN))
            .andExpect(jsonPath("$.adress").value(DEFAULT_ADRESS))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.spetialite").value(DEFAULT_SPETIALITE))
            .andExpect(jsonPath("$.typeDeContrat").value(DEFAULT_TYPE_DE_CONTRAT.toString()))
            .andExpect(jsonPath("$.dateContrat").value(DEFAULT_DATE_CONTRAT.toString()))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProfesseur() throws Exception {
        // Get the professeur
        restProfesseurMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProfesseur() throws Exception {
        // Initialize the database
        professeurRepository.saveAndFlush(professeur);

        int databaseSizeBeforeUpdate = professeurRepository.findAll().size();

        // Update the professeur
        Professeur updatedProfesseur = professeurRepository.findById(professeur.getId()).get();
        // Disconnect from session so that the updates on updatedProfesseur are not directly saved in db
        em.detach(updatedProfesseur);
        updatedProfesseur
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .cin(UPDATED_CIN)
            .adress(UPDATED_ADRESS)
            .ville(UPDATED_VILLE)
            .pays(UPDATED_PAYS)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL)
            .grade(UPDATED_GRADE)
            .spetialite(UPDATED_SPETIALITE)
            .typeDeContrat(UPDATED_TYPE_DE_CONTRAT)
            .dateContrat(UPDATED_DATE_CONTRAT)
            .genre(UPDATED_GENRE);
        ProfesseurDTO professeurDTO = professeurMapper.toDto(updatedProfesseur);

        restProfesseurMockMvc
            .perform(
                put(ENTITY_API_URL_ID, professeurDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(professeurDTO))
            )
            .andExpect(status().isOk());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeUpdate);
        Professeur testProfesseur = professeurList.get(professeurList.size() - 1);
        assertThat(testProfesseur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testProfesseur.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testProfesseur.getCin()).isEqualTo(UPDATED_CIN);
        assertThat(testProfesseur.getAdress()).isEqualTo(UPDATED_ADRESS);
        assertThat(testProfesseur.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testProfesseur.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testProfesseur.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testProfesseur.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProfesseur.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testProfesseur.getSpetialite()).isEqualTo(UPDATED_SPETIALITE);
        assertThat(testProfesseur.getTypeDeContrat()).isEqualTo(UPDATED_TYPE_DE_CONTRAT);
        assertThat(testProfesseur.getDateContrat()).isEqualTo(UPDATED_DATE_CONTRAT);
        assertThat(testProfesseur.getGenre()).isEqualTo(UPDATED_GENRE);
    }

    @Test
    @Transactional
    void putNonExistingProfesseur() throws Exception {
        int databaseSizeBeforeUpdate = professeurRepository.findAll().size();
        professeur.setId(count.incrementAndGet());

        // Create the Professeur
        ProfesseurDTO professeurDTO = professeurMapper.toDto(professeur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfesseurMockMvc
            .perform(
                put(ENTITY_API_URL_ID, professeurDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(professeurDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProfesseur() throws Exception {
        int databaseSizeBeforeUpdate = professeurRepository.findAll().size();
        professeur.setId(count.incrementAndGet());

        // Create the Professeur
        ProfesseurDTO professeurDTO = professeurMapper.toDto(professeur);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProfesseurMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(professeurDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProfesseur() throws Exception {
        int databaseSizeBeforeUpdate = professeurRepository.findAll().size();
        professeur.setId(count.incrementAndGet());

        // Create the Professeur
        ProfesseurDTO professeurDTO = professeurMapper.toDto(professeur);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProfesseurMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(professeurDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProfesseurWithPatch() throws Exception {
        // Initialize the database
        professeurRepository.saveAndFlush(professeur);

        int databaseSizeBeforeUpdate = professeurRepository.findAll().size();

        // Update the professeur using partial update
        Professeur partialUpdatedProfesseur = new Professeur();
        partialUpdatedProfesseur.setId(professeur.getId());

        partialUpdatedProfesseur
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .cin(UPDATED_CIN)
            .adress(UPDATED_ADRESS)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL)
            .grade(UPDATED_GRADE)
            .genre(UPDATED_GENRE);

        restProfesseurMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProfesseur.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProfesseur))
            )
            .andExpect(status().isOk());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeUpdate);
        Professeur testProfesseur = professeurList.get(professeurList.size() - 1);
        assertThat(testProfesseur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testProfesseur.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testProfesseur.getCin()).isEqualTo(UPDATED_CIN);
        assertThat(testProfesseur.getAdress()).isEqualTo(UPDATED_ADRESS);
        assertThat(testProfesseur.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testProfesseur.getPays()).isEqualTo(DEFAULT_PAYS);
        assertThat(testProfesseur.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testProfesseur.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProfesseur.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testProfesseur.getSpetialite()).isEqualTo(DEFAULT_SPETIALITE);
        assertThat(testProfesseur.getTypeDeContrat()).isEqualTo(DEFAULT_TYPE_DE_CONTRAT);
        assertThat(testProfesseur.getDateContrat()).isEqualTo(DEFAULT_DATE_CONTRAT);
        assertThat(testProfesseur.getGenre()).isEqualTo(UPDATED_GENRE);
    }

    @Test
    @Transactional
    void fullUpdateProfesseurWithPatch() throws Exception {
        // Initialize the database
        professeurRepository.saveAndFlush(professeur);

        int databaseSizeBeforeUpdate = professeurRepository.findAll().size();

        // Update the professeur using partial update
        Professeur partialUpdatedProfesseur = new Professeur();
        partialUpdatedProfesseur.setId(professeur.getId());

        partialUpdatedProfesseur
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .cin(UPDATED_CIN)
            .adress(UPDATED_ADRESS)
            .ville(UPDATED_VILLE)
            .pays(UPDATED_PAYS)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL)
            .grade(UPDATED_GRADE)
            .spetialite(UPDATED_SPETIALITE)
            .typeDeContrat(UPDATED_TYPE_DE_CONTRAT)
            .dateContrat(UPDATED_DATE_CONTRAT)
            .genre(UPDATED_GENRE);

        restProfesseurMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProfesseur.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedProfesseur))
            )
            .andExpect(status().isOk());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeUpdate);
        Professeur testProfesseur = professeurList.get(professeurList.size() - 1);
        assertThat(testProfesseur.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testProfesseur.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testProfesseur.getCin()).isEqualTo(UPDATED_CIN);
        assertThat(testProfesseur.getAdress()).isEqualTo(UPDATED_ADRESS);
        assertThat(testProfesseur.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testProfesseur.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testProfesseur.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testProfesseur.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testProfesseur.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testProfesseur.getSpetialite()).isEqualTo(UPDATED_SPETIALITE);
        assertThat(testProfesseur.getTypeDeContrat()).isEqualTo(UPDATED_TYPE_DE_CONTRAT);
        assertThat(testProfesseur.getDateContrat()).isEqualTo(UPDATED_DATE_CONTRAT);
        assertThat(testProfesseur.getGenre()).isEqualTo(UPDATED_GENRE);
    }

    @Test
    @Transactional
    void patchNonExistingProfesseur() throws Exception {
        int databaseSizeBeforeUpdate = professeurRepository.findAll().size();
        professeur.setId(count.incrementAndGet());

        // Create the Professeur
        ProfesseurDTO professeurDTO = professeurMapper.toDto(professeur);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProfesseurMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, professeurDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(professeurDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProfesseur() throws Exception {
        int databaseSizeBeforeUpdate = professeurRepository.findAll().size();
        professeur.setId(count.incrementAndGet());

        // Create the Professeur
        ProfesseurDTO professeurDTO = professeurMapper.toDto(professeur);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProfesseurMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(professeurDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProfesseur() throws Exception {
        int databaseSizeBeforeUpdate = professeurRepository.findAll().size();
        professeur.setId(count.incrementAndGet());

        // Create the Professeur
        ProfesseurDTO professeurDTO = professeurMapper.toDto(professeur);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProfesseurMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(professeurDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Professeur in the database
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProfesseur() throws Exception {
        // Initialize the database
        professeurRepository.saveAndFlush(professeur);

        int databaseSizeBeforeDelete = professeurRepository.findAll().size();

        // Delete the professeur
        restProfesseurMockMvc
            .perform(delete(ENTITY_API_URL_ID, professeur.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Professeur> professeurList = professeurRepository.findAll();
        assertThat(professeurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
