import java.time.Year;
import java.util.List;
import java.util.UUID;

public class Main {

    public LibraryManagementSystem prepareLibrary() {
        Book book1 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", Year.parse("1995"));
        Book book2 = new Book("Dune", "Frank Herbert", Year.parse("1965"));
        DVD dvd1 = new DVD("Inception",  148, "Christopher Nolan", Year.parse("2010"));
        DVD dvd2 = new DVD("Memento", 109, "Christopher Nolan", Year.parse("2000"));

        LibraryManagementSystem library = new LibraryManagementSystem();
        library.addBorrowableItem(booksGalaxy[0]);
        library.addBorrowableItem(book2);
        library.addBorrowableItem(dvd1);
        library.addBorrowableItem(dvd2);
        return library;
    }

    public static void main(String[] args) {
        
        LibraryManagementSystem library = prepareLibrary();
        User user1 = new User("Alice");
        User user2 = new User("Bob");

        List<Item> booksEmpty = library.search("The Colour of Magic"); // is empty
        List<Item> booksGalaxy = library.search("The Hitchhiker's Guide to the Galaxy");
        List<Item> booksDune= library.search("Dune");

        library.lendItem(user1, booksGalaxy[0]);
        // User2 should be in waiting list
        library.lendItem(user2, booksGalaxy[0]);

        // Edge Case: user1 should not be able to borrow the same book while borrowing it
        // and he should not be registered in waiting list
        library.lendItem(user1, booksGalaxy[0]);
        List<Item> booksNo = library.search("The Hitchhiker's Guide to the Galaxy");
        library.returnItem(booksGalaxy[0]);
        library.returnItem(book2); // should do nothing

        library.lendItem(user2, booksGalaxy[0]);
        library.returnItem(booksGalaxy[0]);
    }
}