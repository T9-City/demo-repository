package org.main.view.table;

import javafx.application.Platform;
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


    @FXML
    private Button backbutton;

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
//        tableMap.forEach((checkBox,imageView)->{
//            int tableNo = extractTableNumberFromCheckBox(checkBox.getId());
//            boolean isAvailable = checkBox.isSelected();
//            updateTableAvailability(tableNo,isAvailable,checkBox,imageView);
//        });
 }


    public void ChangeColour(ActionEvent event) throws SQLException {
        CheckBox checkBox = (CheckBox) event.getSource();
        ImageView imageView = tableMap.get(checkBox);
        if (imageView!= null){
            boolean isAvailable = checkBox.isSelected();
            imageView.setImage(isAvailable? TblBlueSq1:TblGreySq1);
            int tableNo = extractTableNumberFromCheckBox(checkBox.getId());

            if(tableNo>0){
                updateTableAvailability(tableNo, isAvailable, checkBox, imageView);
            } else{
                    System.err.println("invalid Table Number skipping db update");
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
        backbutton.setOnAction(event -> backtoOrder());
        for (int i = 1; i<=15; i++){
            final int tableNum = i;
            try{
            CheckBox checkBox = (CheckBox) getClass().getDeclaredField("AvailabilityCheckBox"+i).get(this);
            ImageView imageView = (ImageView) getClass().getDeclaredField("TableImg"+i).get(this);
            tableMap.put(checkBox,imageView);

            boolean isAvailable = tablesDB.isTableAvailability(i);
            checkBox.setSelected(isAvailable);
            imageView.setImage(isAvailable?TblBlueSq1:TblGreySq1);
            checkBox.setOnAction(event ->{
                boolean newSt = checkBox.isSelected();
                updateTableAvailability(tableNum, newSt,checkBox,imageView);
            });
            }catch (NoSuchFieldException|IllegalAccessException e){
                e.printStackTrace();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }


       this.tableViewModel = ViewModelFactory.getInstance().getTableViewModel();
       this.viewHandler = viewHandler.getInstance();
    }

    private void backtoOrder() {
        ViewHandler.getInstance().openBookingView();
    }

    private void updateTableAvailability(int tableNo, boolean isAvailable, CheckBox checkBox, ImageView imageView){
            new Thread(()->{//does the method on a separate thread so it doesnt wait.
                try{
                tablesDB.setTableAvailability(tableNo,!isAvailable);
                Platform.runLater(()-> {
                    imageView.setImage(isAvailable ? TblBlueSq1 : TblGreySq1);
                   checkBox.setSelected(isAvailable);
                });
            }catch (SQLException e){
                e.printStackTrace();
            }
        }).start();
    }

}
