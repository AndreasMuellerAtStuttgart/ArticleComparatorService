package am.prototypes.ArticleComparatorService.model;

import org.springframework.data.annotation.Id;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Document {
    String name;
    @Id
    String url;
    List<Paragraph> paragraphs;

    public Document() {
        paragraphs = new LinkedList<>();
    }

    public Document(String name, String url, List<Paragraph> paragraphs) {
        this.name = name;
        this.url = url;
        this.paragraphs = paragraphs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(name, document.name) &&
                Objects.equals(url, document.url) &&
                Objects.equals(paragraphs, document.paragraphs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, url, paragraphs);
    }
}
