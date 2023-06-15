package com.codeChallenge.crudBlog.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeChallenge.crudBlog.model.BlogPost;
import com.codeChallenge.crudBlog.service.BlogPostService;

@RestController
@RequestMapping("/api")
public class BlogPostController {

	private final BlogPostService blogPostService;

	public BlogPostController(BlogPostService blogPostService) {
		this.blogPostService = blogPostService;
	}

	@GetMapping
	public String Welcome() {
		return "Hallo World";
	}

	@GetMapping("/blog-posts")
	public ResponseEntity<Page<BlogPost>> getBlogPosts(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "title") String sortBy,
			@RequestParam(defaultValue = "desc") String sortDirection) {
		Sort.Direction direction = Sort.Direction.fromString(sortDirection);
		Sort sort = new Sort(direction, sortBy);

		PageRequest pageable = new PageRequest(page, size, sort);
		Page<BlogPost> blogPosts = blogPostService.getAllBlogPosts(pageable);
		int totalPages = (blogPosts.getNumberOfElements());
		if (totalPages != 0) {
			return ResponseEntity.ok(blogPosts);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/blog-posts/{id}")
	public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
		BlogPost blogPost = blogPostService.getBlogPostById(id);
		if (blogPost != null) {
			return ResponseEntity.ok(blogPost);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PostMapping("/blog-posts")
	public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
		BlogPost createdBlogPost = this.blogPostService.createBlogPost(blogPost);
		if (createdBlogPost.equals(blogPost)) {
			return ResponseEntity.status(HttpStatus.CREATED).body(createdBlogPost);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createdBlogPost);
		}
	}

	@PutMapping("/blog-posts")
	public ResponseEntity<BlogPost> updateBlogPost(@RequestBody BlogPost updatedBlogPost) {
		try {
			Long id = updatedBlogPost.getId();
			BlogPost updatedPost = blogPostService.updateBlogPost(id, updatedBlogPost);
			return ResponseEntity.ok(updatedPost);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/blog-posts/{id}")
	public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
		try {
			blogPostService.deleteBlogPost(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

}
