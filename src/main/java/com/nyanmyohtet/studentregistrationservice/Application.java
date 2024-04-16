package com.nyanmyohtet.studentregistrationservice;

import com.nyanmyohtet.studentregistrationservice.persistence.dao.BookDao;
import com.nyanmyohtet.studentregistrationservice.persistence.model.Book;
import com.nyanmyohtet.studentregistrationservice.persistence.model.User;
import com.nyanmyohtet.studentregistrationservice.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository, BookDao bookDao) {
		return args -> {
			List<User> all = userRepository.findAll();
			if (all.isEmpty()) {
				BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				String password = passwordEncoder.encode("admin123");

				User user = new User();
				user.setPassword(password);
				user.setEmail("user@example.com");

				userRepository.save(user);
				User savedUser = userRepository.findById(user.getId()).orElseThrow();
			}

			if (bookDao.getAllBooks().isEmpty()) {
				Book book = new Book();
				book.setTitle("title");
				book.setAuthor("author");
				bookDao.saveBook(book);
			}
			System.out.println(">>> getBooksByAuthor: " + bookDao.getBooksByAuthor("author"));
			System.out.println(">>> getBooksByTitleJPQL: " + bookDao.getBooksByTitleJPQL("title"));
			System.out.println(">>> getBooksByTitleHQL: " + bookDao.getBooksByTitleHQL("title"));
		};
	}

}
