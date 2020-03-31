package am.prototypes.ArticleComparatorService.dtos;

import java.util.Set;

import am.prototypes.ArticleComparatorService.model.Document;
import am.prototypes.ArticleComparatorService.model.SharedConcept;

public class SharedConceptsDTO {
    Document documentOne;
    Document documentTwo;
    Set<SharedConcept> sharedConcepts;

    public SharedConceptsDTO() {
    }

    public SharedConceptsDTO(Document documentOne, Document documentTwo, 
    		Set<SharedConcept> sharedConcepts) {
        this.documentOne = documentOne;
        this.documentTwo = documentTwo;
        this.sharedConcepts = sharedConcepts;
    }

    public Document getDocumentOne() {
        return documentOne;
    }

    public void setDocumentOne(Document documentOne) {
        this.documentOne = documentOne;
    }

    public Document getDocumentTwo() {
        return documentTwo;
    }

    public void setDocumentTwo(Document documentTwo) {
        this.documentTwo = documentTwo;
    }

    public Set<SharedConcept> getSharedConcepts() {
        return sharedConcepts;
    }

    public void setSharedConcepts(Set<SharedConcept> sharedConcepts) {
        this.sharedConcepts = sharedConcepts;
    }
}
