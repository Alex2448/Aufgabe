import java.time.Year;
import java.util.UUID;

public class Book extends Item {
    private final String author;
    private final Year publicationYear;

    public Book(String title, String author, Year publicationYear) {
        super(UUID.randomUUID(), title, 30);
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
        return "Book " + getTitle()  + " from " + getAuthor() + " (" + getPublicationYear() + ")";
    }
}
