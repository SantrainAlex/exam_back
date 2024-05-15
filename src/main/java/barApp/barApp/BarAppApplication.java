package barApp.barApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class BarAppApplication {

	public static void main(String[] args) {
		System.out.println("Hello world!");
		SpringApplication.run(BarAppApplication.class, args);
	}

}
