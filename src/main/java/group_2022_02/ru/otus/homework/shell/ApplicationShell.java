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
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private GenreDao genreDao;

    @ShellMethod(value = "Getting book by name", key = {"b", "book"})
    public void getBook(){
        System.out.println("Write needed name");
        Scanner in = new Scanner(System.in);
        String name = in.next();
        System.out.println(bookDao.getByName(name));
    }

    @ShellMethod(value = "Getting all authors", key = {"a", "authors"})
    public void getAllAuthors(){
        System.out.println(authorDao.getAll());
    }

    @ShellMethod(value = "Getting genre by name", key = {"g", "genre"})
    public void getGenreByName(){
        Scanner in = new Scanner(System.in);
        String name = in.next();
        System.out.println(genreDao.getByName(name));
    }

    @ShellMethod(value = "Insert book", key = {"insert book"})
    public void insertBook(){
        Scanner in = new Scanner(System.in);
        System.out.println("Write id book:");
        long id = in.nextInt();
        if (!bookDao.checkId(id)){
            System.out.println("Write name:");
            String name = in.next();
            System.out.println("Write id author:");
            int author = in.nextInt();
            System.out.println("Write count of pages:");
            int page = in.nextInt();
            System.out.println("Write id genre:");
            int genre = in.nextInt();
            Book newBook = new Book(id, name, page, author, genre);
            bookDao.insert(newBook);
        }
        else System.out.println("Book with such id is already exist, please, restart with other id");
    }
}
