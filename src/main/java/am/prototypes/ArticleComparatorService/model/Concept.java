package am.prototypes.ArticleComparatorService.model;

import java.util.Objects;

/**
 * Equality of concepts is only determined by url because the same article can be referenced with different texts
 */
public class Concept {
    String name;
    String url;

    public Concept() {
    }

    public Concept(String name, String url) {
        this.name = name;
        this.url = url;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Concept concept = (Concept) o;
        return Objects.equals(url, concept.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(url);
    }
}
