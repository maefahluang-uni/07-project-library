package th.cmu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fname;

    private String lname;

    private String email;

    @ManyToMany
    @JoinTable(name = "Borrowing", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> borrowBooks = new ArrayList<>();

    public Member() {
    }

    public Member(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public Member(Long id, String fname, String lname, String email) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }

    public void borrowedBooks(Book book) {
        borrowBooks.add(book);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public List<Book> getBorrowedBooks() {
        return borrowBooks;
    }

    public void setBorrowedBooks(List<Book> borrowBooks) {
        this.borrowBooks = borrowBooks;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
