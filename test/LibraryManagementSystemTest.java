import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;
import java.util.List;
import java.util.UUID;

public class LibraryManagementSystemTest {
    private LibraryManagementSystem library;
    private User user1;
    private User user2;

    @BeforeEach
    public void setup() {
        user1 = new User("Alice");
        user2 = new User("Bob");
        Book book1 = new Book("The Hitchhiker's Guide to the Galaxy", "Douglas Adams", Year.parse("1995"));
        Book book2 = new Book("Dune", "Frank Herbert", Year.parse("1965"));
        DVD dvd1 = new DVD("Inception",  148, "Christopher Nolan", Year.parse("2010"));
        DVD dvd2 = new DVD("Memento", 109, "Christopher Nolan", Year.parse("2000"));

        LibraryManagementSystem library = new LibraryManagementSystem();
        library.addBorrowableItem(booksGalaxy[0]);
        library.addBorrowableItem(book2);
        library.addBorrowableItem(dvd1);
        library.addBorrowableItem(dvd2);
    }

    @Test
    public void testSearchByTitle() {
        List<Item> items = library.search("The Dune");
        assertEquals(1, items.size());
        assertEquals("Dune", items.get(0).getTitle());

        List<Item> bookList = library.search("Gatsby");
        assertTrue(bookList.isEmpty())
    }

    @Test
    public void testSearchByType() {
        List<Item> books = library.search("Book");
        List<Item> dvds = library.search("DVD");

        assertEquals("book", books.get(0).getItemType());
        assertEquals("DVDs", dvds.get(0).getItemType());
    }

    @Test
    public void testBorrowItem() {
        Item book = library.search("Dune").get(0);
        library.lendItem(user1, book);
        assertTrue(book.isBorrowed());
        // todo assert user hats ausgeliehen
    }

    @Test
    public void testReturnItemAndNotifyWaitingList() {
        Item book = library.search("Dune").get(0);
        library.lendItem(user1, book);
        library.lendItem(user2, book); //waitlist
        library.returnItem(book);
        library.lendItem(user2, book);

        // When returned, User2 should be notified and borrow it automatically
        assertTrue(book.isBorrowed());
        assertTrue(library.getBorrowedItems().containsKey(book));
        assertTrue(library.getBorrowingUsers().containsKey(user2));
        assertFalse(library.getBorrowingUsers().containsKey(user1));
    }

    @Test
    public void testBorrowNonExistentItem() {
        Book book3 = new Book(UUID.randomUUID(), "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", Year.parse("2014"));
        library.lendItem(user1, book3);
        boolean borrowed = library.getBorrowedItems().containsKey(book3);
        assertFalse(book3.isBorrowed());
        assertFalse(borrowed);
    }

    @Test
    public void testReturnNonBorrowedItem() {
        Item book = library.search("Dune").get(0);
        library.returnItem(book); // Nothing should break
        book = library.search("Dune").get(0);
        assertFalse(book.isBorrowed());
    }
}
