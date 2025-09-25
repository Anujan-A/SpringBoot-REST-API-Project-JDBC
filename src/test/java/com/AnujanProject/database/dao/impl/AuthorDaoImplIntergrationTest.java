package com.AnujanProject.database.dao.impl;

import com.AnujanProject.database.Dao.impl.AuthorDaoImpl;
import com.AnujanProject.database.TestDataUtil;
import com.AnujanProject.database.domain.Author;
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
public class AuthorDaoImplIntergrationTest {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoImplIntergrationTest(AuthorDaoImpl underTest){
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndCalled(){

        Author author = TestDataUtil.createTestAuthor1();
        underTest.create(author);
        Optional<Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatManyAuthorsCanBeCreatedAndCalled(){
        Author author1 = TestDataUtil.createTestAuthor1();
        Author author2 = TestDataUtil.createTestAuthor2();

        underTest.create(author1);
        underTest.create(author2);
        List<Author> result = underTest.findMany();
        assertThat(result).hasSize(2);
        assertThat(result).containsExactly(author1,author2);
    }

    @Test
    public void testThatAuthorCanBeUpdated(){
        Author author = TestDataUtil.createTestAuthor1();
        underTest.create(author);
        author.setName("Updated");
        underTest.update(author.getId(), author);

        Optional <Author> result = underTest.findOne(author.getId());
        assertThat(result).isPresent();
        assertThat(author.getName()).isEqualTo(result.get().getName());
    }

    @Test
    public void testThatAuthorCanBeDeleted(){
        Author author = TestDataUtil.createTestAuthor1();
        underTest.create(author);
        underTest.delete(author.getId());
        Optional<Author> result = underTest.findOne(author.getId());

        assertThat(result).isEmpty();
    }

}
