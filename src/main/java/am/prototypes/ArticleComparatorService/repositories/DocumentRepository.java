package am.prototypes.ArticleComparatorService.repositories;

import am.prototypes.ArticleComparatorService.model.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, String> {
}
