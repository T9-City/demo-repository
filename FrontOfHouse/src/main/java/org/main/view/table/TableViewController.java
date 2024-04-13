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

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class TableViewController extends ViewController {


    private TablesDB tablesDB = new TablesDB();
    @FXML
    private Button AssignTable;

    @FXML
    private CheckBox AvailabilityCheckBox1,AvailabilityCheckBox2,AvailabilityCheckBox3,AvailabilityCheckBox4,
            AvailabilityCheckBox5,AvailabilityCheckBox6,AvailabilityCheckBox7,AvailabilityCheckBox8,
            AvailabilityCheckBox9,AvailabilityCheckBox10,AvailabilityCheckBox11,AvailabilityCheckBox12,
            AvailabilityCheckBox13,AvailabilityCheckBox14,AvailabilityCheckBox15;

    @FXML
    private ImageView TableImg1,TableImg2,TableImg3,TableImg4,TableImg5,TableImg6,
            TableImg7,TableImg8,TableImg9,TableImg10,TableImg11,TableImg12,TableImg13,
            TableImg14,TableImg15;

    private ViewHandler viewHandler;
    private TableViewModel tableViewModel;
    private Map<CheckBox, ImageView> tableMap =new HashMap<>();

    Image TblBlueSq1 = new Image(getClass().getResourceAsStream("BlueSquare.png"));
    Image TblGreySq1 = new Image(getClass().getResourceAsStream("GreySquare.jpg"));

 public void handleButtonAction(ActionEvent actionEvent) {
    }


    public void ChangeColour(ActionEvent event) throws SQLException {
        CheckBox checkBox = (CheckBox) event.getSource();
        ImageView imageView = tableMap.get(checkBox);
        if (imageView!= null){

            boolean isAvailable = checkBox.isSelected();
            imageView.setImage(isAvailable?TblBlueSq1:TblGreySq1);

            try{
                int tableNo = extractTableNumberFromCheckBox(checkBox.getId());
                if(tableNo>0){
                    tablesDB.setTableAvailability(tableNo, isAvailable);
                }else{
                    System.err.println("invalid table number skipping db update");
                }

            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    public int extractTableNumberFromCheckBox(String checkBoxId){
        System.out.println("Extracting number from checkBox id: " + checkBoxId);
        if (checkBoxId == null|| checkBoxId.trim().isEmpty()){
            System.err.println("checkbox id is null or empty");
            return -1;
        }
        try {
            String numberPart = checkBoxId.replaceAll("\\D+", "");
            System.out.println("Number Extracted: " + numberPart);
            return Integer.parseInt(numberPart);
        }catch (NumberFormatException e){
            System.err.println("failed to extract number from check box id " + checkBoxId);
            e.printStackTrace();
            return -1;
        }
     }


    public void init(){
       tableMap.put(AvailabilityCheckBox1,TableImg1);
       tableMap.put(AvailabilityCheckBox2,TableImg2);
       tableMap.put(AvailabilityCheckBox3,TableImg3);
       tableMap.put(AvailabilityCheckBox4,TableImg4);
       tableMap.put(AvailabilityCheckBox5,TableImg5);
       tableMap.put(AvailabilityCheckBox6,TableImg6);
       tableMap.put(AvailabilityCheckBox7,TableImg7);
       tableMap.put(AvailabilityCheckBox8,TableImg8);
       tableMap.put(AvailabilityCheckBox9,TableImg9);
       tableMap.put(AvailabilityCheckBox10,TableImg10);
       tableMap.put(AvailabilityCheckBox11,TableImg11);
       tableMap.put(AvailabilityCheckBox12,TableImg12);
       tableMap.put(AvailabilityCheckBox13,TableImg13);
       tableMap.put(AvailabilityCheckBox14,TableImg14);
       tableMap.put(AvailabilityCheckBox15,TableImg15);
//       tableMap.forEach((checkBox, imageView) -> {
//         checkBox.setOnAction(event -> imageView.setImage(checkBox.isSelected()? TblBlueSq1: TblGreySq1));
//       });
       this.tableViewModel = ViewModelFactory.getInstance().getTableViewModel();
       this.viewHandler = viewHandler.getInstance();
    }
}
