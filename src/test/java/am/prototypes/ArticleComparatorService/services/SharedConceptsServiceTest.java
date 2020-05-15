package am.prototypes.ArticleComparatorService.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import am.prototypes.ArticleComparatorService.model.Concept;
import am.prototypes.ArticleComparatorService.model.Document;
import am.prototypes.ArticleComparatorService.model.Paragraph;
import am.prototypes.ArticleComparatorService.model.SharedConcept;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class SharedConceptsServiceTest {
	private SharedConceptsService sharedConceptsService = new SharedConceptsService();
	
	private final String MOCK_URL_DOCUMENT_ONE = "mock_url_1";
	private final String MOCK_URL_DOCUMENT_TWO = "mock_url_2";
	
	private static Concept conceptOne = new Concept();
	private static Concept conceptTwo = new Concept();
	private static Concept conceptThree = new Concept();
	private static Concept conceptFour = new Concept();
	
	@Mock
	Connection mockConnectionOne;
	
	@Mock
	Connection mockConnectionTwo;
	
	@BeforeClass
	public static void setup() {
    	conceptOne.setName("Link_1");
    	conceptOne.setUrl("link_1_href");
    	
    	conceptTwo.setName("Link_2");
    	conceptTwo.setUrl("link_2_href");
    	
    	conceptThree.setName("Link_3");
    	conceptThree.setUrl("link_3_href");
    	
    	conceptFour.setName("Link_4");
    	conceptFour.setUrl("link_4_href");
	}
	
    @Test(expected = IllegalArgumentException.class)
    public void extractSharedConceptsFirstArgumentNull() {
        sharedConceptsService.extractSharedConcepts(null, new Document());
    }

    @Test(expected = IllegalArgumentException.class)
    public void extractSharedConceptsSecondArgumentNull() {
        sharedConceptsService.extractSharedConcepts(new Document(), null);
    }
    
    @Test
    public void extractSharedConcepts() throws IOException {
    	Document documentOne = makeDocumentOne();
    	Document documentTwo = makeDocumentTwo();
    	
        Set<SharedConcept> expectedSharedConcepts = makeExpectedSharedConcepts();
        
        Set<SharedConcept> actualSharedConcepts = sharedConceptsService.extractSharedConcepts(
        		documentOne, documentTwo);

        Assert.assertEquals(expectedSharedConcepts, actualSharedConcepts);
    }
    
    private Document makeDocumentOne() {
    	Document document = new Document();
    	
    	List<Paragraph> paragraphs = new LinkedList<>();
    	
    	Paragraph paragraphOne = new Paragraph();
    	paragraphOne.setText("P1");
    	paragraphOne.getConcepts().add(conceptOne);
    	paragraphOne.getConcepts().add(conceptTwo);
    	
    	Paragraph paragraphTwo = new Paragraph();
    	paragraphTwo.setText("P2");
    	paragraphTwo.getConcepts().add(conceptOne);
    	
    	Paragraph paragraphThree = new Paragraph();
    	paragraphThree.setText("P3");
    	paragraphThree.setConcepts(new LinkedList<>());
    	
    	Paragraph paragraphFour = new Paragraph();
    	paragraphFour.setText("P4");
    	paragraphFour.getConcepts().add(conceptOne);
    	paragraphFour.getConcepts().add(conceptTwo);
    	paragraphFour.getConcepts().add(conceptThree);
    	
    	paragraphs.add(paragraphOne);
    	paragraphs.add(paragraphTwo);
    	paragraphs.add(paragraphThree);
    	paragraphs.add(paragraphFour);
    	
    	document.setParagraphs(paragraphs);
    	document.setUrl(MOCK_URL_DOCUMENT_ONE);
    	
    	return document;
    }
    
    private Document makeDocumentTwo() {
    	Document document = new Document();
    	
    	List<Paragraph> paragraphs = new LinkedList<>();
    	
    	Paragraph paragraphOne = new Paragraph();
    	paragraphOne.setText("P1");
    	paragraphOne.getConcepts().add(conceptOne);
    	
    	Paragraph paragraphTwo = new Paragraph();
    	paragraphTwo.setText("P2");
    	paragraphTwo.getConcepts().add(conceptOne);
    	
    	Paragraph paragraphThree = new Paragraph();
    	paragraphThree.setText("");
    	paragraphThree.setConcepts(new LinkedList<>());
    	
    	Paragraph paragraphFour = new Paragraph();
    	paragraphFour.setText("P4");
    	paragraphFour.getConcepts().add(conceptTwo);
    	paragraphFour.getConcepts().add(conceptThree);
    	
    	paragraphs.add(paragraphOne);
    	paragraphs.add(paragraphTwo);
    	paragraphs.add(paragraphThree);
    	paragraphs.add(paragraphFour);
    	
    	document.setParagraphs(paragraphs);
    	document.setUrl(MOCK_URL_DOCUMENT_TWO);
    	
    	return document;
    }
    
    private Set<SharedConcept> makeExpectedSharedConcepts() {
    	Set<SharedConcept> sharedConcepts = new HashSet<>();
    	
    	SharedConcept sharedConceptOne = new SharedConcept();
        sharedConceptOne.setConcept(conceptOne);
        sharedConceptOne.getDocumentOneParagraphIndizees().add(0);
        sharedConceptOne.getDocumentOneParagraphIndizees().add(1);
        sharedConceptOne.getDocumentOneParagraphIndizees().add(3);
        sharedConceptOne.getDocumentTwoParagraphIndizees().add(0);
        sharedConceptOne.getDocumentTwoParagraphIndizees().add(1);

        sharedConcepts.add(sharedConceptOne);

        SharedConcept sharedConceptTwo = new SharedConcept();
        sharedConceptTwo.setConcept(conceptTwo);
        sharedConceptTwo.getDocumentOneParagraphIndizees().add(0);
        sharedConceptTwo.getDocumentOneParagraphIndizees().add(3);
        sharedConceptTwo.getDocumentTwoParagraphIndizees().add(3);

        sharedConcepts.add(sharedConceptTwo);

        SharedConcept sharedConceptThree = new SharedConcept();
        sharedConceptThree.setConcept(conceptThree);
        sharedConceptThree.getDocumentOneParagraphIndizees().add(3);
        sharedConceptThree.getDocumentTwoParagraphIndizees().add(3);

        sharedConcepts.add(sharedConceptThree);
        
        return sharedConcepts;
    }
}
