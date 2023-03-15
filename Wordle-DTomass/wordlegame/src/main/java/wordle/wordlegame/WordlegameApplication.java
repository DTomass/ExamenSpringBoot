package wordle.wordlegame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("wordle.wordlegame.*")
public class WordlegameApplication {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WordlegameApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(WordlegameApplication.class, args);
	}

}
