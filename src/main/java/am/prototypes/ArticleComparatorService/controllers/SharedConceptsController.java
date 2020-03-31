package am.prototypes.ArticleComparatorService.controllers;

import am.prototypes.ArticleComparatorService.dtos.SharedConceptsDTO;
import am.prototypes.ArticleComparatorService.model.Document;
import am.prototypes.ArticleComparatorService.model.SharedConcept;
import am.prototypes.ArticleComparatorService.services.DocumentExtractorService;
import am.prototypes.ArticleComparatorService.services.SharedConceptsService;

import org.jsoup.Jsoup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
public class SharedConceptsController {
    DocumentExtractorService documentExtractor;
    SharedConceptsService sharedConceptsService;

    public SharedConceptsController(DocumentExtractorService documentExtractor,
			SharedConceptsService sharedConceptsService) {
		super();
		this.documentExtractor = documentExtractor;
		this.sharedConceptsService = sharedConceptsService;
	}
    
	@GetMapping("/getSharedConcepts")
    public ResponseEntity<SharedConceptsDTO> getSharedConcepts(
    		@RequestParam("documentOneUrl") String documentOneUrl, 
    		@RequestParam("documentTwoUrl") String documentTwoUrl) {
        SharedConceptsDTO sharedConceptsDTO = new SharedConceptsDTO();

        try {
            Document documentOne = documentExtractor.extractDocument(documentOneUrl);
            Document documentTwo = documentExtractor.extractDocument(documentTwoUrl);

            Set<SharedConcept> sharedConcepts = sharedConceptsService.extractSharedConcepts(
            		documentOne, documentTwo);

            sharedConceptsDTO.setDocumentOne(documentOne);
            sharedConceptsDTO.setDocumentTwo(documentTwo);
            sharedConceptsDTO.setSharedConcepts(sharedConcepts);

            return new ResponseEntity<SharedConceptsDTO>(sharedConceptsDTO, HttpStatus.OK);
        } catch (IOException e) {
        	return new ResponseEntity<SharedConceptsDTO>(sharedConceptsDTO, 
        			HttpStatus.BAD_REQUEST);
        }
    }
}
