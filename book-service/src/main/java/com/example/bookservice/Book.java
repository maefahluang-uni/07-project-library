package th.cmu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private String genus;

    private int publication;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @ManyToMany(mappedBy = "borrowBooks")
    private List<Member> borrower = new ArrayList<>();

    public Book() {
    }

    public Book(String title, Author author, String genus, int publication) {
        this.title = title;
        this.author = author;
        this.genus = genus;
        this.publication = publication;
    }

    public Book(Long id, String title, Author author, String genus, int publication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genus = genus;
        this.publication = publication;
    }

    public void addBorrower(Member member) {
        borrower.add(member);
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

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public List<Member> getBorrower() {
        return borrower;
    }

    public void setBorrower(List<Member> borrower) {
        this.borrower = borrower;
    }

    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

}
