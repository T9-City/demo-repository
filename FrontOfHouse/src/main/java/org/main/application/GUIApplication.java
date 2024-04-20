package org.main.application;

import javafx.application.Application;
import javafx.stage.Stage;
import org.main.core.ViewHandler;

/**
 * @author Mihail Constantin
 * @version 1.0
 * Extension of the javafx application class
 */
public class GUIApplication extends Application {
    /**
     *  Implementation of the start method from Application,
     * @param stage the GUI stage
     * @throws Exception in case anything happens
     */
    @Override
    public void start(Stage stage) throws Exception {

        ViewHandler viewHandler = ViewHandler.getInstance();
        viewHandler.start();
    }
}
