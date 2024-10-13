package com.fmajorcminor.libraryapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fmajorcminor.libraryapi.entity.Author;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Long> {
    // @Query("")
    public boolean existsByFirstNameAndLastName(String firstName, String lastName);

    public Author findByFirstNameAndLastName(String firstName, String lastName);
}
