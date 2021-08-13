package spring.ru.otus.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.ru.otus.library.dto.BookDto;
import spring.ru.otus.library.service.BookService;
import spring.ru.otus.library.web.service.AuthorWebService;
import spring.ru.otus.library.web.service.GenreWebService;

import java.util.UUID;

@Controller
public class BookPageController {

    private final BookService bookService;
    private final GenreWebService genreWebService;
    private final AuthorWebService authorWebService;

    public BookPageController(
            BookService bookService,
            GenreWebService genreWebService,
            AuthorWebService authorWebService
    ) {
        this.bookService = bookService;
        this.genreWebService = genreWebService;
        this.authorWebService = authorWebService;
    }

    @GetMapping("/")
    public String listPage() {
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") UUID id, Model model) {
        BookDto book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "edit";
    }

    @GetMapping("/delete")
    public String deletePage(@RequestParam("id") UUID id) {
        bookService.deleteBookById(id);
        return "redirect:/";
    }

    @PostMapping("/save")
    public String saveBook(
            BookDto bookDto,
            Model model
    ) {
        BookDto saved = bookService.insertBook(bookDto);
        model.addAttribute(saved);
        return "redirect:/edit?id=" + saved.getId();
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("book", bookDto);
        model.addAttribute("authorsList", authorWebService.getAllAuthors());
        model.addAttribute("genresList", genreWebService.getAllGenres());
        return "new";
    }

    @PostMapping("/new")
    public String newBook(@ModelAttribute("book") BookDto bookDto) {
        bookDto.setId(UUID.randomUUID());
        bookService.insertBook(bookDto);
        return "redirect:/";
    }
}
