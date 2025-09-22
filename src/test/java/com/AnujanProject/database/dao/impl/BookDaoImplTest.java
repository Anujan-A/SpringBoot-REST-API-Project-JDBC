package com.AnujanProject.database.dao.impl;

import com.AnujanProject.database.Dao.impl.BookDaoImpl;
import com.AnujanProject.database.TestDataUtil;
import com.AnujanProject.database.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class BookDaoImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGeneratesCorrectSql(){
        Book book = TestDataUtil.createTestBook();
        underTest.create(book);

        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn, title, author_id) VALUES (?,?,?)"),
                eq("123"), eq("Titel"), eq(1L)
        );
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql(){
        underTest.find("123");

        verify(jdbcTemplate).query(
                eq("SELECT isbn, title, author_id FROM books WHERE isbn = ? LIMIT 1"),
                ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),
                eq("123")
        );
    }
}
