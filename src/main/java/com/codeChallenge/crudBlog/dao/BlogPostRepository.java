package com.codeChallenge.crudBlog.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.codeChallenge.crudBlog.model.BlogPost;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
	
	Page<BlogPost> findAll(Pageable pageable);
}
