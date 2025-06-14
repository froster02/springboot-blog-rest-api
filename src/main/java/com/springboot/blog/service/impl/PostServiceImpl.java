package com.springboot.blog.service.impl;

import com.springboot.blog.entity.Post;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.PostService;
import com.springboot.blog.payload.PostDto;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert PostDto to Post entity
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        // convert Post entity to PostDto
        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }
}


//This code defines a Spring Boot service implementation for managing posts. Here's how it works:
//
//Class Declaration:
//
//The @Service annotation marks the class as a Spring service component, making it eligible for dependency injection.
//The class implements the PostService interface, ensuring it adheres to the contract defined by the interface.
//Dependency Injection:
//
//The PostRepository is injected into the class using constructor injection. This allows the service to interact with the database via the repository.
//createPost Method:
//
//Input: Accepts a PostDto object, which is a Data Transfer Object (DTO) used to transfer data between layers.
//DTO to Entity Conversion:
//A new Post entity is created.
//Data from the PostDto is copied to the Post entity using setter methods (setTitle, setDescription, setContent).
//Saving the Entity:
//The postRepository.save(post) method is called to persist the Post entity in the database. This returns the saved entity with a generated ID.
//Entity to DTO Conversion:
//A new PostDto object is created.
//Data from the saved Post entity is copied back to the PostDto using setter methods (setId, setTitle, setDescription, setContent).
//Output: Returns the PostDto object, which contains the saved post's details.
//Purpose:
//This method encapsulates the logic for creating a post, ensuring separation between the API layer (DTOs) and the database layer (entities). It follows a common pattern in Spring Boot applications for handling data flow.