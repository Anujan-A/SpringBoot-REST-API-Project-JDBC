package com.AnujanProject.database;

import com.AnujanProject.database.domain.Author;
import com.AnujanProject.database.domain.Book;

public final class TestDataUtil {

    private TestDataUtil(){

    }

    public static Author createTestAuthor1() {
        return Author.builder()
                .id(1L)
                .name("Hohenheim")
                .age(88)
                .build();
    }

    public static Author createTestAuthor2() {
        return Author.builder()
                .id(2L)
                .name("Hellheim")
                .age(30)
                .build();
    }

    public static Book createTestBook1() {
        Book book = Book.builder()
                .isbn("123")
                .title("Titel")
                .authorId(1L)
                .build();
        return book;
    }

    public static Book createTestBook2() {
        Book book = Book.builder()
                .isbn("321")
                .title("Buch")
                .authorId(1L)
                .build();
        return book;
    }
}
