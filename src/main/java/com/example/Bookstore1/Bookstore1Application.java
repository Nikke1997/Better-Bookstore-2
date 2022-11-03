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

			log.info("save a couple of books");

			crepository.save(new Category("Horror"));
			crepository.save(new Category("Romantic"));
			crepository.save(new Category("Action"));
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Thriller"));

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
