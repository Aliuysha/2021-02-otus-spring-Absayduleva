package spring.ru.otus.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;
import spring.ru.otus.library.domain.Book;
import spring.ru.otus.library.repositories.AuthorRepository;
import spring.ru.otus.library.repositories.BookRepository;
import spring.ru.otus.library.repositories.GenreRepository;

@Controller
public class BookPageController {

    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;

    public BookPageController(
            BookRepository bookRepository,
            GenreRepository genreRepository,
            AuthorRepository authorRepository
    ) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @GetMapping("/")
    public String listPage() {
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        Book book = bookRepository.findById(id).block();
        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping("/delete")
    public String deletePage(@RequestParam("id") String id) {
        bookRepository.deleteById(id).subscribe();
        return "redirect:/";
    }

    @PostMapping("/save")
    public String saveBook(
            Book book,
            Model model
    ) {
        Mono<Book> saved = bookRepository.save(book);
        model.addAttribute(saved.block());
        return "redirect:/edit?id=" + saved.block().getId();
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("authorsList", authorRepository.findAll().collectList().block());
        model.addAttribute("genresList", genreRepository.findAll().collectList().block());
        return "new";
    }

    @PostMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book).subscribe();
        return "redirect:/";
    }
}
