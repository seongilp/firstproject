package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.hibernate.mapping.Array;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("All comments from a article")
    void findByArticleId() {
        // Case 1
        {
            Long articleId = 4L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = new Article(4L,"당신의 인생 영화는?","댓글 ㄱ");
            Comment a = new Comment(1L,article,"Park","굳 윌 헌팅");
            Comment b = new Comment(2L,article,"Kim","아이 엠 샘");
            Comment c = new Comment(3L,article,"Choi","쇼생크의 탈출");
            List<Comment> expected = Arrays.asList(a,b,c);
            assertEquals(expected.toString(), comments.toString(),"4번 글의 모든 댓글을 출력!");
        }
        // Case 2
        {
            Long articleId = 1L;
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            Article article = new Article(1L,"가가가가","1111");
            List<Comment> expected = Arrays.asList();
            assertEquals(expected.toString(), comments.toString(),"1번 글은 댓글이 없음!");
        }
        // 9번 게시글의 댓글
        // 9999
        // -1 게시글
    }

    @Test
    @DisplayName("All comments from a nickname")
    void findByNickname() {
        // Park
        {
            String nickname ="Park";
            List<Comment> comments = commentRepository.findByNickname(nickname);
            Comment a = new Comment(1L,new Article(4L,"당신의 인생 영화는?","댓글 ㄱ"),nickname,"굳 윌 헌팅");
            Comment b = new Comment(4L,new Article(5L,"당신의 소울 푸드는?","댓글 ㄱㄱ"),nickname,"치킨");
            Comment c = new Comment(7L,new Article(6L,"당신의 취미는?","댓글 ㄱㄱㄱ"),nickname,"조깅");
            List<Comment> expected = Arrays.asList(a,b,c);
            assertEquals(expected.toString(),comments.toString(),"Park의 모든 댓글을 출력!");
        }
        // Kim
        // Null
        // ""의 댓글 조회
        // "I"의 댓글 조회
    }
}