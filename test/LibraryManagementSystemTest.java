import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;
import java.util.List;

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

        library = new LibraryManagementSystem();
        library.addItem(book1);
        library.addItem(book2);
        library.addItem(dvd1);
        library.addItem(dvd2);
    }

    @Test
    public void testSearchByTitleShouldFindExistingBook() {
        //When
        List<Item> items = library.search("Dune");

        //Then
        assertEquals(1, items.size());
        assertEquals("Dune", items.getFirst().getTitle());
    }

    @Test
    public void testSearchByTitleShouldNotFindNonExistingBook() {
        //When
        List<Item> bookList = library.search("Gatsby");

        //Then
        assertTrue(bookList.isEmpty());
    }

    @Test
    public void testSearchByTypeShouldFindExistingBook() {
        //When
        List<Item> books = library.search("Book");

        //Then
        assertEquals("Book", books.getFirst().getItemType());
    }

    @Test
    public void testSearchByTypeShouldNotFindNonExistingItemType() {
        //When
        List<Item> boardGameList = library.search("BoardGame");

        //Then
        assertTrue(boardGameList.isEmpty());
    }

    @Test
    public void testBorrowItems() {
        //Given
        Item bookDune = library.search("Dune").getFirst();
        Item bookGalaxy = library.search("The Hitchhiker's Guide to the Galaxy").getFirst();

        //When
        library.lendItem(user1, bookDune);
        library.lendItem(user1, bookGalaxy);

        //Then
        assertTrue(bookDune.isBorrowed());
        assertTrue(bookGalaxy.isBorrowed());
        assertTrue(library.getBorrowingUsers().containsKey(user1));
        assertTrue(library.getBorrowingUsers().get(user1).contains(bookDune));
        assertTrue(library.getBorrowingUsers().get(user1).contains(bookGalaxy));
    }

    @Test
    public void testBorrowItemAndNotifyWaitingList() {
        //Given
        Item book = library.search("Dune").getFirst();

        //When
        library.lendItem(user1, book);
        library.lendItem(user2, book);

        // Then
        assertTrue(book.isBorrowed());
        assertTrue(library.getBorrowedItems().containsKey(book));
        assertTrue(library.getBorrowingUsers().containsKey(user1));
        assertFalse(library.getBorrowingUsers().containsKey(user2));
        assertEquals(1, book.getObservers().size());
        assertSame(book.getObservers().getFirst(), user2);
    }

    @Test
    public void testRegisterUserAtMultipleWaitingLists() {
        //Given
        Item bookDune = library.search("Dune").getFirst();
        Item bookGalaxy = library.search("The Hitchhiker's Guide to the Galaxy").getFirst();

        //When
        library.lendItem(user1, bookDune);
        library.lendItem(user2, bookGalaxy);
        library.lendItem(user1, bookGalaxy);

        // Then
        assertTrue(bookDune.isBorrowed());
        assertTrue(bookGalaxy.isBorrowed());
        assertTrue(library.getBorrowedItems().containsKey(bookDune));
        assertTrue(library.getBorrowedItems().containsKey(bookGalaxy));
        assertTrue(library.getBorrowingUsers().containsKey(user1));
        assertTrue(library.getBorrowingUsers().containsKey(user2));
        assertEquals(1, bookGalaxy.getObservers().size());
        assertEquals(0, bookDune.getObservers().size());
        assertSame(bookGalaxy.getObservers().getFirst(), user1);
    }

    @Test
    public void testRemoveFromWaitingList() {
        //Given
        Item book = library.search("Dune").getFirst();

        //When
        library.lendItem(user1, book);
        library.lendItem(user2, book);
        library.unsubscribeFromWaitingList(user2, book);

        // Then
        assertTrue(book.isBorrowed());
        assertTrue(library.getBorrowedItems().containsKey(book));
        assertTrue(library.getBorrowingUsers().containsKey(user1));
        assertFalse(library.getBorrowingUsers().containsKey(user2));
        assertEquals(0, book.getObservers().size());
    }

    @Test
    public void testBorrowNonExistentItem() {
        //Given
        Book book3 = new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", Year.parse("2014"));

        //When
        library.lendItem(user1, book3);

        //Then
        assertFalse(book3.isBorrowed());
        assertFalse(library.getBorrowedItems().containsKey(book3));
    }

    @Test
    public void testReturnNonBorrowedItem() {
        //Given
        Item book = library.search("Dune").getFirst();

        //When
        library.returnItem(book); // Nothing should break

        //Then
        assertFalse(book.isBorrowed());
    }
}
