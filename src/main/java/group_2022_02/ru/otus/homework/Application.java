package group_2022_02.ru.otus.homework;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class Application {


    public static void main(String[] args) throws SQLException {
        new SpringApplication(Application.class).run(args);
        Console.main(args);
    }
}
