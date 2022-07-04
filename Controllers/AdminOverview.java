package sample.market;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class AdminOverview implements Initializable{

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

    String SQL = "SELECT * from doctor";
    ObservableList<ModelTable> ob = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        try {
//            ConnectDB db = new ConnectDB();
//            connect = db.connectDB();
//            ResultSet rs = connect.createStatement().executeQuery(SQL);
//            while (rs.next()) {
//                ob.add(new ModelTable(rs.getString("item"), 
//                        rs.getString("quantity"),
//                        rs.getString("price")));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        itemColumn.setCellValueFactory(new PropertyValueFactory<>("item"));
//        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
//        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
//        table.getItems().addAll(ob);
    }
    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

}
