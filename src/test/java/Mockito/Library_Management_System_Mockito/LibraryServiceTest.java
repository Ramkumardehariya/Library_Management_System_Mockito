package Mockito.Library_Management_System_Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class LibraryServiceTest {

    private LibraryService libraryService;
    private BookRepository bookRepository;

    @Before
    public void setUp() {
        bookRepository = Mockito.mock(BookRepository.class);
        libraryService = new LibraryService(bookRepository);
    }

    @Test
    public void testBorrowBook_whenBookIsAvailable() {
        Long bookId = 1L;
        Book book = new Book(bookId, "Test Book", "available");

        // Mocking the repository to return the book
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Act
        String result = libraryService.borrowBook(bookId);

        // Assert that the book's status is updated
        assertEquals("borrowed", book.getStatus());
        // Assert that the correct message is returned
        assertEquals("Successfully borrowed the book: Test Book", result);

        // Verify that the save method was called to update the book status
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testBorrowBook_whenBookIsAlreadyBorrowed() {
        Long bookId = 1L;
        Book book = new Book(bookId, "Test Book", "borrowed");

        // Mocking the repository to return the book
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Act
        String result = libraryService.borrowBook(bookId);

        // Assert that the book's status is unchanged
        assertEquals("borrowed", book.getStatus());
        // Assert that the correct message is returned
        assertEquals("The book is already borrowed.", result);
    }

    @Test
    public void testReturnBook_whenBookIsBorrowed() {
        Long bookId = 1L;
        Book book = new Book(bookId, "Test Book", "borrowed");

        // Mocking the repository to return the book
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Act
        String result = libraryService.returnBook(bookId);

        // Assert that the book's status is updated
        assertEquals("available", book.getStatus());
        // Assert that the correct message is returned
        assertEquals("Successfully returned the book: Test Book", result);

        // Verify that the save method was called to update the book status
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    public void testReturnBook_whenBookIsAlreadyAvailable() {
        Long bookId = 1L;
        Book book = new Book(bookId, "Test Book", "available");

        // Mocking the repository to return the book
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // Act
        String result = libraryService.returnBook(bookId);

        // Assert that the book's status is unchanged
        assertEquals("available", book.getStatus());
        // Assert that the correct message is returned
        assertEquals("The book is already available.", result);
    }
}