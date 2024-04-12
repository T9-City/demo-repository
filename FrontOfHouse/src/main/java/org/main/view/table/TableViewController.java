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

import java.util.HashMap;
import java.util.Map;


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

    private Map<CheckBox, ImageView> tableMap =new HashMap<>();

    
    public void handleButtonAction(ActionEvent actionEvent) {
    }
    @FXML
    public void initialise(){
        this.tableViewModel = new TableViewModel();

        Image TblBlueSq1 = new Image(getClass().getResourceAsStream("BlueSquare.png"));

        Image TblGreySq1 = new Image(getClass().getResourceAsStream("GreySquare.jpg"));

        for (int i = 1; i<= numOfTables; i++){
            CheckBox AvailabilityCheckBox = new CheckBox();
            ImageView TableImg = new ImageView(TblBlueSq1);
            tableMap.put(AvailabilityCheckBox,TableImg);

            AvailabilityCheckBox.setId("table" + i + "AvailabilityBox");
            TableImg.setId("table" + i + "TableImg");

            AvailabilityCheckBox.setOnAction(this::ChangeColour);


        }

    }

    public void ChangeColour(ActionEvent event){
        CheckBox AvailabilityCheckBox = (CheckBox) event.getSource();
        ImageView TableImg = tableMap.get(AvailabilityCheckBox);
        if(TableImg !=null){
            System.out.println("ON");
            TableImg.setImage(AvailabilityCheckBox.isSelected() ? T);
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
