package am.prototypes.ArticleComparatorService.services;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.util.ResourceUtils;

import am.prototypes.ArticleComparatorService.model.Concept;
import am.prototypes.ArticleComparatorService.model.Document;
import am.prototypes.ArticleComparatorService.model.Paragraph;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class DocumentExtractorServiceTest {
	private static final DocumentExtractorService service = new DocumentExtractorService();
    private static final String PARAGRAPH_ONE_TEXT  = "This is a test paragraph containing "
    		+ "Link_1. And it contains a span.";
    private static final String PARAGRAPH_TWO_TEXT = "This is a second test paragraph "
    		+ "without a link.";
    private static final String PARAGRAPH_THREE_TEXT = "";
    private static final String PARAGRAPH_FOUR_TEXT = "And another paragraph with Link_2 "
    		+ "and Link_3.";
    
    private final String MOCK_URL = "http://www.google.com";
    
    @Mock
    Connection mockConnection;
	
	@Test(expected = IllegalArgumentException.class)
    public void testExtractDocumentFirstArgumentNull() throws IOException {
        service.extractDocument(null);
	}

    @Test(expected = IOException.class)
    public void testExtractDocumentNonExistentUrl() throws IOException {
        service.extractDocument("http://some_non_existing_url.com");
    }
    
    private List<Paragraph> makeParagraphsWithConcepts(Concept conceptOne, Concept conceptTwo, 
    		Concept conceptThree) {
    	List<Paragraph> paragraphs = new LinkedList<>();
    	
    	Paragraph paragraphOne = new Paragraph();
        paragraphOne.setText(PARAGRAPH_ONE_TEXT);
        paragraphOne.getConcepts().add(conceptOne);

        Paragraph paragraphTwo = new Paragraph();
        paragraphTwo.setText(PARAGRAPH_TWO_TEXT);

        Paragraph paragraphThree = new Paragraph();
        paragraphThree.setText(PARAGRAPH_THREE_TEXT);

        Paragraph paragraphFour = new Paragraph();
        paragraphFour.setText(PARAGRAPH_FOUR_TEXT);
        paragraphFour.getConcepts().add(conceptTwo);
        paragraphFour.getConcepts().add(conceptThree);
        
        paragraphs.add(paragraphOne);
        paragraphs.add(paragraphTwo);
        paragraphs.add(paragraphThree);
        paragraphs.add(paragraphFour);
        
        return paragraphs;
    }
    
    private Document constructExpectedDocument() {
    	Document document = new Document();
    	
    	Concept conceptOne = new Concept("Link_1", "link_1_href");
        Concept conceptTwo = new Concept("Link_2", "link_2_href");
        Concept conceptThree = new Concept("Link_3", "link_3_href");

        document.setParagraphs(makeParagraphsWithConcepts(conceptOne, conceptTwo, 
        		conceptThree));
        
        document.setUrl(MOCK_URL);
        
        return document;
    }
    
    @Test
    public void testExtractDocument() throws IOException {
        Document expectedDocument = constructExpectedDocument();

        File file = ResourceUtils.getFile("classpath:test.html");
        org.jsoup.nodes.Document htmlDocument = Jsoup.parse(file, "UTF-8", "");

        PowerMockito.mockStatic(Jsoup.class);
        Mockito.when(Jsoup.connect(MOCK_URL)).thenReturn(mockConnection);
        Mockito.when(mockConnection.get()).thenReturn(htmlDocument);
        
        Document actualDocument = service.extractDocument(MOCK_URL);

        assertEquals(expectedDocument, actualDocument);
    }
}
