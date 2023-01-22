package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired ArticleService articleService;

    @Test
    void index() {
        Article a = new Article(1L,"가가가가","1111");
        Article b = new Article(2L,"나나나나","2222");
        Article c = new Article(3L,"다다다다","3333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));

        List<Article> articles = articleService.index();

        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_success() {
        Long id = 1L;
        Article expected = new Article(id, "가가가가","1111");
        Article article = articleService.show(id);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_failed() {
        Long id = -1L;
        Article expected = null;
        Article article = articleService.show(id);
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_success() {
        String title    = "라라라라";
        String content  = "4444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);
        Article article = articleService.create(dto);
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_failed() {
        String title    = "라라라라";
        String content  = "4444";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;
        Article article = articleService.create(dto);
        assertEquals(expected,article);
    }
}