package uz.elmurodov.simple.book;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public record BookController(BookService bookService) {

    @GetMapping("/{id}")
    public Book get(@PathVariable Integer id) {
        return bookService.get(id);
    }

    @PostMapping("")
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("")
    public Book update(@RequestBody Book book) {
        return bookService.update(book);
    }


    @GetMapping("")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return bookService.delete(id);
    }

}
