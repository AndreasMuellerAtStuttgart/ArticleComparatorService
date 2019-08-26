package am.prototypes.ArticleComparatorService.services;

import am.prototypes.ArticleComparatorService.model.Concept;
import am.prototypes.ArticleComparatorService.model.Document;
import am.prototypes.ArticleComparatorService.model.Paragraph;
import am.prototypes.ArticleComparatorService.model.SharedConcept;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class ModelComputerService {
    public Document extractDocument(org.jsoup.nodes.Document htmlDocument, String url, String name) {
        if (htmlDocument == null || url == null || name == null) {
            throw new IllegalArgumentException("document can not be extracted with null arguments");
        }

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

    public Set<SharedConcept> extractSharedConcepts(Document documentOne, Document documentTwo) {
        if (documentOne == null || documentTwo == null) {
            throw new IllegalArgumentException("shared concepts can not be computed from one or both documents " +
                    "being null");
        }

        Map<Concept, Set<Integer>> conceptMappingDocOne = extractConceptMapping(documentOne);
        Map<Concept, Set<Integer>> conceptMappingDocTwo = extractConceptMapping(documentTwo);

        Set<SharedConcept> sharedConcepts = extractSharedConceptsFromConceptMappings(conceptMappingDocOne,
                conceptMappingDocTwo);

        return sharedConcepts;
    }

    private Set<SharedConcept> extractSharedConceptsFromConceptMappings(
            Map<Concept, Set<Integer>> conceptMappingDocOne, Map<Concept, Set<Integer>> conceptMappingDocTwo) {
        Set<SharedConcept> sharedConcepts = new HashSet<>();

        for (Concept concept: conceptMappingDocOne.keySet()) {
            if (conceptMappingDocTwo.containsKey(concept)) {
                SharedConcept sharedConcept = new SharedConcept();
                sharedConcept.setConcept(concept);

                Set<Integer> docOneParagraphIds = conceptMappingDocOne.get(concept);
                Set<Integer> docTwoParagraphIds = conceptMappingDocTwo.get(concept);

                sharedConcept.setDocumentOneParagraphIds(docOneParagraphIds);
                sharedConcept.setDocumentTwoParagraphIds(docTwoParagraphIds);

                sharedConcepts.add(sharedConcept);
            }
        }

        return sharedConcepts;
    }

    private Map<Concept, Set<Integer>> extractConceptMapping(Document document) {
        List<Paragraph> paragraphs = document.getParagraphs();

        Map<Concept, Set<Integer>> conceptMapping = new HashMap<>();

        for (int i=0;i<paragraphs.size();i++) {
            Paragraph paragraph = paragraphs.get(i);

            for (Concept concept: paragraph.getConcepts()) {
                if (conceptMapping.containsKey(concept)) {
                    conceptMapping.get(concept).add(i);
                } else {
                    Set<Integer> paragraphIds = new HashSet<>();
                    paragraphIds.add(i);

                    conceptMapping.put(concept, paragraphIds);
                }
            }
        }

        return conceptMapping;
    }
}
