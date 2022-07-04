/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.market;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class NewElectronicsController implements Initializable {

    @FXML
    private Button add1Button;

    @FXML
    private Button add2Button;

    @FXML
    private ImageView i1;

    @FXML
    private ImageView i2;

    @FXML
    private TextField q1;

    @FXML
    private TextField q2;
    @FXML
    private ImageView cimg;
        @FXML
    private GridPane productGridPane;
    private TextArea resultArea;
    private Connection connect;
    private PreparedStatement statement;
    ObservableList<ModelTable> ob = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String sql = "select iname,price,qty,pic from item where category=\"Electronics\"";
        try {
            ConnectDB db = new ConnectDB();
            connect = db.connectDB();
            ResultSet rs = connect.createStatement().executeQuery(sql);
            int i = 0;
            int j = 0;
            while (rs.next()) {
                if(j==2){j=0;}
                VBox layout = new VBox();
                File imageFile = new File(rs.getString("pic"));
                ImageView imgv = new ImageView();
                imgv.setImage(new Image(imageFile.toURI().toString()));
                imgv.setFitWidth(210);
                imgv.setFitHeight(210);
                Label productName = new Label(rs.getString("iname"));
                Label price = new Label(rs.getString("price") + "  Quantity: "+rs.getString("qty"));
                layout.getChildren().addAll(imgv, productName, price);
                productGridPane.add(layout, i++, 0);
                productGridPane.setHgap(25); //horizontal gap in pixels => that's what you are asking for
                productGridPane.setVgap(25); //vertical gap in pixels
                productGridPane.setPadding(new Insets(10, 10, 10, 10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("Categories");
    }

    @FXML
    void onCartClick(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }

    @FXML
    void onAddLaptopClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("7");
        ItemsArrayList.q.add(q1.getText());
        ItemsArrayList.p.add("$1500");
        ItemsArrayList.n.add("Laptop");

    }

    @FXML
    void onAddMobileClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("8");
        ItemsArrayList.q.add(q2.getText());
        ItemsArrayList.p.add("$600");
        ItemsArrayList.n.add("Mobile");

    }
}
