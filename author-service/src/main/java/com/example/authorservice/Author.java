package com.example.authorservice;

import java.util.ArrayList;
import java.util.List;

// import javax.persistence.*;

// @Entity
public class Author {
    // @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    // @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // public List<Book> getBooks() {
    // return books;
    // }

    // public void setBooks(List<Book> books) {
    // this.books = books;
    // }

}
