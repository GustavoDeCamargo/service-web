package guru.springframework;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//testando o commit
@SpringBootApplication
public class WebServiceApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(WebServiceApplication.class, args);

    }
}
