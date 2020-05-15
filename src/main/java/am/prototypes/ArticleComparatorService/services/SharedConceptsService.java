package am.prototypes.ArticleComparatorService.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import am.prototypes.ArticleComparatorService.model.Concept;
import am.prototypes.ArticleComparatorService.model.Document;
import am.prototypes.ArticleComparatorService.model.Paragraph;
import am.prototypes.ArticleComparatorService.model.SharedConcept;

@Service
public class SharedConceptsService {
	public Set<SharedConcept> extractSharedConcepts(Document documentOne, Document documentTwo) {
        if (documentOne == null || documentTwo == null) {
            throw new IllegalArgumentException("shared concepts can not be computed from one or "
            		+ "both documents being null");
        }

        Map<Concept, Set<Integer>> conceptMappingDocOne = extractConceptToParagraphIndizeesMapping(
        		documentOne);
        Map<Concept, Set<Integer>> conceptMappingDocTwo = extractConceptToParagraphIndizeesMapping(
        		documentTwo);

        Set<SharedConcept> sharedConcepts = extractSharedConceptsFromConceptMappings(
        		conceptMappingDocOne, conceptMappingDocTwo);

        return sharedConcepts;
    }

    private Set<SharedConcept> extractSharedConceptsFromConceptMappings(
            Map<Concept, Set<Integer>> conceptMappingDocOne, Map<Concept, Set<Integer>> 
            		conceptMappingDocTwo) {
        Set<SharedConcept> sharedConcepts = new HashSet<>();

        conceptMappingDocOne.keySet().forEach((concept) -> {
        	if (conceptMappingDocTwo.containsKey(concept)) {
        		Set<Integer> docOneParagraphIds = conceptMappingDocOne.get(concept);
                Set<Integer> docTwoParagraphIds = conceptMappingDocTwo.get(concept);
        		
        		sharedConcepts.add(makeSharedConcept(docOneParagraphIds, docTwoParagraphIds, 
        				concept));
        	}
        });

        return sharedConcepts;
    }
    
    private SharedConcept makeSharedConcept(Set<Integer> docOneParagraphIds, 
    		Set<Integer> docTwoParagraphIds, Concept concept) {
    	SharedConcept sharedConcept = new SharedConcept();
        sharedConcept.setConcept(concept);

        sharedConcept.setDocumentOneParagraphIndizees(docOneParagraphIds);
        sharedConcept.setDocumentTwoParagraphIndizees(docTwoParagraphIds);
    
        return sharedConcept;
    }

    private Map<Concept, Set<Integer>> extractConceptToParagraphIndizeesMapping(Document document) {
        List<Paragraph> paragraphs = document.getParagraphs();

        Map<Concept, Set<Integer>> conceptToParagraphIndizees = new HashMap<>();

        for (int i=0;i<paragraphs.size();i++) {
            Paragraph paragraph = paragraphs.get(i);
            final int index = i;
            
            paragraph.getConcepts().forEach((concept) -> {
            	addParagraphIndexToConceptsContainedInParagraph(conceptToParagraphIndizees, 
                		concept, index);
            });
        }

        return conceptToParagraphIndizees;
    }
    
    private void addParagraphIndexToConceptsContainedInParagraph(
    		Map<Concept, Set<Integer>> conceptToParagraphIndizees, Concept concept, int index) {
    	if (conceptToParagraphIndizees.containsKey(concept)) {
            conceptToParagraphIndizees.get(concept).add(index);
        } else {
            Set<Integer> paragraphIds = new HashSet<>();
            paragraphIds.add(index);

            conceptToParagraphIndizees.put(concept, paragraphIds);
        }
    }
}
