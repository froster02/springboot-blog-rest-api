package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a blog post entity mapped to the "posts" table in the database.
 * This class uses JPA annotations for ORM mapping and Lombok annotations
 * for boilerplate code generation.
 */
@Data // Generates getters, setters, toString, equals, and hashCode methods.
@AllArgsConstructor // Generates a constructor with all fields as parameters.
@NoArgsConstructor // Generates a no-argument constructor.
@Entity // Marks this class as a JPA entity.
@Table(
        name = "posts", // Specifies the table name in the database.
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"title"}) // Ensures the "title" column is unique.
        }
)
public class Post {

    /**
     * The unique identifier for the post.
     * This field is mapped to the primary key column in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value.
    private Long id;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version = 0;
    /**
     * The title of the post.
     * This field is mapped to the "title" column in the database and cannot be null.
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * A brief description of the post.
     * This field is mapped to the "description" column in the database and cannot be null.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The main content of the post.
     * This field is mapped to the "content" column in the database and cannot be null.
     */
    @Column(name = "content", nullable = false)
    private String content;
}