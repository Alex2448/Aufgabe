import java.time.Year;
import java.util.UUID;

public class Book extends Item {
    private String author;
    private Year publicationYear;

    public Book(UUID id, String title, String author, Year publicationYear) {
        super(id, title, 30);
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public String toString() {
        return "Book " + getTitle();
    }
}
