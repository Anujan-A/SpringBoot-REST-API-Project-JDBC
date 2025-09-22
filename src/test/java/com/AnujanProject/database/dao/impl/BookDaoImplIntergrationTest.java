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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
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
        Book book = TestDataUtil.createTestBook();
        book.setAuthorId(author.getId());
        authorDao.create(author);
        underTest.create(book);
        Optional<Book> result = underTest.find(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);
    }

}
