package com.twu.biblioteca;


import java.util.Objects;

public class Book
{
    private String author;
    private int publishedYear;

    public Book(String author, int publishedYear) {
        this.author = author;
        this.publishedYear = publishedYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", publishedYear=" + publishedYear +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publishedYear == book.publishedYear &&
                author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, publishedYear);
    }
}
