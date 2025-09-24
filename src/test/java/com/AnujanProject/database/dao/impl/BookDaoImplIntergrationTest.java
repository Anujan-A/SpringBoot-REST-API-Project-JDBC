package com.AnujanProject.database.dao.impl;

import com.AnujanProject.database.Dao.impl.AuthorDaoImpl;
import com.AnujanProject.database.Dao.impl.BookDaoImpl;
import com.AnujanProject.database.TestDataUtil;
import com.AnujanProject.database.domain.Author;
import com.AnujanProject.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntergrationTest {

    private AuthorDaoImpl authorDao;
    private BookDaoImpl underTest;

    @Autowired
    BookDaoImplIntergrationTest(BookDaoImpl underTest, AuthorDaoImpl authorDao){
        this.underTest = underTest;
        this.authorDao = authorDao;
    }

    @Test
    public void testThatBookCanBeCreatedAndCalled(){
        Author author = TestDataUtil.createTestAuthor1();
        Book book = TestDataUtil.createTestBook1();
        book.setAuthorId(author.getId());
        authorDao.create(author);
        underTest.create(book);
        Optional<Book> result = underTest.find(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

    @Test
    public void testThatManyBooksCanBeCreatedAndCalled(){
        Author author1 = TestDataUtil.createTestAuthor1();
        authorDao.create(author1);

        Book book1 = TestDataUtil.createTestBook1();
        underTest.create(book1);

        Book book2 = TestDataUtil.createTestBook2();
        underTest.create(book2);

        List<Book> books = underTest.findMany();
        assertThat(books).hasSize(2);
        assertThat(books).containsExactly(book1, book2);
    }
}
