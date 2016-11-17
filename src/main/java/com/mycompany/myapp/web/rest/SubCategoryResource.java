package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.SubCategory;
import com.mycompany.myapp.repository.SubCategoryRepository;
import com.mycompany.myapp.repository.search.SubCategorySearchRepository;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing SubCategory.
 */
@RestController
@RequestMapping("/api")
public class SubCategoryResource {

    private final Logger log = LoggerFactory.getLogger(SubCategoryResource.class);
        
    @Inject
    private SubCategoryRepository subCategoryRepository;
    
    @Inject
    private SubCategorySearchRepository subCategorySearchRepository;
    
    /**
     * POST  /sub-categories : Create a new subCategory.
     *
     * @param subCategory the subCategory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subCategory, or with status 400 (Bad Request) if the subCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sub-categories",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SubCategory> createSubCategory(@Valid @RequestBody SubCategory subCategory) throws URISyntaxException {
        log.debug("REST request to save SubCategory : {}", subCategory);
        if (subCategory.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("subCategory", "idexists", "A new subCategory cannot already have an ID")).body(null);
        }
        ZonedDateTime datetime = ZonedDateTime.now(); 
        subCategory.setCreated_date(datetime);
        subCategory.setCreated_by(SecurityUtils.getCurrentLogin());
        SubCategory result = subCategoryRepository.save(subCategory);
        subCategorySearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sub-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("subCategory", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-categories : Updates an existing subCategory.
     *
     * @param subCategory the subCategory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subCategory,
     * or with status 400 (Bad Request) if the subCategory is not valid,
     * or with status 500 (Internal Server Error) if the subCategory couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/sub-categories",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SubCategory> updateSubCategory(@Valid @RequestBody SubCategory subCategory) throws URISyntaxException {
        log.debug("REST request to update SubCategory : {}", subCategory);
        if (subCategory.getId() == null) {
            return createSubCategory(subCategory);
        }
        SubCategory result = subCategoryRepository.save(subCategory);
        subCategorySearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("subCategory", subCategory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sub-categories : get all the subCategories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of subCategories in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/sub-categories",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SubCategory>> getAllSubCategories(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of SubCategories");
        Page<SubCategory> page = subCategoryRepository.findAll(pageable); 
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sub-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /sub-categories/:id : get the "id" subCategory.
     *
     * @param id the id of the subCategory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subCategory, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/sub-categories/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<SubCategory> getSubCategory(@PathVariable Long id) {
        log.debug("REST request to get SubCategory : {}", id);
        SubCategory subCategory = subCategoryRepository.findOne(id);
        return Optional.ofNullable(subCategory)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /sub-categories/:id : delete the "id" subCategory.
     *
     * @param id the id of the subCategory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/sub-categories/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long id) {
        log.debug("REST request to delete SubCategory : {}", id);
        subCategoryRepository.delete(id);
        subCategorySearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("subCategory", id.toString())).build();
    }

    /**
     * SEARCH  /_search/sub-categories?query=:query : search for the subCategory corresponding
     * to the query.
     *
     * @param query the query of the subCategory search
     * @return the result of the search
     */
    @RequestMapping(value = "/_search/sub-categories",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<SubCategory>> searchSubCategories(@RequestParam String query, Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to search for a page of SubCategories for query {}", query);
        Page<SubCategory> page = subCategorySearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sub-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
