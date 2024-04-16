package com.nyanmyohtet.studentregistrationservice;

import com.nyanmyohtet.studentregistrationservice.persistence.dao.BookDao;
import com.nyanmyohtet.studentregistrationservice.persistence.model.Book;
import com.nyanmyohtet.studentregistrationservice.persistence.model.User;
import com.nyanmyohtet.studentregistrationservice.persistence.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
public class Application {

	private static final Logger logger = LogManager.getLogger(Application.class);

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

			Optional.ofNullable(bookDao.getAllBooks())
					.filter(books -> !books.isEmpty())
					.ifPresentOrElse(books -> logger.info("Books: {}", books),
						() -> {
							Book book = new Book();
							book.setTitle("title");
							book.setAuthor("author");
							bookDao.saveBook(book);
					});

            logger.info("getBooksByAuthor: {}", bookDao.getBooksByAuthor("author"));
            logger.info("getBooksByTitleJPQL: {}", bookDao.getBooksByTitleJPQL("title"));
            logger.info("getBooksByTitleHQL: {}", bookDao.getBooksByTitleHQL("title"));
		};
	}

}
