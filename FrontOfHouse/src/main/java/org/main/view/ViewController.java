package org.main.view;

import javafx.scene.Parent;
import javafx.scene.layout.Region;

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
