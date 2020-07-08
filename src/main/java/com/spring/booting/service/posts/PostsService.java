package com.spring.booting.service.posts;

import com.spring.booting.domain.posts.Posts;
import com.spring.booting.domain.posts.PostsRepository;
import com.spring.booting.web.dto.PostsListResponseDto;
import com.spring.booting.web.dto.PostsResponseDto;
import com.spring.booting.web.dto.PostsSaveRequestDto;
import com.spring.booting.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// 생성자로 주입, @Autowired 불필요
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update (Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow( () -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
//        JPA의 영속성 컨텍스트 때문에 쿼리 없어도 가능
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return  id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete (Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow( () -> new IllegalArgumentException ("해당 게시글이 없습니다. id = " + id));
        postsRepository.delete(posts);
    }
}
