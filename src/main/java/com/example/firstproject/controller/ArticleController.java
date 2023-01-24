package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("articles/create")
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString());
        log.info(form.toString());

        // 1. Dto to Entity
        Article article = form.toEntity();
//        System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repo to DB
        Article saved = articleRepository.save(article);
//        System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);
        // 1. data from id
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. data to model
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);

        // 3. page setup
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList",articleEntityList);
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info(form.toString());
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        if (target != null) {
            articleRepository.save(articleEntity);
        }
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("deleted.....!");
        Article target = articleRepository.findById(id).orElse(null);
        log.info((target.toString()));
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료되었습니다");
        }
        return "redirect:/articles";
    }
}
