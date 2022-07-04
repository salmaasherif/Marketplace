package sample.market;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminController {

    @FXML
    private TextField email;

    @FXML
    private PasswordField pass;

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        App.setRoot("AdminOverview");
    }

}
