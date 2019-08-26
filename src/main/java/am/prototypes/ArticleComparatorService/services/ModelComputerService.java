package am.prototypes.ArticleComparatorService.services;

import am.prototypes.ArticleComparatorService.model.Concept;
import am.prototypes.ArticleComparatorService.model.Document;
import am.prototypes.ArticleComparatorService.model.Paragraph;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;
import java.util.List;

public class ModelComputerService {
    public Document extractDocument(org.jsoup.nodes.Document htmlDocument, String url, String name) {
        Elements paragraphElements = htmlDocument.body().getElementsByTag("p");

        List<Paragraph> paragraphs = new LinkedList<>();

        for (Element element: paragraphElements) {
            Paragraph paragraph = new Paragraph();
            paragraph.setText(element.text());

            Elements conceptElements = element.getElementsByTag("a");
            List<Concept> concepts = new LinkedList<>();

            for (Element conceptElement: conceptElements) {
                Concept concept = new Concept();
                concept.setName(conceptElement.text());
                concept.setUrl(conceptElement.attr("href"));

                concepts.add(concept);
            }

            paragraph.setConcepts(concepts);

            paragraphs.add(paragraph);
        }

        Document document = new Document(name, url, paragraphs);

        return document;
    }
}
