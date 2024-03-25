package org.main.applicaiton;

import javafx.application.Application;
import javafx.stage.Stage;
import org.main.core.ViewHandler;

public class GUIApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ViewHandler viewHandler = ViewHandler.getInstance();
        viewHandler.start();
    }
}
