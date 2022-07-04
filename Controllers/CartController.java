package sample.market;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CartController implements Initializable {

    @FXML
    private TableColumn<ModelTable, String> itemColumn;

    @FXML
    private TableColumn<ModelTable, String> priceColumn;

    @FXML
    private TableColumn<ModelTable, String> quantityColumn;

    @FXML
    private TableView<ModelTable> table;

    ObservableList<ModelTable> ob = FXCollections.observableArrayList();


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ItemsArrayList v=new ItemsArrayList();
        for (int i = 0; i < ItemsArrayList.q.size(); i++) {
            ob.add(new ModelTable(
                    ItemsArrayList.n.get(i),
                    ItemsArrayList.q.get(i),
                    ItemsArrayList.p.get(i)));
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
    void onChangeClick(ActionEvent event) {

    }

    @FXML
    void onRemoveClick(ActionEvent event) {

    }

    @FXML
    void onCheckoutClick(ActionEvent event) {
        for (int x = 0; x < ItemsArrayList.q.size(); x++) {
            System.out.println(ItemsArrayList.n.get(x));
            System.out.println(ItemsArrayList.q.get(x));
        }
    }
}
