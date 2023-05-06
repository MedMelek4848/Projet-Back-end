package com.nourelmaaref.master.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nourelmaaref.master.IntegrationTest;
import com.nourelmaaref.master.domain.Eleve;
import com.nourelmaaref.master.domain.enumeration.Egenre;
import com.nourelmaaref.master.domain.enumeration.typeDePaiement;
import com.nourelmaaref.master.repository.EleveRepository;
import com.nourelmaaref.master.service.EleveService;
import com.nourelmaaref.master.service.dto.EleveDTO;
import com.nourelmaaref.master.service.mapper.EleveMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EleveResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class EleveResourceIT {

    private static final String DEFAULT_MATRICULE = "AAAAAAAAAA";
    private static final String UPDATED_MATRICULE = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

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

    private static final LocalDate DEFAULT_DATE_DE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final Egenre DEFAULT_GENRE = Egenre.HOMME;
    private static final Egenre UPDATED_GENRE = Egenre.FEMME;

    private static final typeDePaiement DEFAULT_TYPE_DE_PAIEMENT = typeDePaiement.CHEQUE;
    private static final typeDePaiement UPDATED_TYPE_DE_PAIEMENT = typeDePaiement.ESPECE;

    private static final String ENTITY_API_URL = "/api/eleves";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EleveRepository eleveRepository;

    @Mock
    private EleveRepository eleveRepositoryMock;

    @Autowired
    private EleveMapper eleveMapper;

    @Mock
    private EleveService eleveServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEleveMockMvc;

    private Eleve eleve;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Eleve createEntity(EntityManager em) {
        Eleve eleve = new Eleve()
            .matricule(DEFAULT_MATRICULE)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .adress(DEFAULT_ADRESS)
            .ville(DEFAULT_VILLE)
            .pays(DEFAULT_PAYS)
            .telephone(DEFAULT_TELEPHONE)
            .email(DEFAULT_EMAIL)
            .dateDeNaissance(DEFAULT_DATE_DE_NAISSANCE)
            .genre(DEFAULT_GENRE)
            .typeDePaiement(DEFAULT_TYPE_DE_PAIEMENT);
        return eleve;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Eleve createUpdatedEntity(EntityManager em) {
        Eleve eleve = new Eleve()
            .matricule(UPDATED_MATRICULE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adress(UPDATED_ADRESS)
            .ville(UPDATED_VILLE)
            .pays(UPDATED_PAYS)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL)
            .dateDeNaissance(UPDATED_DATE_DE_NAISSANCE)
            .genre(UPDATED_GENRE)
            .typeDePaiement(UPDATED_TYPE_DE_PAIEMENT);
        return eleve;
    }

    @BeforeEach
    public void initTest() {
        eleve = createEntity(em);
    }

    @Test
    @Transactional
    void createEleve() throws Exception {
        int databaseSizeBeforeCreate = eleveRepository.findAll().size();
        // Create the Eleve
        EleveDTO eleveDTO = eleveMapper.toDto(eleve);
        restEleveMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eleveDTO)))
            .andExpect(status().isCreated());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeCreate + 1);
        Eleve testEleve = eleveList.get(eleveList.size() - 1);
        assertThat(testEleve.getMatricule()).isEqualTo(DEFAULT_MATRICULE);
        assertThat(testEleve.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEleve.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testEleve.getAdress()).isEqualTo(DEFAULT_ADRESS);
        assertThat(testEleve.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testEleve.getPays()).isEqualTo(DEFAULT_PAYS);
        assertThat(testEleve.getTelephone()).isEqualTo(DEFAULT_TELEPHONE);
        assertThat(testEleve.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEleve.getDateDeNaissance()).isEqualTo(DEFAULT_DATE_DE_NAISSANCE);
        assertThat(testEleve.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testEleve.getTypeDePaiement()).isEqualTo(DEFAULT_TYPE_DE_PAIEMENT);
    }

    @Test
    @Transactional
    void createEleveWithExistingId() throws Exception {
        // Create the Eleve with an existing ID
        eleve.setId(1L);
        EleveDTO eleveDTO = eleveMapper.toDto(eleve);

        int databaseSizeBeforeCreate = eleveRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEleveMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eleveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEleves() throws Exception {
        // Initialize the database
        eleveRepository.saveAndFlush(eleve);

        // Get all the eleveList
        restEleveMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(eleve.getId().intValue())))
            .andExpect(jsonPath("$.[*].matricule").value(hasItem(DEFAULT_MATRICULE)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].adress").value(hasItem(DEFAULT_ADRESS)))
            .andExpect(jsonPath("$.[*].ville").value(hasItem(DEFAULT_VILLE)))
            .andExpect(jsonPath("$.[*].pays").value(hasItem(DEFAULT_PAYS)))
            .andExpect(jsonPath("$.[*].telephone").value(hasItem(DEFAULT_TELEPHONE)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].dateDeNaissance").value(hasItem(DEFAULT_DATE_DE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE.toString())))
            .andExpect(jsonPath("$.[*].typeDePaiement").value(hasItem(DEFAULT_TYPE_DE_PAIEMENT.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllElevesWithEagerRelationshipsIsEnabled() throws Exception {
        when(eleveServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEleveMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(eleveServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllElevesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(eleveServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restEleveMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(eleveRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getEleve() throws Exception {
        // Initialize the database
        eleveRepository.saveAndFlush(eleve);

        // Get the eleve
        restEleveMockMvc
            .perform(get(ENTITY_API_URL_ID, eleve.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(eleve.getId().intValue()))
            .andExpect(jsonPath("$.matricule").value(DEFAULT_MATRICULE))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.adress").value(DEFAULT_ADRESS))
            .andExpect(jsonPath("$.ville").value(DEFAULT_VILLE))
            .andExpect(jsonPath("$.pays").value(DEFAULT_PAYS))
            .andExpect(jsonPath("$.telephone").value(DEFAULT_TELEPHONE))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.dateDeNaissance").value(DEFAULT_DATE_DE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE.toString()))
            .andExpect(jsonPath("$.typeDePaiement").value(DEFAULT_TYPE_DE_PAIEMENT.toString()));
    }

    @Test
    @Transactional
    void getNonExistingEleve() throws Exception {
        // Get the eleve
        restEleveMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEleve() throws Exception {
        // Initialize the database
        eleveRepository.saveAndFlush(eleve);

        int databaseSizeBeforeUpdate = eleveRepository.findAll().size();

        // Update the eleve
        Eleve updatedEleve = eleveRepository.findById(eleve.getId()).get();
        // Disconnect from session so that the updates on updatedEleve are not directly saved in db
        em.detach(updatedEleve);
        updatedEleve
            .matricule(UPDATED_MATRICULE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adress(UPDATED_ADRESS)
            .ville(UPDATED_VILLE)
            .pays(UPDATED_PAYS)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL)
            .dateDeNaissance(UPDATED_DATE_DE_NAISSANCE)
            .genre(UPDATED_GENRE)
            .typeDePaiement(UPDATED_TYPE_DE_PAIEMENT);
        EleveDTO eleveDTO = eleveMapper.toDto(updatedEleve);

        restEleveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, eleveDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(eleveDTO))
            )
            .andExpect(status().isOk());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeUpdate);
        Eleve testEleve = eleveList.get(eleveList.size() - 1);
        assertThat(testEleve.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testEleve.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEleve.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testEleve.getAdress()).isEqualTo(UPDATED_ADRESS);
        assertThat(testEleve.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testEleve.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testEleve.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testEleve.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEleve.getDateDeNaissance()).isEqualTo(UPDATED_DATE_DE_NAISSANCE);
        assertThat(testEleve.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testEleve.getTypeDePaiement()).isEqualTo(UPDATED_TYPE_DE_PAIEMENT);
    }

    @Test
    @Transactional
    void putNonExistingEleve() throws Exception {
        int databaseSizeBeforeUpdate = eleveRepository.findAll().size();
        eleve.setId(count.incrementAndGet());

        // Create the Eleve
        EleveDTO eleveDTO = eleveMapper.toDto(eleve);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEleveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, eleveDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(eleveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEleve() throws Exception {
        int databaseSizeBeforeUpdate = eleveRepository.findAll().size();
        eleve.setId(count.incrementAndGet());

        // Create the Eleve
        EleveDTO eleveDTO = eleveMapper.toDto(eleve);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEleveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(eleveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEleve() throws Exception {
        int databaseSizeBeforeUpdate = eleveRepository.findAll().size();
        eleve.setId(count.incrementAndGet());

        // Create the Eleve
        EleveDTO eleveDTO = eleveMapper.toDto(eleve);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEleveMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(eleveDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEleveWithPatch() throws Exception {
        // Initialize the database
        eleveRepository.saveAndFlush(eleve);

        int databaseSizeBeforeUpdate = eleveRepository.findAll().size();

        // Update the eleve using partial update
        Eleve partialUpdatedEleve = new Eleve();
        partialUpdatedEleve.setId(eleve.getId());

        partialUpdatedEleve
            .matricule(UPDATED_MATRICULE)
            .pays(UPDATED_PAYS)
            .telephone(UPDATED_TELEPHONE)
            .dateDeNaissance(UPDATED_DATE_DE_NAISSANCE)
            .genre(UPDATED_GENRE)
            .typeDePaiement(UPDATED_TYPE_DE_PAIEMENT);

        restEleveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEleve.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEleve))
            )
            .andExpect(status().isOk());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeUpdate);
        Eleve testEleve = eleveList.get(eleveList.size() - 1);
        assertThat(testEleve.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testEleve.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEleve.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testEleve.getAdress()).isEqualTo(DEFAULT_ADRESS);
        assertThat(testEleve.getVille()).isEqualTo(DEFAULT_VILLE);
        assertThat(testEleve.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testEleve.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testEleve.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEleve.getDateDeNaissance()).isEqualTo(UPDATED_DATE_DE_NAISSANCE);
        assertThat(testEleve.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testEleve.getTypeDePaiement()).isEqualTo(UPDATED_TYPE_DE_PAIEMENT);
    }

    @Test
    @Transactional
    void fullUpdateEleveWithPatch() throws Exception {
        // Initialize the database
        eleveRepository.saveAndFlush(eleve);

        int databaseSizeBeforeUpdate = eleveRepository.findAll().size();

        // Update the eleve using partial update
        Eleve partialUpdatedEleve = new Eleve();
        partialUpdatedEleve.setId(eleve.getId());

        partialUpdatedEleve
            .matricule(UPDATED_MATRICULE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .adress(UPDATED_ADRESS)
            .ville(UPDATED_VILLE)
            .pays(UPDATED_PAYS)
            .telephone(UPDATED_TELEPHONE)
            .email(UPDATED_EMAIL)
            .dateDeNaissance(UPDATED_DATE_DE_NAISSANCE)
            .genre(UPDATED_GENRE)
            .typeDePaiement(UPDATED_TYPE_DE_PAIEMENT);

        restEleveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEleve.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEleve))
            )
            .andExpect(status().isOk());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeUpdate);
        Eleve testEleve = eleveList.get(eleveList.size() - 1);
        assertThat(testEleve.getMatricule()).isEqualTo(UPDATED_MATRICULE);
        assertThat(testEleve.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEleve.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testEleve.getAdress()).isEqualTo(UPDATED_ADRESS);
        assertThat(testEleve.getVille()).isEqualTo(UPDATED_VILLE);
        assertThat(testEleve.getPays()).isEqualTo(UPDATED_PAYS);
        assertThat(testEleve.getTelephone()).isEqualTo(UPDATED_TELEPHONE);
        assertThat(testEleve.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEleve.getDateDeNaissance()).isEqualTo(UPDATED_DATE_DE_NAISSANCE);
        assertThat(testEleve.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testEleve.getTypeDePaiement()).isEqualTo(UPDATED_TYPE_DE_PAIEMENT);
    }

    @Test
    @Transactional
    void patchNonExistingEleve() throws Exception {
        int databaseSizeBeforeUpdate = eleveRepository.findAll().size();
        eleve.setId(count.incrementAndGet());

        // Create the Eleve
        EleveDTO eleveDTO = eleveMapper.toDto(eleve);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEleveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, eleveDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(eleveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEleve() throws Exception {
        int databaseSizeBeforeUpdate = eleveRepository.findAll().size();
        eleve.setId(count.incrementAndGet());

        // Create the Eleve
        EleveDTO eleveDTO = eleveMapper.toDto(eleve);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEleveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(eleveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEleve() throws Exception {
        int databaseSizeBeforeUpdate = eleveRepository.findAll().size();
        eleve.setId(count.incrementAndGet());

        // Create the Eleve
        EleveDTO eleveDTO = eleveMapper.toDto(eleve);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEleveMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(eleveDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Eleve in the database
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEleve() throws Exception {
        // Initialize the database
        eleveRepository.saveAndFlush(eleve);

        int databaseSizeBeforeDelete = eleveRepository.findAll().size();

        // Delete the eleve
        restEleveMockMvc
            .perform(delete(ENTITY_API_URL_ID, eleve.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Eleve> eleveList = eleveRepository.findAll();
        assertThat(eleveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
