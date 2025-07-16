package com.max.domain.model.user;

import com.max.domain.model.book.Book;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final List<Book> borrowedBooks = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }

    public void borrowBook(Book book) { borrowedBooks.add(book); }
    public void returnBook(Book book) { borrowedBooks.remove(book); }

    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
}
