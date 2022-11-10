package com.example.Bookstore1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Bookstore1.domain.Book;
import com.example.Bookstore1.domain.BookRepository;
import com.example.Bookstore1.domain.Category;
import com.example.Bookstore1.domain.CategoryRepository;
import com.example.Bookstore1.domain.User;
import com.example.Bookstore1.domain.UserRepository;

@SpringBootApplication
public class Bookstore1Application {

	private static final Logger log = LoggerFactory.getLogger(Bookstore1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Bookstore1Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository, CategoryRepository crepository,
			UserRepository urepository) {

		return (args) -> {

			log.info("Categorys");

			crepository.save(new Category("Horror"));
			crepository.save(new Category("Romantic"));
			crepository.save(new Category("Action"));
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Thriller"));

			log.info("Testing books");
			repository.save(new Book("Test Book", "Test Author", 1988, "12322-232495", 20.00,
					crepository.findByName("Horror").get(0)));
			repository.save(new Book("Test Book 2", "Author Nauthor", 1995, "123-09876", 400.90,
					crepository.findByName("Action").get(0)));
			repository.save(new Book("Test Book 3", "Guy Writer", 2019, "754-948545", 3000.05,
					crepository.findByName("Drama").get(0)));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER",
					"nikke@gmail.com");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN",
					"nikke1@gmail.com");
			urepository.save(user1);
			urepository.save(user2);

			;
		};

	}
}
