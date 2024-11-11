package Mockito.Library_Management_System_Mockito;

public class LibraryService {

    private final BookRepository bookRepository;

    public LibraryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public String borrowBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if ("borrowed".equals(book.getStatus())) {
            return "The book is already borrowed.";
        }

        book.setStatus("borrowed");
        bookRepository.save(book);
        return "Successfully borrowed the book: " + book.getTitle();
    }

    public String returnBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        if ("available".equals(book.getStatus())) {
            return "The book is already available.";
        }

        book.setStatus("available");
        bookRepository.save(book);
        return "Successfully returned the book: " + book.getTitle();
    }
}

