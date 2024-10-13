package com.fmajorcminor.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.fmajorcminor.libraryapi.entity.Author;
import com.fmajorcminor.libraryapi.entity.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
    public Book findByTitleAndAuthor(String title, Author author);

    public List<Book> findAllByAuthor(Author author);
}
