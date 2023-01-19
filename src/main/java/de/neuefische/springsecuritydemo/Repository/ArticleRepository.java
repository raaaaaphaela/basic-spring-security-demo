package de.neuefische.springsecuritydemo.Repository;

import de.neuefische.springsecuritydemo.Model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String> {
    List<Article> findAllByCreatedBy  (String createdBy);

}
