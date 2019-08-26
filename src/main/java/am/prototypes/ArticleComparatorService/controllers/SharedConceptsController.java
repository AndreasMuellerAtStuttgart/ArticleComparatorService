package am.prototypes.ArticleComparatorService.controllers;

import am.prototypes.ArticleComparatorService.model.SharedConcept;
import am.prototypes.ArticleComparatorService.model.SharedConcepts;
import am.prototypes.ArticleComparatorService.services.ModelComputerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@RestController
public class SharedConceptsController {
    ModelComputerService modelComputerService;

    public SharedConceptsController(ModelComputerService modelComputerService) {
        this.modelComputerService = modelComputerService;
    }

    @GetMapping("/getSharedConcepts")
    public SharedConcepts getSharedConcepts(@RequestParam("documentOneUrl") String documentOneUrl,
                                            @RequestParam("documentTwoUrl") String documentTwoUrl) {
        SharedConcepts sharedConcepts = new SharedConcepts();

        try {
            Document htmlDocumentOne = Jsoup.connect(documentOneUrl).get();
            Document htmlDocumentTwo = Jsoup.connect(documentTwoUrl).get();

            System.out.println(htmlDocumentOne.getAllElements().size());
            System.out.println(htmlDocumentTwo.getAllElements().size());

            am.prototypes.ArticleComparatorService.model.Document documentOne = modelComputerService.extractDocument(
                    htmlDocumentOne, documentOneUrl, htmlDocumentOne.title());
            am.prototypes.ArticleComparatorService.model.Document documentTwo = modelComputerService.extractDocument(
                    htmlDocumentTwo, documentTwoUrl, htmlDocumentTwo.title());

            Set<SharedConcept> sharedConceptSet = modelComputerService.extractSharedConcepts(documentOne, documentTwo);

            sharedConcepts.setDocumentOne(documentOne);
            sharedConcepts.setDocumentTwo(documentTwo);
            sharedConcepts.setSharedConcepts(sharedConceptSet);
            sharedConcepts.setWasMadeSuccessfully(true);

            return sharedConcepts;
        } catch (IOException e) {
            e.printStackTrace();

            sharedConcepts.setDocumentOne(new am.prototypes.ArticleComparatorService.model.Document());
            sharedConcepts.setDocumentTwo(new am.prototypes.ArticleComparatorService.model.Document());
            sharedConcepts.setSharedConcepts(new HashSet<>());
            sharedConcepts.setWasMadeSuccessfully(false);

            return sharedConcepts;
        }
    }
}
