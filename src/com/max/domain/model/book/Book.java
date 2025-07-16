package com.max.domain.model.book;

public class Book {
    private final String title;
    private final String author;
    private boolean isAvailable = true;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public boolean isAvailable() { return isAvailable; }
    public void markBorrowed() { isAvailable = false; }
    public void markReturned() { isAvailable = true; }

    @Override
    public String toString() {
        return title + " by " + author;
    }

    public String getTitle() { return title; }
}