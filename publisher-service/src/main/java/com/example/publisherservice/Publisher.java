package com.example.publisherservice;

import java.util.ArrayList;
import java.util.List;

// import javax.persistence.*;

// @Entity
public class Publisher {
    private Long id;
    private String name;
    private String address;

    // @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval =
    // true)
    // private List<Book> books = new ArrayList<>();

    public Publisher() {
    }

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Publisher(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
