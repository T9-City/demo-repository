package org.main.view.table;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.main.core.ViewHandler;
import org.main.core.ViewModelFactory;
import org.main.view.ViewController;


public class TableViewController extends ViewController {

    @FXML
    private Button AddTable;
    @FXML
    private Button RemoveTable;

    @FXML
    private Button EditTable;

    @FXML
    private Button AssignTable;
    @FXML
    private Label Label;

//    @FXML
//    private CheckBox AvailabilityCheckBox;

//    @FXML
//    private ImageView TableImg;

    private TableViewModel tableViewModel;

    private int numOfTables = 15;
    private ViewHandler viewHandler;
    public void handleButtonAction(ActionEvent actionEvent) {
    }
    @FXML
    public void initialise(){
        this.tableViewModel = new TableViewModel();

        Image TblBlueSq1 = new Image(getClass().getResourceAsStream("BlueSquare.png"));

        Image TblGreySq1 = new Image(getClass().getResourceAsStream("GreySquare.jpg"));

        for (int i = 1; i<= numOfTables; i++){
            AvailabilityCheckBox = new CheckBox();

        }

    }

    public void ChangeColour(ActionEvent event){
        if(AvailabilityCheckBox.isSelected()){
            System.out.println("ON");
            TableImg.setImage(TblBlueSq1);
        }
        else {
            System.out.println("OFF");
            TableImg.setImage(TblGreySq1);
        }
    }

    @FXML


    public void init(){
        this.tableViewModel = ViewModelFactory.getInstance().getTableViewModel();
        this.viewHandler = viewHandler.getInstance();
    }
}
