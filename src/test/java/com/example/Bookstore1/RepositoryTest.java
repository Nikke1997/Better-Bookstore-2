package com.example.Bookstore1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.Bookstore1.domain.Book;
import com.example.Bookstore1.domain.BookRepository;
import com.example.Bookstore1.domain.Category;
import com.example.Bookstore1.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTest {

	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository repository1;

	// Testing searching
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("Test Book");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Test Author");
	}

	// Testing create functionality
	@Test
	public void createNewBook() {
		Book book = new Book("Olipa", "Kerran", 1997, "Hiiri", 13.00, repository1.findByName("Horror").get(0));
		repository.save(book);
		assertThat(book.getId()).isNotNull();

	}

	// Testing deleting
	@Test
	public void deleteBook() {
		List<Book> books = repository.findByTitle("Test Book");
		Book book = books.get(0);
		repository.delete(book);
		List<Book> newBooks = repository.findByTitle("Test Book");
		assertThat(newBooks).hasSize(0);

	}

	// Testing searching
	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = repository1.findByName("Horror");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo("Horror");
	}

	// Testing Creating
	@Test
	public void createNewCategory() {
		Category category = new Category("Horror");
		repository1.save(category);
		assertThat(category.getId()).isNotNull();

	}

	// Testing deleting
	@Test
	public void deleteCategory() {
		List<Category> categories = repository1.findByName("Thriller");
		Category category = categories.get(0);
		repository1.delete(category);
		List<Category> newCategories = repository1.findByName("Thriller");
		assertThat(newCategories).hasSize(0);

	}

}
