import java.time.Year;
import java.util.UUID;

public class Book extends Item {
    private String author;
    private Year publicationYear;

    public Book(String title, String author, Year publicationYear) {
        UUID id = UUID.randomUUID();
        super(id, title, 30);
        this.author = author;
        this.publicationYear = publicationYear;
    }

    public String getAuthor() {
        return author;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public String toString() {
        return "Book " + getTitle()  + " from " + getAuthor() + " (" + getPublicationYear() + ")"
    }
}
