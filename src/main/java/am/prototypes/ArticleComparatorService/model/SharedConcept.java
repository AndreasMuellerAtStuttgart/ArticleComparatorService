package am.prototypes.ArticleComparatorService.model;

import java.util.List;
import java.util.Objects;

public class SharedConcept {
    Concept concept;
    List<Integer> documentOneParagraphIds;
    List<Integer> documentTwoParagraphIds;

    public SharedConcept(Concept concept, List<Integer> documentOneParagraphIds, List<Integer> documentTwoParagraphIds) {
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

    public List<Integer> getDocumentOneParagraphIds() {
        return documentOneParagraphIds;
    }

    public void setDocumentOneParagraphIds(List<Integer> documentOneParagraphIds) {
        this.documentOneParagraphIds = documentOneParagraphIds;
    }

    public List<Integer> getDocumentTwoParagraphIds() {
        return documentTwoParagraphIds;
    }

    public void setDocumentTwoParagraphIds(List<Integer> documentTwoParagraphIds) {
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
