package am.prototypes.ArticleComparatorService.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Paragraph {
    String text;
    List<Concept> concepts;

    public Paragraph() {
        concepts = new LinkedList<>();
    }

    public Paragraph(String text, List<Concept> concepts) {
        this.text = text;
        this.concepts = concepts;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Concept> getConcepts() {
        return concepts;
    }

    public void setConcepts(List<Concept> concepts) {
        this.concepts = concepts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paragraph paragraph = (Paragraph) o;
        return Objects.equals(text, paragraph.text) &&
                Objects.equals(concepts, paragraph.concepts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(text, concepts);
    }
}
