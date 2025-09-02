package com.springboot.blog.contoller;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostDtoV2;
import com.springboot.blog.payload.PostResponse;
import com.springboot.blog.service.PostService;
import com.springboot.blog.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(
        name = "CRUD REST APIs for $Post$ Resource"
)
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post
    @Operation(
            summary = "Create Post REST API",
            description = "this is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/v1/posts")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    // get all blog posts rest api
//    @GetMapping
//    public List<PostDto> getAllPosts() {
//        return postService.getAllPosts();
//    }

    // get all blog posts rest api
//    @GetMapping
//    public List<PostDto> getAllPosts(
//            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
//            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
//    ) {
//        return postService.getAllPosts(pageNo, pageSize);
//    }

    // get all blog posts with pagination
    @Operation(
            summary = "Get all Post REST API",
            description = "this is used to fetch all the post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 success"
    )
    @GetMapping("/v1/posts")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

    // get blog post by id rest api
    @Operation(
            summary = "Get Post by Id REST API",
            description = "this is used to get single post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 success"
    )
    @GetMapping("/v1/posts/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {
        PostDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
    }

    //version 2

    @GetMapping(value = "/v2/posts/{id}", produces = "application/vnd.blog.v2+json")
    @Operation(summary = "Get Post by Id V2", description = "Get a single post with additional V2 features")
    public ResponseEntity<PostDtoV2> getPostByIdV2(@PathVariable("id") long id) {
        PostDto postDto = postService.getPostById(id);

        // Map PostDto to PostDtoV2
        PostDtoV2 postDtoV2 = new PostDtoV2();
        postDtoV2.setId(postDto.getId());
        postDtoV2.setTitle(postDto.getTitle());
        postDtoV2.setDescription(postDto.getDescription());
        postDtoV2.setContent(postDto.getContent());
        postDtoV2.setCommentDtoSet(postDto.getCommentDtoSet());
        postDtoV2.setCategoryId(postDto.getCategoryId());

        // Add tags (this would typically come from the database in a real application)
        List<String> tags = new ArrayList<>();
        tags.add("Java");
        tags.add("Spring Boot");
        tags.add("AWS");
        postDtoV2.setTags(tags);
        // Note: You'll need to add a 'tags' field to PostDtoV2 if you want to use them

        return ResponseEntity.ok(postDtoV2);
    }

    //update post by id rest api
    @Operation(
            summary = "Update Post REST API",
            description = "this is used to update particular post in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 created"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/v1/posts/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    // delete post by id rest api
    @Operation(
            summary = "Delete Post REST API",
            description = "this is used to delete a particular post from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 success"
    )
    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/v1/posts/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }

    // Build Get Posts by Category REST API
    // http://localhost:8081/api/posts/category/3
    @GetMapping("/v1/posts/category/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Long categoryId) {
        List<PostDto> postDtos = postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }

}
