module FrontOfHouse {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens org.main;
    exports org.main.view.login to javafx.graphics, javafx.fxml;
    exports org.main.application to javafx.graphics, javafx.fxml;
    exports org.main.view.table to javafx.graphics, javafx.fxml;
    opens org.main.view.table to javafx.fxml, javafx.base;
    opens org.main.view.login to javafx.fxml, javafx.base;
}