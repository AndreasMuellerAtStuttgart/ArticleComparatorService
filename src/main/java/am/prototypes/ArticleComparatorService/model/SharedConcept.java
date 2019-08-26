package am.prototypes.ArticleComparatorService.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SharedConcept {
    Concept concept;
    Set<Integer> documentOneParagraphIds;
    Set<Integer> documentTwoParagraphIds;

    public SharedConcept() {
        documentOneParagraphIds = new HashSet<>();
        documentTwoParagraphIds = new HashSet<>();
    }

    public SharedConcept(Concept concept, Set<Integer> documentOneParagraphIds, Set<Integer> documentTwoParagraphIds) {
        this.concept = concept;
        this.documentOneParagraphIds = documentOneParagraphIds;
        this.documentTwoParagraphIds = documentTwoParagraphIds;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Set<Integer> getDocumentOneParagraphIds() {
        return documentOneParagraphIds;
    }

    public void setDocumentOneParagraphIds(Set<Integer> documentOneParagraphIds) {
        this.documentOneParagraphIds = documentOneParagraphIds;
    }

    public Set<Integer> getDocumentTwoParagraphIds() {
        return documentTwoParagraphIds;
    }

    public void setDocumentTwoParagraphIds(Set<Integer> documentTwoParagraphIds) {
        this.documentTwoParagraphIds = documentTwoParagraphIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedConcept that = (SharedConcept) o;
        return Objects.equals(concept, that.concept) &&
                Objects.equals(documentOneParagraphIds, that.documentOneParagraphIds) &&
                Objects.equals(documentTwoParagraphIds, that.documentTwoParagraphIds);
    }

    @Override
    public int hashCode() {

        return Objects.hash(concept, documentOneParagraphIds, documentTwoParagraphIds);
    }
}
