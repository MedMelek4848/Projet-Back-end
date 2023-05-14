package com.nourelmaaref.master.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.nourelmaaref.master.IntegrationTest;
import com.nourelmaaref.master.domain.Cours;
import com.nourelmaaref.master.repository.CoursRepository;
import com.nourelmaaref.master.service.CoursService;
import com.nourelmaaref.master.service.dto.CoursDTO;
import com.nourelmaaref.master.service.mapper.CoursMapper;
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
 * Integration tests for the {@link CoursResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class CoursResourceIT {

    private static final Integer DEFAULT_NUMERO_COURS = 1;
    private static final Integer UPDATED_NUMERO_COURS = 2;

    private static final LocalDate DEFAULT_DATE_COUR = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_COUR = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/cours";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CoursRepository coursRepository;

    @Mock
    private CoursRepository coursRepositoryMock;

    @Autowired
    private CoursMapper coursMapper;

    @Mock
    private CoursService coursServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCoursMockMvc;

    private Cours cours;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cours createEntity(EntityManager em) {
        Cours cours = new Cours().numeroCours(DEFAULT_NUMERO_COURS).dateCour(DEFAULT_DATE_COUR);
        return cours;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cours createUpdatedEntity(EntityManager em) {
        Cours cours = new Cours().numeroCours(UPDATED_NUMERO_COURS).dateCour(UPDATED_DATE_COUR);
        return cours;
    }

    @BeforeEach
    public void initTest() {
        cours = createEntity(em);
    }

    @Test
    @Transactional
    void createCours() throws Exception {
        int databaseSizeBeforeCreate = coursRepository.findAll().size();
        // Create the Cours
        CoursDTO coursDTO = coursMapper.toDto(cours);
        restCoursMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coursDTO)))
            .andExpect(status().isCreated());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeCreate + 1);
        Cours testCours = coursList.get(coursList.size() - 1);
        assertThat(testCours.getNumeroCours()).isEqualTo(DEFAULT_NUMERO_COURS);
        assertThat(testCours.getDateCour()).isEqualTo(DEFAULT_DATE_COUR);
    }

    @Test
    @Transactional
    void createCoursWithExistingId() throws Exception {
        // Create the Cours with an existing ID
        cours.setId(1L);
        CoursDTO coursDTO = coursMapper.toDto(cours);

        int databaseSizeBeforeCreate = coursRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCoursMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coursDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCours() throws Exception {
        // Initialize the database
        coursRepository.saveAndFlush(cours);

        // Get all the coursList
        restCoursMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cours.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroCours").value(hasItem(DEFAULT_NUMERO_COURS)))
            .andExpect(jsonPath("$.[*].dateCour").value(hasItem(DEFAULT_DATE_COUR.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCoursWithEagerRelationshipsIsEnabled() throws Exception {
        when(coursServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCoursMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(coursServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCoursWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(coursServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCoursMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(coursRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getCours() throws Exception {
        // Initialize the database
        coursRepository.saveAndFlush(cours);

        // Get the cours
        restCoursMockMvc
            .perform(get(ENTITY_API_URL_ID, cours.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cours.getId().intValue()))
            .andExpect(jsonPath("$.numeroCours").value(DEFAULT_NUMERO_COURS))
            .andExpect(jsonPath("$.dateCour").value(DEFAULT_DATE_COUR.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCours() throws Exception {
        // Get the cours
        restCoursMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCours() throws Exception {
        // Initialize the database
        coursRepository.saveAndFlush(cours);

        int databaseSizeBeforeUpdate = coursRepository.findAll().size();

        // Update the cours
        Cours updatedCours = coursRepository.findById(cours.getId()).get();
        // Disconnect from session so that the updates on updatedCours are not directly saved in db
        em.detach(updatedCours);
        updatedCours.numeroCours(UPDATED_NUMERO_COURS).dateCour(UPDATED_DATE_COUR);
        CoursDTO coursDTO = coursMapper.toDto(updatedCours);

        restCoursMockMvc
            .perform(
                put(ENTITY_API_URL_ID, coursDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coursDTO))
            )
            .andExpect(status().isOk());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeUpdate);
        Cours testCours = coursList.get(coursList.size() - 1);
        assertThat(testCours.getNumeroCours()).isEqualTo(UPDATED_NUMERO_COURS);
        assertThat(testCours.getDateCour()).isEqualTo(UPDATED_DATE_COUR);
    }

    @Test
    @Transactional
    void putNonExistingCours() throws Exception {
        int databaseSizeBeforeUpdate = coursRepository.findAll().size();
        cours.setId(count.incrementAndGet());

        // Create the Cours
        CoursDTO coursDTO = coursMapper.toDto(cours);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoursMockMvc
            .perform(
                put(ENTITY_API_URL_ID, coursDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coursDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCours() throws Exception {
        int databaseSizeBeforeUpdate = coursRepository.findAll().size();
        cours.setId(count.incrementAndGet());

        // Create the Cours
        CoursDTO coursDTO = coursMapper.toDto(cours);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoursMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(coursDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCours() throws Exception {
        int databaseSizeBeforeUpdate = coursRepository.findAll().size();
        cours.setId(count.incrementAndGet());

        // Create the Cours
        CoursDTO coursDTO = coursMapper.toDto(cours);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoursMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(coursDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCoursWithPatch() throws Exception {
        // Initialize the database
        coursRepository.saveAndFlush(cours);

        int databaseSizeBeforeUpdate = coursRepository.findAll().size();

        // Update the cours using partial update
        Cours partialUpdatedCours = new Cours();
        partialUpdatedCours.setId(cours.getId());

        restCoursMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCours.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCours))
            )
            .andExpect(status().isOk());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeUpdate);
        Cours testCours = coursList.get(coursList.size() - 1);
        assertThat(testCours.getNumeroCours()).isEqualTo(DEFAULT_NUMERO_COURS);
        assertThat(testCours.getDateCour()).isEqualTo(DEFAULT_DATE_COUR);
    }

    @Test
    @Transactional
    void fullUpdateCoursWithPatch() throws Exception {
        // Initialize the database
        coursRepository.saveAndFlush(cours);

        int databaseSizeBeforeUpdate = coursRepository.findAll().size();

        // Update the cours using partial update
        Cours partialUpdatedCours = new Cours();
        partialUpdatedCours.setId(cours.getId());

        partialUpdatedCours.numeroCours(UPDATED_NUMERO_COURS).dateCour(UPDATED_DATE_COUR);

        restCoursMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCours.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCours))
            )
            .andExpect(status().isOk());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeUpdate);
        Cours testCours = coursList.get(coursList.size() - 1);
        assertThat(testCours.getNumeroCours()).isEqualTo(UPDATED_NUMERO_COURS);
        assertThat(testCours.getDateCour()).isEqualTo(UPDATED_DATE_COUR);
    }

    @Test
    @Transactional
    void patchNonExistingCours() throws Exception {
        int databaseSizeBeforeUpdate = coursRepository.findAll().size();
        cours.setId(count.incrementAndGet());

        // Create the Cours
        CoursDTO coursDTO = coursMapper.toDto(cours);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCoursMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, coursDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(coursDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCours() throws Exception {
        int databaseSizeBeforeUpdate = coursRepository.findAll().size();
        cours.setId(count.incrementAndGet());

        // Create the Cours
        CoursDTO coursDTO = coursMapper.toDto(cours);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoursMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(coursDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCours() throws Exception {
        int databaseSizeBeforeUpdate = coursRepository.findAll().size();
        cours.setId(count.incrementAndGet());

        // Create the Cours
        CoursDTO coursDTO = coursMapper.toDto(cours);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCoursMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(coursDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cours in the database
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCours() throws Exception {
        // Initialize the database
        coursRepository.saveAndFlush(cours);

        int databaseSizeBeforeDelete = coursRepository.findAll().size();

        // Delete the cours
        restCoursMockMvc
            .perform(delete(ENTITY_API_URL_ID, cours.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Cours> coursList = coursRepository.findAll();
        assertThat(coursList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
