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


    public void init()
    {
        this.loginViewModel = ViewModelFactory.getInstance().getLoginViewModel();
        this.viewHandler = ViewHandler.getInstance();
        initComboBox();
        //Add listeners later
        roleComboBox.valueProperty().bindBidirectional(this.loginViewModel.roleProperty());
        staffNameTextField.textProperty().bindBidirectional(this.loginViewModel.staffNameProperty());
    }

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

    public void onCloseButton(ActionEvent actionEvent) {
        viewHandler.close();
    }

    public void onMinimiseButton(ActionEvent actionEvent) {
        viewHandler.minimize();
    }

    private void initComboBox()
    {
        roleComboBox.getItems().addAll("SOMMELIER","WAITER","MAITRE");
        roleComboBox.getSelectionModel().selectFirst();
    }
}
