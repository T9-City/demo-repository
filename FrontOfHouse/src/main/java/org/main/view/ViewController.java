package org.main.view;

import javafx.scene.Parent;
import javafx.scene.layout.Region;

/**
 * @author Mihail Constantin
 * @version 1.0
 * Abstract class of ViewController. It has the main methods it needs for a ViewController type class.
 */
public abstract class ViewController {

    private Region root;

    public void init() {
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Region root) {
        this.root = root;
    }
}
