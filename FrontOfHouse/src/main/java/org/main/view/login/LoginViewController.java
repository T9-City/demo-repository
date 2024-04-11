package org.main.view.login;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.main.core.ViewHandler;
import org.main.core.ViewModelFactory;
import org.main.view.ViewController;

public class LoginViewController extends ViewController {

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
        loginViewModel.login();
    }
}
