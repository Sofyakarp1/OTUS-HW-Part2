package group_2022_02.ru.otus.homework.shell;

import group_2022_02.ru.otus.homework.dao.AuthorDao;
import group_2022_02.ru.otus.homework.dao.BookDao;
import group_2022_02.ru.otus.homework.dao.GenreDao;
import group_2022_02.ru.otus.homework.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import java.util.Scanner;

@ShellComponent
@PropertySource("classpath:application.yml")
public class ApplicationShell {

    @Autowired
    private BookDao book;

    @Autowired
    private AuthorDao author;

    @Autowired
    private GenreDao genre;

    @ShellMethod(value = "Getting book by id", key = {"b", "book"})
    public void getBook(){
        System.out.println("Write needed id");
        Scanner in = new Scanner(System.in);
        int id = in.nextInt();
        System.out.println(book.getById(id));
    }

    @ShellMethod(value = "Getting all authors", key = {"a", "authors"})
    public void getAllAuthors(){
        System.out.println(author.getAll());
    }

    @ShellMethod(value = "Getting genre by name", key = {"g", "genre"})
    public void getGenreByName(){
        Scanner in = new Scanner(System.in);
        String name = in.next();
        System.out.println(genre.getByName(name));
    }

    @ShellMethod(value = "Insert book", key = {"insert book"})
    public void insertBook(){
        Scanner in = new Scanner(System.in);
        System.out.println("Write id book:");
        long id = in.nextInt();
        if (!book.checkId(id)){
            System.out.println("Write name:");
            String name = in.next();
            System.out.println("Write id author:");
            int author = in.nextInt();
            System.out.println("Write count of pages:");
            int page = in.nextInt();
            System.out.println("Write id genre:");
            int genre = in.nextInt();
            Book newBook = new Book(id, name, page, author, genre);
            book.insert(newBook);
        }
        else System.out.println("Book with such id is already exist, please, restart with other id");
    }
}
