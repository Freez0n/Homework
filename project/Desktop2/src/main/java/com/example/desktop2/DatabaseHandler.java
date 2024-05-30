package com.example.desktop2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {

    Connection connection;

    public Connection getDBConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://localhost:3306/library";
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Сергей Игоревич, введите нужный пароль от вашей базы данных,
        //а так же не забудьте ее развернуть на своем компьютере через mysql,
        //файл с кодом лежит в репозиторие :3
        connection = DriverManager.getConnection(connectionString, "root", "19962005");
        return connection;
    }

    public ResultSet getBooks() throws SQLException, ClassNotFoundException {
        String getBooks = "SELECT b.book_id, b.book_title, a.author_name, g.genre_name\n" +
                "FROM library.books b\n" +
                "JOIN library.authors a ON b.author_id = a.author_id\n" +
                "JOIN library.genres g ON b.genre_id = g.genre_id;";
        PreparedStatement prSt = getDBConnection().prepareStatement(getBooks);
        return prSt.executeQuery();
    }

    public void insertGenre(String genreName) throws SQLException, ClassNotFoundException {
        String insertGenreQuery = "INSERT INTO library.genres (genre_name) VALUES (?)";
        PreparedStatement prSt = getDBConnection().prepareStatement(insertGenreQuery);
        prSt.setString(1, genreName);
        prSt.executeUpdate();
    }

    public void insertAuthor(String authorName) throws SQLException, ClassNotFoundException {
        String insertAuthorQuery = "INSERT INTO library.authors (author_name) VALUES (?)";
        PreparedStatement prSt = getDBConnection().prepareStatement(insertAuthorQuery);
        prSt.setString(1, authorName);
        prSt.executeUpdate();
    }

    public void insertBook(String bookTitle, String authorName, String genreName) throws SQLException, ClassNotFoundException {
        int authorId = getAuthorIdByName(authorName);
        if (authorId == -1) {
            insertAuthor(authorName);
            authorId = getAuthorIdByName(authorName);
        }

        int genreId = getGenreIdByName(genreName);
        if (genreId == -1) {
            insertGenre(genreName);
            genreId = getGenreIdByName(genreName);
        }

        String insertBookQuery = "INSERT INTO library.books (book_title, author_id, genre_id) VALUES (?, ?, ?)";
        PreparedStatement prSt = getDBConnection().prepareStatement(insertBookQuery);
        prSt.setString(1, bookTitle);
        prSt.setInt(2, authorId);
        prSt.setInt(3, genreId);
        prSt.executeUpdate();
    }

    private int getAuthorIdByName(String authorName) throws SQLException, ClassNotFoundException {
        String query = "SELECT author_id FROM library.authors WHERE author_name = ?";
        PreparedStatement prSt = getDBConnection().prepareStatement(query);
        prSt.setString(1, authorName);
        ResultSet resultSet = prSt.executeQuery();
        return resultSet.next() ? resultSet.getInt(1) : -1;
    }

    private int getGenreIdByName(String genreName) throws SQLException, ClassNotFoundException {
        String query = "SELECT genre_id FROM library.genres WHERE genre_name = ?";
        PreparedStatement prSt = getDBConnection().prepareStatement(query);
        prSt.setString(1, genreName);
        ResultSet resultSet = prSt.executeQuery();
        return resultSet.next() ? resultSet.getInt(1) : -1;
    }
}
