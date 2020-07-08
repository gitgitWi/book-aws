package com.spring.booting.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// MyBatis 등 Mapper에서 DAO로 불리는 것
// JpaRepository<Entity_Class, PK_DataType> 자동으로 CRUD methods 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

//   SpringDataJPA에서 제공하지 않는 method는 직접 query 작성 가능
    @Query("SELECT P FROM Posts P ORDER BY P.id DESC")
    List<Posts> findAllDesc();

}
