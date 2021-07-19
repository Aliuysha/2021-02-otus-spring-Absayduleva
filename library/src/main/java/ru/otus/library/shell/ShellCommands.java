package ru.otus.library.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.library.service.BookService;

@ShellComponent
public class ShellCommands {
    private final BookService bookService;

    public ShellCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod(value = "Get all books command", key = {"b", "books"})
    public String getAllBooks() {
        return bookService.getAllBooks();
    }

    @ShellMethod(value = "New book command", key = {"n", "new"})
    public String newBook() {
        return bookService.insertBook();
    }

    @ShellMethod(value = "Get book by id command", key = {"g", "get"})
    public String getBookById() {
        return bookService.getBookById();
    }

    @ShellMethod(value = "Delete book by id command", key = {"d", "delete"})
    public void deleteBook() {
        bookService.deleteBook();
    }

    @ShellMethod(value = "Get all comments by id command", key = {"c", "comment"})
    public String getAllComments() {
        return bookService.getAllCommentsByBook();
    }

    @ShellMethod(value = "Change book name by id command", key = {"ch", "change"})
    public void changeBookName() {
        bookService.updateNameById();
    }

}
