package am.prototypes.ArticleComparatorService.services;

import am.prototypes.ArticleComparatorService.model.Concept;
import am.prototypes.ArticleComparatorService.model.Document;
import am.prototypes.ArticleComparatorService.model.Paragraph;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class ModelComputerServiceTest {
    private static final ModelComputerService service = new ModelComputerService();
    private static final String PARAGRAPH_ONE_TEXT  = "This is a test paragraph containing Link_1. And it contains a" +
            " span.";
    private static final String PARAGRAPH_TWO_TEXT = "This is a second test paragraph without a link.";
    private static final String PARAGRAPH_THREE_TEXT = "";
    private static final String PARAGRAPH_FOUR_TEXT = "And another paragraph with Link_2 and Link_3.";

    private static final String DOCUMENT_NAME = "Test";
    private static final String DOCUMENT_URL = "Test.html";

    @Test(expected = IllegalArgumentException.class)
    public void testExtractDocumentNullArgument() {
        service.extractDocument(null, "", "");
    }

    @Test
    public void testExtractDocumentNormalCase() throws IOException {
        Document expectedDocument = new Document();

        Concept conceptOne = new Concept("Link_1", "link_1_href");
        Concept conceptTwo = new Concept("Link_2", "link_2_href");
        Concept conceptThree = new Concept("Link_3", "link_3_href");

        List<Paragraph> paragraphs = new LinkedList<>();

        Paragraph paragraphOne = new Paragraph();
        paragraphOne.setText(PARAGRAPH_ONE_TEXT);
        paragraphOne.getConcepts().add(conceptOne);

        expectedDocument.getParagraphs().add(paragraphOne);

        Paragraph paragraphTwo = new Paragraph();
        paragraphTwo.setText(PARAGRAPH_TWO_TEXT);

        expectedDocument.getParagraphs().add(paragraphTwo);

        Paragraph paragraphThree = new Paragraph();
        paragraphThree.setText(PARAGRAPH_THREE_TEXT);

        expectedDocument.getParagraphs().add(paragraphThree);

        Paragraph paragraphFour = new Paragraph();
        paragraphFour.setText(PARAGRAPH_FOUR_TEXT);
        paragraphFour.getConcepts().add(conceptTwo);
        paragraphFour.getConcepts().add(conceptThree);

        expectedDocument.getParagraphs().add(paragraphFour);
        expectedDocument.setName(DOCUMENT_NAME);
        expectedDocument.setUrl(DOCUMENT_URL);

        File file = ResourceUtils.getFile("classpath:test.html");
        org.jsoup.nodes.Document doc = Jsoup.parse(file, "UTF-8", "");

        Document actualDocument = service.extractDocument(doc, DOCUMENT_URL, DOCUMENT_NAME);

        Assert.assertEquals(expectedDocument.getParagraphs().size(), actualDocument.getParagraphs().size());
        Assert.assertEquals(expectedDocument, actualDocument);
    }
}
