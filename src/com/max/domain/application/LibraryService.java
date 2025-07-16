package com.max.domain.application;

import com.max.domain.model.book.Book;
import com.max.domain.model.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class LibraryService {
    private final List<Book> catalog = new ArrayList<>();
    private final Map<String, User> users = new HashMap<>();

    public void addBook(String title, String author) {
        catalog.add(new Book(title, author));
    }

    public void borrowBook(String userName, String bookTitle) {
        Book book = findBook(bookTitle);
        if (book == null || !book.isAvailable()) {
            System.out.println("Book not available: " + bookTitle);
            return;
        }
        User user = users.computeIfAbsent(userName, User::new);
        user.borrowBook(book);
        book.markBorrowed();
        System.out.println(userName + " borrowed: " + bookTitle);
    }

    public void returnBook(String userName, String bookTitle) {
        User user = users.get(userName);
        Book book = findBook(bookTitle);
        if (user == null || book == null || !user.getBorrowedBooks().contains(book)) {
            System.out.println("Error returning book: " + bookTitle);
            return;
        }
        user.returnBook(book);
        book.markReturned();
        System.out.println(userName + " returned: " + bookTitle);
    }

    public void printBorrowedBooks(String userName) {
        User user = users.get(userName);
        if (user != null && !user.getBorrowedBooks().isEmpty()) {
            System.out.println(userName + " borrowed:");
            for (Book b : user.getBorrowedBooks()) {
                System.out.println("- " + b);
            }
        } else {
            System.out.println(userName + " has no borrowed books.");
        }
    }

    private Book findBook(String title) {
        return catalog.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
}

