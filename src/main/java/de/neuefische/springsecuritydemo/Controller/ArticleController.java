package de.neuefische.springsecuritydemo.Controller;

import de.neuefische.springsecuritydemo.Model.Article;
import de.neuefische.springsecuritydemo.Service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public List<Article> getAll () {
        return articleService.getAll();
    }

    @PostMapping
    public Article create (@RequestBody Article article) {
        return articleService.create(article);
    }
}
