package org.main.view.login;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.main.core.ViewHandler;
import org.main.core.ViewModelFactory;
import org.main.view.ViewController;

public class LoginViewController extends ViewController {

    public ImageView lancasterImageView;
    @FXML
   private TextField staffNameTextField;
    @FXML
    private AnchorPane currentPane;
    @FXML
    private TextField roleTextField;

    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;


    public void init()
    {
        this.loginViewModel = ViewModelFactory.getInstance().getLoginViewModel();
        this.viewHandler = ViewHandler.getInstance();
        //Add listeners later
        staffNameTextField.textProperty().bindBidirectional(this.loginViewModel.staffNameProperty());
        roleTextField.textProperty().bindBidirectional(this.loginViewModel.roleProperty());
    }

    public void LoginStaff(ActionEvent actionEvent) {
        if(loginViewModel.login() && roleTextField.textProperty().getValue().equals("Maitre"));
        viewHandler.openBookingView();
        if(loginViewModel.login() && roleTextField.textProperty().getValue().equals("Waiter") || roleTextField.textProperty().getValue().equals("Sommelier"))
            viewHandler.openOrderingView();
    }

    public void onCloseButton(ActionEvent actionEvent) {
        viewHandler.close();
    }

    public void onMinimiseButton(ActionEvent actionEvent) {
        viewHandler.minimize();
    }
}
