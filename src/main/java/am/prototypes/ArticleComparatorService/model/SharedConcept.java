package am.prototypes.ArticleComparatorService.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class SharedConcept {
    Concept concept;
    Set<Integer> documentOneParagraphIndizees;
    Set<Integer> documentTwoParagraphIndizees;

    public SharedConcept() {
        documentOneParagraphIndizees = new HashSet<>();
        documentTwoParagraphIndizees = new HashSet<>();
    }

    public SharedConcept(Concept concept, Set<Integer> documentOneParagraphIndizees, 
    		Set<Integer> documentTwoParagraphIndizees) {
        this.concept = concept;
        this.documentOneParagraphIndizees = documentOneParagraphIndizees;
        this.documentTwoParagraphIndizees = documentTwoParagraphIndizees;
    }

    public Concept getConcept() {
        return concept;
    }

    public void setConcept(Concept concept) {
        this.concept = concept;
    }

    public Set<Integer> getDocumentOneParagraphIndizees() {
		return documentOneParagraphIndizees;
	}

	public void setDocumentOneParagraphIndizees(Set<Integer> documentOneParagraphIndizees) {
		this.documentOneParagraphIndizees = documentOneParagraphIndizees;
	}

	public Set<Integer> getDocumentTwoParagraphIndizees() {
		return documentTwoParagraphIndizees;
	}

	public void setDocumentTwoParagraphIndizees(Set<Integer> documentTwoParagraphIndizees) {
		this.documentTwoParagraphIndizees = documentTwoParagraphIndizees;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharedConcept that = (SharedConcept) o;
        return Objects.equals(concept, that.concept) &&
                Objects.equals(documentOneParagraphIndizees, that.documentOneParagraphIndizees) &&
                Objects.equals(documentTwoParagraphIndizees, that.documentTwoParagraphIndizees);
    }

    @Override
    public int hashCode() {

        return Objects.hash(concept, documentOneParagraphIndizees, documentTwoParagraphIndizees);
    }
}
