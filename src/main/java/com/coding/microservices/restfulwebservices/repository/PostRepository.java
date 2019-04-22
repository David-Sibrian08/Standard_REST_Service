package com.coding.microservices.restfulwebservices.repository;

import com.coding.microservices.restfulwebservices.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
}
