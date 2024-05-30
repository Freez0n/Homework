package com.example.desktop2;

public class Library {
    private int book_id;
    private String book_title;
    private String author_name;
    private String genre_name;

    public Library(int book_id, String book_title, String author_name, String genre_name) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.author_name = author_name;
        this.genre_name = genre_name;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }
}
