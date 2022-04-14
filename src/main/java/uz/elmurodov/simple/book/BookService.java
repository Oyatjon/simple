package uz.elmurodov.simple.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {


    private final BookRepository bookRepository;

    @Cacheable(value = "books", key = "#id", /*condition = "#id < 3",*/ unless = "#id == 4")
    public Book get(Integer id) {
        log.info("Getting book with id : {}", id);
        return bookRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Book not found");
        });
    }

    //  @Cacheable(value = "books", key = "#result.id")
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @CachePut(value = "books", key = "#book.id")
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @CacheEvict(value = "books", key = "#id")
    public String delete(Integer id) {
        bookRepository.deleteById(id);
        return "Successfully delete";
    }
}

