package am.prototypes.ArticleComparatorService.services;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import am.prototypes.ArticleComparatorService.model.Concept;
import am.prototypes.ArticleComparatorService.model.Document;
import am.prototypes.ArticleComparatorService.model.Paragraph;

@Service
public class DocumentExtractorService {

	private final String PARAGRAPH_TAG = "p";
	private final String LINK_TAG = "a";
	private final String HYPERLINK_ATTRIBUTE = "href";
	
    public Document extractDocument(String url) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("Invalid argument url = null");
        }
        
        org.jsoup.nodes.Document htmlDocument;
        
		try {
			htmlDocument = Jsoup.connect(url).get();
		} catch (IOException e) {
			throw e;
		}

        Elements paragraphElements = htmlDocument.body().getElementsByTag(PARAGRAPH_TAG);

        List<Paragraph> paragraphs = extractParagraphsFromParagraphElements(paragraphElements);

        Document document = new Document(url, paragraphs);

        return document;
    }
    
    private List<Paragraph> extractParagraphsFromParagraphElements(Elements paragraphElements) {
    	List<Paragraph> paragraphs = new LinkedList<>();
    	
    	paragraphElements.forEach((paragraphElement) -> {
    		paragraphs.add(extractParagraphFromParagraphElement(paragraphElement));
    	});
    	
    	return paragraphs;
    }
    
    private Paragraph extractParagraphFromParagraphElement(Element paragraphElement) {
    	Paragraph paragraph = new Paragraph();
        paragraph.setText(paragraphElement.text());

        List<Concept> concepts = extractConceptsFromParagraphElement(paragraphElement);

        paragraph.setConcepts(concepts);
        
        return paragraph;
    }
    
    private List<Concept> extractConceptsFromParagraphElement(Element paragraphElement) {
    	Elements conceptElements = paragraphElement.getElementsByTag(LINK_TAG);
    	List<Concept> concepts = new LinkedList<>();
    	
    	conceptElements.forEach((conceptElement) -> {
            concepts.add(extractConceptFromConceptElement(conceptElement));
    	});
    	
    	return concepts;
    }
    
    private Concept extractConceptFromConceptElement(Element conceptElement) {
    	Concept concept = new Concept();
        concept.setName(conceptElement.text());
        concept.setUrl(conceptElement.attr(HYPERLINK_ATTRIBUTE));
        
        return concept;
    }
}
