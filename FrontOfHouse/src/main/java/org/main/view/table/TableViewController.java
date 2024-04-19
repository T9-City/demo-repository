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

/**
 * @author amirj
 * @version 1.9
 * @since 1.7
 */
public class TableViewController extends ViewController {

/**
 * represents the back Button displayed on the Table plan GUI
 **/
    @FXML
    private Button backbutton;
/**
 * tablesDB uses the TablesDB class to make use of the SQL queries*/
    private TablesDB tablesDB = new TablesDB();
/**
 * The CheckBox declaration here is used to
 * reference all the checkboxes created in the fxml file*/
    @FXML
    private CheckBox AvailabilityCheckBox1,AvailabilityCheckBox2,AvailabilityCheckBox3,AvailabilityCheckBox4,
            AvailabilityCheckBox5,AvailabilityCheckBox6,AvailabilityCheckBox7,AvailabilityCheckBox8,
            AvailabilityCheckBox9,AvailabilityCheckBox10,AvailabilityCheckBox11,AvailabilityCheckBox12,
            AvailabilityCheckBox13,AvailabilityCheckBox14,AvailabilityCheckBox15;
/**
 * the ImageView declaration is used to
 * reference all the table images in the fxml file*/
    @FXML
    private ImageView TableImg1,TableImg2,TableImg3,TableImg4,TableImg5,TableImg6,
            TableImg7,TableImg8,TableImg9,TableImg10,TableImg11,TableImg12,TableImg13,
            TableImg14,TableImg15;
/**
 * viewHandler is used as a variable which starts the GUI
 * it acts as a controller that can switch between different views.
 * in this case it is used for the Tables View window.
 */
    private ViewHandler viewHandler;
    /**
     * this variable implements the class of the tableviewmodel*/
    private TableViewModel tableViewModel;
    /**
     * This Hash map is used to map the CheckBoxes to the corresponding ImageView,
     * which is made in the fxml file.
     * Each CheckBox indicates if a table is available or not.
     * this will also allow the synchronisation between the tables checkboxes' state
     * and the image changes*/
    private Map<CheckBox, ImageView> tableMap =new HashMap<>();
/**
 * TblBlueSq1 represents the blue image to indicate that
 * the table is available*/
    Image TblBlueSq1 = new Image(getClass().getResourceAsStream("BlueSquare.png"));
   /**
    * TblGreySq1 represents the grey image to indicate that the table is unavailable
    * this can be due to it being booked, or it is reserved for walk-in.*/
    Image TblGreySq1 = new Image(getClass().getResourceAsStream("GreySquare.jpg"));


/**
 * ChangeColour method, as it says,
 * changes the colour of the table visually
 * When the tables availability checkbox is clicked this will change the image of the table.
 * If it was available for example, it would be blue but when clicked would change to grey.
 * Same works vice versa.
 * Also updates the Database with the current availability of the tables
 *
 * @param event the action event from the checkbox triggers this method
 * @throws SQLException if there is an error when updating the database.*/
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

/**
 * this method extracts the table number via the given checkBoxId
 * this assumes the checkbox id has a numeric value indicating the table number
 * if there is no ID found or its null, the method returns -1 as a test
 *
 * @param checkBoxId this is the ID of the checkboxes,
 *                   this holds the numeric value for the table numebers
 * @return only if the extracted table number is -1, if the ID is invalid or not found
 */
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

/**
 *This is the initialisation method where it initialises the UI by creating the link
 * between the checkboxes and the correlating table image
 * Another thing it does is that it sets the checkboxes to show the current availability of each table
 * from the Database.
 * Another thing it does is that it assigns an action to each checkbox to update the table's availability
 * via user interaction.
 * Also it iterates through a fixed number of tables (15) as shown via the for loop.*/
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
/**
 * this method uses the back button to return to the booking UI of the application*/
    private void backtoOrder() {
        ViewHandler.getInstance().openBookingView();
    }
/**
 * As it says, it updates the table availability status via the GUI and the Databaase.
 * it runs the Database update operation on a separate thread to prevent the GUI from having conflicts/freezing
 * After update the DB(database), it sets the correlating image and checkbox state on the Javafx application thread.
 *
 * @param tableNo the table number which will update.
 * @param isAvailable the new availability status of the table.
 * @param checkBox the checkbox linked to the respective table.
 * @param imageView the image view which shows the table's status.*/
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
