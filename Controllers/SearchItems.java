/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SearchItems implements Initializable {

    static String search(String itemName, PrintWriter out, BufferedReader in) throws IOException {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(7));
        out.flush();

        // sending the itemID to server
        out.println(itemName);
        out.flush();
        // displaying server reply to check whether data sent are valid or not
        return in.readLine();
    }
    
    @FXML
    private TextField iname;

    @FXML
    private TextField qty;
    @FXML
    private TableColumn<ModelTable, String> itemColumn;

    @FXML
    private TableColumn<ModelTable, String> priceColumn;

    @FXML
    private TableColumn<ModelTable, String> quantityColumn;

    @FXML
    private TableView<ModelTable> table;

    private Connection connect;
    private PreparedStatement statement;

    //ObservableList<ModelTable> ob = FXCollections.observableArrayList();
    ObservableList<ModelTable> ob = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {


        try {
            ConnectDB db = new ConnectDB();
            connect = db.connectDB();
            PreparedStatement ps = connect.prepareStatement("SELECT iname,qty,price from `item`");
            //String SQL = "SELECT iname,qty,price from `item` where iname=?";
            //statement.setString(1, this.iname.getText());            
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                ob.add(new ModelTable(rs.getString("iname"), 
                        rs.getString("qty"),
                        rs.getString("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.getItems().addAll(ob);
    }

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("Categories");
    }
        @FXML
    void onSearchCompletedClick(ActionEvent event) throws IOException {
        try (Socket socket = new Socket("localhost", 1234)) {
            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));    
        String res = search(iname.getText(),out,in);
        }
    }
    @FXML
    void onAddClick(ActionEvent event) throws IOException {
        //If Available in Database,, Added Successfully Message Box

    }

}
