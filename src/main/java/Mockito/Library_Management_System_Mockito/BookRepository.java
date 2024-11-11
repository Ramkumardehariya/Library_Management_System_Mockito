package Mockito.Library_Management_System_Mockito;
import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(Long id);
    void save(Book book);
}

