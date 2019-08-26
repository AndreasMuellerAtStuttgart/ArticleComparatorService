package am.prototypes.ArticleComparatorService.model;

import java.util.Set;

public class SharedConcepts {
    Document documentOne;
    Document documentTwo;
    Set<SharedConcept> sharedConcepts;
    boolean wasMadeSuccessfully;

    public SharedConcepts() {
    }

    public SharedConcepts(Document documentOne, Document documentTwo, Set<SharedConcept> sharedConcepts, boolean wasMadeSuccessfully) {
        this.documentOne = documentOne;
        this.documentTwo = documentTwo;
        this.sharedConcepts = sharedConcepts;
        this.wasMadeSuccessfully = wasMadeSuccessfully;
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

    public boolean isWasMadeSuccessfully() {
        return wasMadeSuccessfully;
    }

    public void setWasMadeSuccessfully(boolean wasMadeSuccessfully) {
        this.wasMadeSuccessfully = wasMadeSuccessfully;
    }
}
