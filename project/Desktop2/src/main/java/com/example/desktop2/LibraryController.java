package com.example.desktop2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LibraryController implements Initializable {

    @FXML
    public TableView<Library> LibraryTable;
    @FXML
    public TableColumn<Library, Integer> idBook;
    @FXML
    public TableColumn<Library, String> nameBook;
    @FXML
    public TableColumn<Library, String> nameAuthor;
    @FXML
    public TableColumn<Library, String> nameGenre;
    @FXML
    public TextField bookTitleField;
    @FXML
    public TextField authorNameField;
    @FXML
    public TextField genreNameField;
    @FXML
    public Button addButton;

    DatabaseHandler dbHandler = new DatabaseHandler();

    private final ObservableList<Library> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            addInfAboutBook();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        idBook.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        nameBook.setCellValueFactory(new PropertyValueFactory<>("book_title"));
        nameAuthor.setCellValueFactory(new PropertyValueFactory<>("author_name"));
        nameGenre.setCellValueFactory(new PropertyValueFactory<>("genre_name"));
        LibraryTable.setItems(data);
    }

    private void addInfAboutBook() throws SQLException, ClassNotFoundException {
        ResultSet library = dbHandler.getBooks();
        while (library.next()) {
            Library libraryItem = new Library(library.getInt(1), library.getString(2), library.getString(3), library.getString(4));
            data.add(libraryItem);
        }
    }

    @FXML
    public void addButtonClicked() {
        String bookTitle = bookTitleField.getText();
        String authorName = authorNameField.getText();
        String genreName = genreNameField.getText();
        try {
            dbHandler.insertBook(bookTitle, authorName, genreName);
            clearFields();
            refreshTable();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        bookTitleField.clear();
        authorNameField.clear();
        genreNameField.clear();
    }

    private void refreshTable() {
        data.clear();
        try {
            addInfAboutBook();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
