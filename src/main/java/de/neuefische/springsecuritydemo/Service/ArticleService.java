package de.neuefische.springsecuritydemo.Service;

import de.neuefische.springsecuritydemo.Model.Article;
import de.neuefische.springsecuritydemo.Repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final AppUserService appUserService;

    public Article create(Article article) {
        article.setCreatedBy(appUserService.getAuthenticatedUser().getId());
        return articleRepository.save(article);
    }

    public List<Article> getAll() {
        return articleRepository.findAllByCreatedBy(
                appUserService.getAuthenticatedUser().getId()
        );
    }
}
