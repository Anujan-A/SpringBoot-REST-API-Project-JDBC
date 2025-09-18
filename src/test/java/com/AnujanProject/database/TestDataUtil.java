package com.AnujanProject.database;

import com.AnujanProject.database.domain.Author;
import com.AnujanProject.database.domain.Book;

public final class TestDataUtil {

    private TestDataUtil(){

    }

    public static Author createTestAuthor() {
        return Author.builder()
                .id(1L)
                .name("Anujan")
                .age(88)
                .build();
    }

    public static Book createTestBook() {
        Book book = Book.builder()
                .isbn("123")
                .title("Titel")
                .authorId(1L)
                .build();
        return book;
    }
}
