import java.time.Year;
import java.util.UUID;

public class DVD extends Item {
    private final long runtime;
    private final String director;
    private final Year publicationYear;

    public DVD(String title, long runtime, String director, Year publicationYear) {
        super(UUID.randomUUID(), title, 14);
        this.runtime = runtime;
        this.director = director;
        this.publicationYear = publicationYear;
    }

    public long getRuntime() {
        return runtime;
    }

    public String getDirector() {
        return director;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String getItemType() {
        return "DVD";
    }

    @Override
    public String toString() {
        return "DVD " + getTitle() + " directed by " + getDirector() + " (" + getPublicationYear() + ")";
    }
}
