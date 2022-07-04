/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.market;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HomeController implements Initializable {
    @FXML
    private TextField q1;

    @FXML
    private TextField q2;

    @FXML
    private TextField q3;

    @FXML
    private TextField q4;
    @FXML
    private GridPane productGridPane;
    private TextArea resultArea;
    private Connection connect;
    private PreparedStatement statement;
    ObservableList<ModelTable> ob = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String sql = "select iname,price,qty,pic from item where category=\"Food\"";
        try {
            ConnectDB db = new ConnectDB();
            connect = db.connectDB();
            ResultSet rs = connect.createStatement().executeQuery(sql);
            int i = 0;
            int j = 0;
            while (rs.next()) {
                if(j==2){j=0; i++;}
                if(i==3){i=0;}
                VBox layout = new VBox();
                File imageFile = new File(rs.getString("pic"));
                ImageView imgv = new ImageView();
                imgv.setImage(new Image(imageFile.toURI().toString()));
                imgv.setFitWidth(150);
                imgv.setFitHeight(150);
                Label productName = new Label(rs.getString("iname"));
                Label price = new Label(rs.getString("price") + "  Quantity: "+rs.getString("qty"));
                //Label qty = new Label("Quantity: "+rs.getString("qty"));
                //Button addButton = new Button("Add to Cart");
                //addButton.setMaxWidth(150);
//                TextField tf = new TextField();
//                tf.setMaxWidth(150);
//                tf.setPromptText("Quantity");
                //tf.setFocusTraversable(false);
                layout.getChildren().addAll(imgv, productName, price);
                productGridPane.add(layout, i, j++);
//                productGridPane.setAlignment(Pos.CENTER_RIGHT);
                productGridPane.setHgap(25); //horizontal gap in pixels => that's what you are asking for
                productGridPane.setVgap(25); //vertical gap in pixels
                productGridPane.setPadding(new Insets(10, 10, 10, 10));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
            @FXML
    void onAddTomClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("1");
        ItemsArrayList.q.add(q1.getText());
        ItemsArrayList.p.add("$2.99");
        ItemsArrayList.n.add("Tomato");

    }

    @FXML
    void onAddBanClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("2");
        ItemsArrayList.q.add(q2.getText());
        ItemsArrayList.p.add("$4.99");
        ItemsArrayList.n.add("Banana");

    }

    @FXML
    void onAddCheeseClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("3");
        ItemsArrayList.q.add(q3.getText());
        ItemsArrayList.p.add("$6.99");
        ItemsArrayList.n.add("Cheese");

    }

    @FXML
    void onAddPizzaClick(ActionEvent event) throws IOException {
        ItemsArrayList.i.add("4");
        ItemsArrayList.q.add(q4.getText());
        ItemsArrayList.p.add("$15.99");
        ItemsArrayList.n.add("Pizza");
    }
    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("Categories");
    }
    @FXML
    void onCartClick(ActionEvent event) throws IOException {
        App.setRoot("cart");
    }
}

