module com.example.freeman {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.freeman to javafx.fxml;
    exports com.example.freeman;
    exports main;
    opens main to javafx.fxml;
    exports main.Vista;
    opens main.Vista to javafx.fxml;
}