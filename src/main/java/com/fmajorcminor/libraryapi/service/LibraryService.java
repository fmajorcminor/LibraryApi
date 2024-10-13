package com.fmajorcminor.libraryapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fmajorcminor.libraryapi.dto.author.AuthorRequestDTO;
import com.fmajorcminor.libraryapi.dto.book.BookRequestDTO;
import com.fmajorcminor.libraryapi.entity.Author;
import com.fmajorcminor.libraryapi.entity.Book;
import com.fmajorcminor.libraryapi.repository.IAuthorRepository;
import com.fmajorcminor.libraryapi.repository.IBookRepository;

@Service
public class LibraryService {

    @Autowired
    private IAuthorRepository authorRepository;
    @Autowired
    private IBookRepository bookRepository;

    public ResponseEntity<?> saveAuthor(AuthorRequestDTO authorRequestDTO) {
        Author author = new Author(authorRequestDTO.getFirstName(), authorRequestDTO.getLastName(),
                authorRequestDTO.getBio());
        Author existingAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(),
                author.getLastName());
        if (null == existingAuthor) {
            return new ResponseEntity<Author>(authorRepository.save(author), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Author already exists", HttpStatus.BAD_REQUEST);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    public ResponseEntity<?> updateAuthor(AuthorRequestDTO authorRequestDTO, Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            Author updatedAuthor = new Author(author.get().getAuthorId(), authorRequestDTO.getFirstName(),
                    authorRequestDTO.getLastName(), authorRequestDTO.getBio());
            return new ResponseEntity<>(authorRepository.save(updatedAuthor), HttpStatus.OK);
        }
        return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteAuthor(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            List<Book> books = bookRepository.findAllByAuthor(author.get());
            bookRepository.deleteAllById(books.stream().map(book -> book.getBookId()).toList());
        }
        authorRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<?> saveBook(BookRequestDTO bookRequestDTO) {
        Author author = new Author(bookRequestDTO.getAuthor().getFirstName(), bookRequestDTO.getAuthor().getLastName(),
                bookRequestDTO.getAuthor().getBio());
        Author existingAuthor = authorRepository.findByFirstNameAndLastName(author.getFirstName(),
                author.getLastName());
        Book existingBook = new Book();
        if (null != existingAuthor) {
            author = existingAuthor;
            existingBook = bookRepository.findByTitleAndAuthor(bookRequestDTO.getTitle(), author);
            if (null != existingBook) {
                return new ResponseEntity<>("Book already exists", HttpStatus.BAD_REQUEST);
            }
        }
        authorRepository.save(author);
        Book book = new Book(bookRequestDTO.getTitle(), bookRequestDTO.getDescription(), author,
                bookRequestDTO.getPublishedDate());

        return new ResponseEntity<Book>(bookRepository.save(book), HttpStatus.CREATED);

    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // In short, this method will also make sure that the author is appropriately
    // updated if need be
    public ResponseEntity<?> updateBook(BookRequestDTO bookRequestDTO, Long id) {

        if (null == bookRequestDTO.getAuthor() || null == bookRequestDTO.getAuthor().getFirstName()
                || null == bookRequestDTO.getAuthor().getLastName()) {
            return new ResponseEntity<>("Author info is required", HttpStatus.BAD_REQUEST);
        }
        Optional<Book> book = bookRepository.findById(id);

        Author author = new Author();
        author = authorRepository.findByFirstNameAndLastName(bookRequestDTO.getAuthor().getFirstName(),
                bookRequestDTO.getAuthor().getLastName());
        if (null == author) {
            author = new Author(bookRequestDTO.getAuthor().getFirstName(),
                    bookRequestDTO.getAuthor().getLastName(), bookRequestDTO.getAuthor().getBio());
        }
        authorRepository.save(author);

        if (book.isPresent()) {
            Book updatedBook = new Book(book.get().getBookId(), bookRequestDTO.getTitle(),
                    bookRequestDTO.getDescription(), author, bookRequestDTO.getPublishedDate());
            return new ResponseEntity<>(bookRepository.save(updatedBook), HttpStatus.OK);
        }
        return new ResponseEntity<>("Author not found", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteBook(Long id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
