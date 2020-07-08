package com.spring.booting.domain.posts;

import org.apache.tomcat.jni.Local;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After  // Junit Test 끝날 때마다 수행되는 method
    public void cleanup(){
        postsRepository.deleteAll();
    }


    @Test
    public void saveAndCall() {
        String title = "테스트 게시글";
        String content = "테스트 본문";

//        insert/update query
//        id값 있으면 update
//        id값 없으면 insert
        postsRepository.save(Posts.builder()
            .title(title)
            .content(content)
            .author("gitgitwi@gmail")
            .build()
        );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }


    @Test
    public void BaseTimeEntity_Insert() {

        LocalDateTime now = LocalDateTime.of(2020, 6,1,0,0,0);
        postsRepository.save(Posts.builder().title("titleTEST").content("contentTEST").author("authorTEST").build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>> createDate=" + posts.getCreatedDate() + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }


}
