package com.codeChallenge.crudBlog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.codeChallenge.crudBlog.dao.BlogPostRepository;
import com.codeChallenge.crudBlog.model.BlogPost;

@Service
public class BlogPostService {

	private final BlogPostRepository blogPostRepository;

	public BlogPostService(BlogPostRepository blogPostRepository) {
		this.blogPostRepository = blogPostRepository;
	}

	public BlogPost createBlogPost(BlogPost blogPost) {
		return blogPostRepository.save(blogPost);
	}

	public BlogPost getBlogPostById(Long id) {
		return blogPostRepository.findOne(id);
	}

	public Page<BlogPost> getAllBlogPosts(Pageable pageable) {
		return blogPostRepository.findAll(pageable);
	}

	public BlogPost updateBlogPost(Long id, BlogPost updatedBlogPost) {
		BlogPost existingBlogPost = getBlogPostById(id);
		existingBlogPost.setTitle(updatedBlogPost.getTitle());
		existingBlogPost.setBody(updatedBlogPost.getBody());
		existingBlogPost.setAuthor(updatedBlogPost.getAuthor());
		return blogPostRepository.save(existingBlogPost);
	}

	public void deleteBlogPost(Long id) {
		blogPostRepository.delete(id);
	}
	
}
