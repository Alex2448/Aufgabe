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
        Book book1 = new Book(UUID.randomUUID(), "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", Year.parse("1995"));
        Book book2 = new Book(UUID.randomUUID(), "Dune", "Frank Herbert", Year.parse("1965"));
        DVD dvd1 = new DVD(UUID.randomUUID(), "Inception",  148, "Christopher Nolan", Year.parse("2010"));
        DVD dvd2 = new DVD(UUID.randomUUID(), "Memento", 109, "Christopher Nolan", Year.parse("2000"));

        user1 = new User("Alice");
        user2 = new User("Bob");

        library = new LibraryManagementSystem();
        library.addBorrowableItem(book1);
        library.addBorrowableItem(book2);
        library.addBorrowableItem(dvd1);
        library.addBorrowableItem(dvd2);
    }

    @Test
    public void testSearchByTitle() {
        List<Item> items = library.search("Dune");
        assertEquals(1, items.size());
        assertEquals("Dune", items.get(0).getTitle());
    }

    @Test
    public void testSearchByType() {
        List<Item> books = library.search("Book");
        List<Item> dvds = library.search("DVD");

        assertEquals("Book", books.get(0).getItemType());
        assertEquals("DVD", dvds.get(0).getItemType());
    }

    @Test
    public void testBorrowItem() {
        Item book = library.search("Dune").get(0);
        library.borrowItem(user1, book);
        assertTrue(book.isBorrowed());

        List<Item> bookList = library.search("Gatsby");
        assertTrue(bookList.isEmpty());
    }

    @Test
    public void testBorrowItemWaitingList() {
        Item book = library.search("Dune").get(0);
        library.borrowItem(user1, book);
        library.borrowItem(user2, book); //waitlist

        // User2 should be on the waitlist for the book
        assertTrue(library.getWaitingList().get(book).contains(user2));

        // When returned, User2 should be notified and borrow it automatically
        assertTrue(book.isBorrowed());
    }

    @Test
    public void testReturnItemAndNotifyWaitingList() {
        Item book = library.search("Dune").get(0);
        library.borrowItem(user1, book);
        library.borrowItem(user2, book); //waitlist
        library.returnItem(book);
        library.borrowItem(user2, book);

        // When returned, User2 should be notified and borrow it automatically
        assertTrue(book.isBorrowed());
        assertTrue(library.getBorrowedItems().containsKey(book));
        assertTrue(library.getBorrowingUsers().containsKey(user2));
        assertFalse(library.getBorrowingUsers().containsKey(user1));
    }

    @Test
    public void testMultipleWaitlistNotifications() {
        User user3 = new User("Charlie");
        Item book = library.search("Dune").get(0);
        library.borrowItem(user1, book);
        library.returnItem(book);
        library.borrowItem(user2, book); //waitlist
        library.returnItem(book);
        library.borrowItem(user3, book); //waitlist

        // When returned, User2 should be notified and borrow it automatically
        assertTrue(book.isBorrowed());
        assertTrue(library.getBorrowedItems().containsKey(book));
        assertTrue(library.getBorrowingUsers().containsKey(user3));
        assertFalse(library.getBorrowingUsers().containsKey(user1));
        assertFalse(library.getBorrowingUsers().containsKey(user2));

    }

    @Test
    public void testBorrowNonExistentItem() {
        Book book3 = new Book(UUID.randomUUID(), "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", Year.parse("2014"));
        library.borrowItem(user1, book3);
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
