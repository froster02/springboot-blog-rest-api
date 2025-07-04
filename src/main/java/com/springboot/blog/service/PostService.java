package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    void deletePostById(long id);

    PostDto updatePost(PostDto postDto, long id);
}
