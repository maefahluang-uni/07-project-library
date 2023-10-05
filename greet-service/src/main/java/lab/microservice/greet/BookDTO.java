package lab.microservice.greet;

public class BookDTO {
    private String title;

    private AuthorDTO author;

    private String genresID;

    private int publication;

    private PublisherDTO publisher;

    private int port;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDTO author) {
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

    public PublisherDTO getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDTO publisher) {
        this.publisher = publisher;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
