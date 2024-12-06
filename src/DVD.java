import java.time.Year;
import java.util.UUID;

public class DVD extends Item {
    private long runtime;
    private String director;
    private Year publicationYear;

    public DVD(UUID id, String title, long runtime, String director, Year publicationYear) {
        super(id, title, 14);
        this.runtime = runtime;
        this.director = director;
        this.publicationYear = publicationYear;
    }

    public long getRuntime() {
        return runtime;
    }

    public void setRuntime(long runtime) {
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Year getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Year publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String getItemType() {
        return "DVD";
    }

    @Override
    public String toString() {
        return "DVD " + getTitle();
    }
}
