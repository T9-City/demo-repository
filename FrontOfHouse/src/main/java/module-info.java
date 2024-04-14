module FrontOfHouse {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens org.main;
    exports org.main.view.login to javafx.graphics, javafx.fxml;
    exports org.main.application to javafx.graphics, javafx.fxml;
    opens org.main.view.login to javafx.fxml, javafx.base;

    opens org.main.view.booking.viewBookings to javafx.fxml, javafx.base;
    opens org.main.view.booking.createBookings to javafx.fxml, javafx.base;
    opens org.main.view.booking.editBookings to javafx.fxml, javafx.base;
    exports org.main.view.booking.viewBookings to javafx.fxml, javafx.graphics;
    exports org.main.view.booking.createBookings to javafx.fxml, javafx.graphics;
    exports org.main.view.booking.editBookings to javafx.fxml, javafx.graphics;

    opens org.main.view.order to javafx.fxml, javafx.base;
    opens org.main.view.ViewOrder to javafx.fxml, javafx.base;
    exports org.main.view.order to javafx.fxml, javafx.graphics;
    exports org.main.view.ViewOrder to javafx.fxml, javafx.graphics;
}