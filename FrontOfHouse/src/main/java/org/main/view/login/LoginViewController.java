package org.main.view.login;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.main.core.ViewHandler;
import org.main.core.ViewModelFactory;
import org.main.view.ViewController;

/**
 * @author Mihail Constantin
 * @version 2.0
 * LoginViewController implementation of ViewController
 * It's the controller of LoginView.fxml
 * It controls the GUI of the login functionality
 */
public class LoginViewController extends ViewController {

    public ImageView lancasterImageView;
    public Text errorText;
    @FXML
   private TextField staffNameTextField;
    @FXML
    private AnchorPane currentPane;

    @FXML
    private ComboBox<String> roleComboBox;

    private LoginViewModel loginViewModel;
    private ViewHandler viewHandler;

    /**
     * Implementation of init() from ViewController.
     * This method binds data from the GUI to the ViewModel, initialises specific GUI elements
     * And gets the instances of ViewHandler and ViewModel.
     */
    public void init()
    {
        this.loginViewModel = ViewModelFactory.getInstance().getLoginViewModel();
        this.viewHandler = ViewHandler.getInstance();
        initComboBox();
        //Add listeners later
        roleComboBox.valueProperty().bindBidirectional(this.loginViewModel.roleProperty());
        staffNameTextField.textProperty().bindBidirectional(this.loginViewModel.staffNameProperty());
    }

    /**
     * Button action implementation; This is called when the button for login is pressed.
     * This method then opens a booking according to what is received from the ViewModel
     * and based on what role was selected.
     * @param actionEvent
     */
    public void LoginStaff(ActionEvent actionEvent) {
        System.out.println(roleComboBox.getValue());
        switch(roleComboBox.getValue())
        {
            case "MAITRE":
                if(loginViewModel.login())
                {
                    System.out.println(roleComboBox.getValue());
                    viewHandler.openBookingView();
                }

                break;
            case "WAITER","SOMMELIER":
                if(loginViewModel.login())
                {
                    System.out.println(roleComboBox.getValue());
                    viewHandler.openOrderingView();
                }

                break;
        }
        errorText.setText("Something has gone wrong with the login!");
    }

    /**
     * Method that implements a close button; it closes the GUI when pressed.
     * @param actionEvent
     */
    public void onCloseButton(ActionEvent actionEvent) {
        viewHandler.close();
    }

    /**
     * Method that implements a minimise button; it minimises the GUI when pressed.
     * @param actionEvent
     */
    public void onMinimiseButton(ActionEvent actionEvent) {
        viewHandler.minimize();
    }

    /**
     * Method that initialises the values in the ComboBox with set roles.
     */
    private void initComboBox()
    {
        roleComboBox.getItems().addAll("SOMMELIER","WAITER","MAITRE");
        roleComboBox.getSelectionModel().selectFirst();
    }
}
