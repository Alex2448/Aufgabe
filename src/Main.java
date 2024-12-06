import java.time.Year;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Book book1 = new Book(UUID.randomUUID(), "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", Year.parse("1995"));
        Book book2 = new Book(UUID.randomUUID(), "Dune", "Frank Herbert", Year.parse("1965"));
        DVD dvd1 = new DVD(UUID.randomUUID(), "Inception",  148, "Christopher Nolan", Year.parse("2010"));
        DVD dvd2 = new DVD(UUID.randomUUID(), "Memento", 109, "Christopher Nolan", Year.parse("2000"));

        LibraryManagementSystem library = new LibraryManagementSystem();
        library.addBorrowableItem(book1);
        library.addBorrowableItem(book2);
        library.addBorrowableItem(dvd1);
        library.addBorrowableItem(dvd2);

        User user1 = new User("Alice");
        User user2 = new User("Bob");

        List<Item> booksNull = library.search("The Colour of Magic"); // ist leer
        List<Item> bookOne = library.search("The Hitchhiker's Guide to the Galaxy");
        library.borrowItem(user1, book1);
        // User2 soll in Waiting List sein
        library.borrowItem(user2, book1);

        // Edge Case: user1 soll nicht in Waiting List gehen, wenn er wieder dasselbe Buch versucht auszuleihen
        library.borrowItem(user1, book1);
        // search soll nichts zurückliefern, wenn Buch bereits ausgeliehen
        List<Item> booksNotAvailableForSearch = library.search("The Hitchhiker's Guide to the Galaxy");
        library.returnItem(book1);
        library.returnItem(book2); // soll nichts machen
        library.returnItem(book1);

        library.borrowItem(user2, book1);
        library.returnItem(book1);
    }
}