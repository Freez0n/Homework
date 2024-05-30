module com.example.desktop2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.desktop2 to javafx.fxml;
    exports com.example.desktop2;
}