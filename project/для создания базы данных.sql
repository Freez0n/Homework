CREATE SCHEMA library;

CREATE TABLE library.genres (
    genre_id INT AUTO_INCREMENT PRIMARY KEY,
    genre_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE library.authors (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE library.books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    book_title VARCHAR(100) NOT NULL,
    author_id INT,
    genre_id INT,
    FOREIGN KEY (author_id) REFERENCES library.authors(author_id),
    FOREIGN KEY (genre_id) REFERENCES library.genres(genre_id)
);