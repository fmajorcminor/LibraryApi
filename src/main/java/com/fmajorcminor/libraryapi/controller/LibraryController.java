package com.fmajorcminor.libraryapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fmajorcminor.libraryapi.dto.author.AuthorRequestDTO;
import com.fmajorcminor.libraryapi.dto.book.BookRequestDTO;
import com.fmajorcminor.libraryapi.entity.Author;
import com.fmajorcminor.libraryapi.entity.Book;
import com.fmajorcminor.libraryapi.service.LibraryService;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequestMapping("/api/v1")
public class LibraryController {
    private LibraryService libraryService;

    public LibraryController(@Autowired LibraryService service) {
        this.libraryService = service;
    }

    @PostMapping("/authors")
    public ResponseEntity<?> createAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
        try {
            return libraryService.saveAuthor(authorRequestDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors() {
        try {
            return new ResponseEntity<>(libraryService.getAllAuthors(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/authors/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable Long id) {
        try {
            Optional<Author> author = libraryService.getAuthorById(id);
            if (!author.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Author>(author.get(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity<?> updateAuthor(@RequestBody AuthorRequestDTO author, @PathVariable Long id) {
        try {
            return libraryService.updateAuthor(author, id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        try {
            return libraryService.deleteAuthor(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        try {
            return libraryService.saveBook(bookRequestDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            return new ResponseEntity<List<Book>>(libraryService.getAllBooks(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        try {
            Optional<Book> book = libraryService.getBookById(id);
            if (!book.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<Book>(book.get(),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@RequestBody BookRequestDTO bookRequestDTO, @PathVariable Long id) {
        try {
            return libraryService.updateBook(bookRequestDTO, id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            return libraryService.deleteBook(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
