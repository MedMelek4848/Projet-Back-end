package com.nourelmaaref.master.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nourelmaaref.master.IntegrationTest;
import com.nourelmaaref.master.domain.Examen;
import com.nourelmaaref.master.repository.ExamenRepository;
import com.nourelmaaref.master.service.ExamenService;
import com.nourelmaaref.master.service.dto.ExamenDTO;
import com.nourelmaaref.master.service.mapper.ExamenMapper;
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
 * Integration tests for the {@link ExamenResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ExamenResourceIT {

    private static final Integer DEFAULT_NUM_EXAMEN = 1;
    private static final Integer UPDATED_NUM_EXAMEN = 2;

    private static final Double DEFAULT_POURCENTAGE = 1D;
    private static final Double UPDATED_POURCENTAGE = 2D;

    private static final Boolean DEFAULT_VALIDE = false;
    private static final Boolean UPDATED_VALIDE = true;

    private static final LocalDate DEFAULT_DATE_EXAMEN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EXAMEN = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_DUREE = 1;
    private static final Integer UPDATED_DUREE = 2;

    private static final String DEFAULT_NOM_EXAMEN = "AAAAAAAAAA";
    private static final String UPDATED_NOM_EXAMEN = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/examen";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ExamenRepository examenRepository;

    @Mock
    private ExamenRepository examenRepositoryMock;

    @Autowired
    private ExamenMapper examenMapper;

    @Mock
    private ExamenService examenServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restExamenMockMvc;

    private Examen examen;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Examen createEntity(EntityManager em) {
        Examen examen = new Examen()
            .numExamen(DEFAULT_NUM_EXAMEN)
            .pourcentage(DEFAULT_POURCENTAGE)
            .valide(DEFAULT_VALIDE)
            .dateExamen(DEFAULT_DATE_EXAMEN)
            .duree(DEFAULT_DUREE)
            .nomExamen(DEFAULT_NOM_EXAMEN);
        return examen;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Examen createUpdatedEntity(EntityManager em) {
        Examen examen = new Examen()
            .numExamen(UPDATED_NUM_EXAMEN)
            .pourcentage(UPDATED_POURCENTAGE)
            .valide(UPDATED_VALIDE)
            .dateExamen(UPDATED_DATE_EXAMEN)
            .duree(UPDATED_DUREE)
            .nomExamen(UPDATED_NOM_EXAMEN);
        return examen;
    }

    @BeforeEach
    public void initTest() {
        examen = createEntity(em);
    }

    @Test
    @Transactional
    void createExamen() throws Exception {
        int databaseSizeBeforeCreate = examenRepository.findAll().size();
        // Create the Examen
        ExamenDTO examenDTO = examenMapper.toDto(examen);
        restExamenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(examenDTO)))
            .andExpect(status().isCreated());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeCreate + 1);
        Examen testExamen = examenList.get(examenList.size() - 1);
        assertThat(testExamen.getNumExamen()).isEqualTo(DEFAULT_NUM_EXAMEN);
        assertThat(testExamen.getPourcentage()).isEqualTo(DEFAULT_POURCENTAGE);
        assertThat(testExamen.getValide()).isEqualTo(DEFAULT_VALIDE);
        assertThat(testExamen.getDateExamen()).isEqualTo(DEFAULT_DATE_EXAMEN);
        assertThat(testExamen.getDuree()).isEqualTo(DEFAULT_DUREE);
        assertThat(testExamen.getNomExamen()).isEqualTo(DEFAULT_NOM_EXAMEN);
    }

    @Test
    @Transactional
    void createExamenWithExistingId() throws Exception {
        // Create the Examen with an existing ID
        examen.setId(1L);
        ExamenDTO examenDTO = examenMapper.toDto(examen);

        int databaseSizeBeforeCreate = examenRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restExamenMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(examenDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllExamen() throws Exception {
        // Initialize the database
        examenRepository.saveAndFlush(examen);

        // Get all the examenList
        restExamenMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(examen.getId().intValue())))
            .andExpect(jsonPath("$.[*].numExamen").value(hasItem(DEFAULT_NUM_EXAMEN)))
            .andExpect(jsonPath("$.[*].pourcentage").value(hasItem(DEFAULT_POURCENTAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].valide").value(hasItem(DEFAULT_VALIDE.booleanValue())))
            .andExpect(jsonPath("$.[*].dateExamen").value(hasItem(DEFAULT_DATE_EXAMEN.toString())))
            .andExpect(jsonPath("$.[*].duree").value(hasItem(DEFAULT_DUREE)))
            .andExpect(jsonPath("$.[*].nomExamen").value(hasItem(DEFAULT_NOM_EXAMEN)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllExamenWithEagerRelationshipsIsEnabled() throws Exception {
        when(examenServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restExamenMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(examenServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllExamenWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(examenServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restExamenMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(examenRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getExamen() throws Exception {
        // Initialize the database
        examenRepository.saveAndFlush(examen);

        // Get the examen
        restExamenMockMvc
            .perform(get(ENTITY_API_URL_ID, examen.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(examen.getId().intValue()))
            .andExpect(jsonPath("$.numExamen").value(DEFAULT_NUM_EXAMEN))
            .andExpect(jsonPath("$.pourcentage").value(DEFAULT_POURCENTAGE.doubleValue()))
            .andExpect(jsonPath("$.valide").value(DEFAULT_VALIDE.booleanValue()))
            .andExpect(jsonPath("$.dateExamen").value(DEFAULT_DATE_EXAMEN.toString()))
            .andExpect(jsonPath("$.duree").value(DEFAULT_DUREE))
            .andExpect(jsonPath("$.nomExamen").value(DEFAULT_NOM_EXAMEN));
    }

    @Test
    @Transactional
    void getNonExistingExamen() throws Exception {
        // Get the examen
        restExamenMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingExamen() throws Exception {
        // Initialize the database
        examenRepository.saveAndFlush(examen);

        int databaseSizeBeforeUpdate = examenRepository.findAll().size();

        // Update the examen
        Examen updatedExamen = examenRepository.findById(examen.getId()).get();
        // Disconnect from session so that the updates on updatedExamen are not directly saved in db
        em.detach(updatedExamen);
        updatedExamen
            .numExamen(UPDATED_NUM_EXAMEN)
            .pourcentage(UPDATED_POURCENTAGE)
            .valide(UPDATED_VALIDE)
            .dateExamen(UPDATED_DATE_EXAMEN)
            .duree(UPDATED_DUREE)
            .nomExamen(UPDATED_NOM_EXAMEN);
        ExamenDTO examenDTO = examenMapper.toDto(updatedExamen);

        restExamenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, examenDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(examenDTO))
            )
            .andExpect(status().isOk());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeUpdate);
        Examen testExamen = examenList.get(examenList.size() - 1);
        assertThat(testExamen.getNumExamen()).isEqualTo(UPDATED_NUM_EXAMEN);
        assertThat(testExamen.getPourcentage()).isEqualTo(UPDATED_POURCENTAGE);
        assertThat(testExamen.getValide()).isEqualTo(UPDATED_VALIDE);
        assertThat(testExamen.getDateExamen()).isEqualTo(UPDATED_DATE_EXAMEN);
        assertThat(testExamen.getDuree()).isEqualTo(UPDATED_DUREE);
        assertThat(testExamen.getNomExamen()).isEqualTo(UPDATED_NOM_EXAMEN);
    }

    @Test
    @Transactional
    void putNonExistingExamen() throws Exception {
        int databaseSizeBeforeUpdate = examenRepository.findAll().size();
        examen.setId(count.incrementAndGet());

        // Create the Examen
        ExamenDTO examenDTO = examenMapper.toDto(examen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExamenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, examenDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(examenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchExamen() throws Exception {
        int databaseSizeBeforeUpdate = examenRepository.findAll().size();
        examen.setId(count.incrementAndGet());

        // Create the Examen
        ExamenDTO examenDTO = examenMapper.toDto(examen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExamenMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(examenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamExamen() throws Exception {
        int databaseSizeBeforeUpdate = examenRepository.findAll().size();
        examen.setId(count.incrementAndGet());

        // Create the Examen
        ExamenDTO examenDTO = examenMapper.toDto(examen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExamenMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(examenDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateExamenWithPatch() throws Exception {
        // Initialize the database
        examenRepository.saveAndFlush(examen);

        int databaseSizeBeforeUpdate = examenRepository.findAll().size();

        // Update the examen using partial update
        Examen partialUpdatedExamen = new Examen();
        partialUpdatedExamen.setId(examen.getId());

        partialUpdatedExamen.pourcentage(UPDATED_POURCENTAGE).valide(UPDATED_VALIDE).duree(UPDATED_DUREE);

        restExamenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExamen.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExamen))
            )
            .andExpect(status().isOk());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeUpdate);
        Examen testExamen = examenList.get(examenList.size() - 1);
        assertThat(testExamen.getNumExamen()).isEqualTo(DEFAULT_NUM_EXAMEN);
        assertThat(testExamen.getPourcentage()).isEqualTo(UPDATED_POURCENTAGE);
        assertThat(testExamen.getValide()).isEqualTo(UPDATED_VALIDE);
        assertThat(testExamen.getDateExamen()).isEqualTo(DEFAULT_DATE_EXAMEN);
        assertThat(testExamen.getDuree()).isEqualTo(UPDATED_DUREE);
        assertThat(testExamen.getNomExamen()).isEqualTo(DEFAULT_NOM_EXAMEN);
    }

    @Test
    @Transactional
    void fullUpdateExamenWithPatch() throws Exception {
        // Initialize the database
        examenRepository.saveAndFlush(examen);

        int databaseSizeBeforeUpdate = examenRepository.findAll().size();

        // Update the examen using partial update
        Examen partialUpdatedExamen = new Examen();
        partialUpdatedExamen.setId(examen.getId());

        partialUpdatedExamen
            .numExamen(UPDATED_NUM_EXAMEN)
            .pourcentage(UPDATED_POURCENTAGE)
            .valide(UPDATED_VALIDE)
            .dateExamen(UPDATED_DATE_EXAMEN)
            .duree(UPDATED_DUREE)
            .nomExamen(UPDATED_NOM_EXAMEN);

        restExamenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedExamen.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedExamen))
            )
            .andExpect(status().isOk());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeUpdate);
        Examen testExamen = examenList.get(examenList.size() - 1);
        assertThat(testExamen.getNumExamen()).isEqualTo(UPDATED_NUM_EXAMEN);
        assertThat(testExamen.getPourcentage()).isEqualTo(UPDATED_POURCENTAGE);
        assertThat(testExamen.getValide()).isEqualTo(UPDATED_VALIDE);
        assertThat(testExamen.getDateExamen()).isEqualTo(UPDATED_DATE_EXAMEN);
        assertThat(testExamen.getDuree()).isEqualTo(UPDATED_DUREE);
        assertThat(testExamen.getNomExamen()).isEqualTo(UPDATED_NOM_EXAMEN);
    }

    @Test
    @Transactional
    void patchNonExistingExamen() throws Exception {
        int databaseSizeBeforeUpdate = examenRepository.findAll().size();
        examen.setId(count.incrementAndGet());

        // Create the Examen
        ExamenDTO examenDTO = examenMapper.toDto(examen);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restExamenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, examenDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(examenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchExamen() throws Exception {
        int databaseSizeBeforeUpdate = examenRepository.findAll().size();
        examen.setId(count.incrementAndGet());

        // Create the Examen
        ExamenDTO examenDTO = examenMapper.toDto(examen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExamenMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(examenDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamExamen() throws Exception {
        int databaseSizeBeforeUpdate = examenRepository.findAll().size();
        examen.setId(count.incrementAndGet());

        // Create the Examen
        ExamenDTO examenDTO = examenMapper.toDto(examen);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restExamenMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(examenDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Examen in the database
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteExamen() throws Exception {
        // Initialize the database
        examenRepository.saveAndFlush(examen);

        int databaseSizeBeforeDelete = examenRepository.findAll().size();

        // Delete the examen
        restExamenMockMvc
            .perform(delete(ENTITY_API_URL_ID, examen.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Examen> examenList = examenRepository.findAll();
        assertThat(examenList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
