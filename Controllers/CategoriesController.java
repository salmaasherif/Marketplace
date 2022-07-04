package sample.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class CategoriesController implements Initializable{
    
static String View_Acc_Info(String User_ID,PrintWriter out, BufferedReader in) throws IOException
{  
    // sending the FUNCTION ID to server
    out.println(String.valueOf(5));
    out.flush();
    
    // sending the itemID to server
    out.println(User_ID);
    out.flush();

   // displaying server reply to check whether data sent are valid or not
    return in.readLine();
}

    @FXML
    private Button back;
    
    @FXML
    private Button clothing;

    @FXML
    private Button electronics;

    @FXML
    private Button food;

    @FXML
    private Button happ;

    @FXML
    private ImageView i1;

    @FXML
    private ImageView i2;
    
    @FXML
    private ImageView i3;

    @FXML
    private ImageView i4;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        i1.setImage(new Image("fruits.jpg"));
        i2.setImage(new Image("shirts.jpg"));
        i3.setImage(new Image("elec.png"));
        i4.setImage(new Image("ha.jpg"));
    }
    @FXML
    void onAccountClick(ActionEvent event) throws IOException {
        App.setRoot("ClientAccount");
        //View_Acc_Info()
        
    }
    @FXML
    void onSerachClick(ActionEvent event) throws IOException {
        App.setRoot("SearchItems");
    }   
    @FXML 
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("primary");

    }
    @FXML
    void onClothingClick(ActionEvent event) throws IOException {
        App.setRoot("NewClothing");
    }

    @FXML
    void onElectronicsClick(ActionEvent event) throws IOException {
        App.setRoot("NewElectronics");

    }

    @FXML
    void onFoodClick(ActionEvent event) throws IOException {
        //App.setRoot("food");
            App.setRoot("NewFood");
}

    @FXML
    void onHAClick(ActionEvent event) throws IOException {
        App.setRoot("NewHomeApp");

    }
    
    @FXML
    void onCartClick(ActionEvent event) throws IOException {
        App.setRoot("cart");  
    }
    

}
