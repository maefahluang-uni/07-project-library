package lab.microservice.userrepo.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lab.microservice.userrepo.author.Author;
import lab.microservice.userrepo.publisher.Publisher;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private String genresID;

    private int publication;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    public Book() {
    }

    public Book(String title, Author author, String genresID, int publication, Publisher publisher) {
        this.title = title;
        this.author = author;
        this.genresID = genresID;
        this.publication = publication;
        this.publisher = publisher;
    }

    public Book(Long id, String title, Author author, String genresID, int publication, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genresID = genresID;
        this.publication = publication;
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenresID() {
        return genresID;
    }

    public void setGenresID(String genresID) {
        this.genresID = genresID;
    }

    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

}
