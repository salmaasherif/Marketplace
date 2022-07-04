package sample.market;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button adminLogin;

    @FXML
    private Button clientLogin;

    @FXML
    private Button exit;

    @FXML
    private Button signUpButton;

    @FXML
    void onAdminLoginClick(ActionEvent event) throws IOException {
        App.setRoot("khara");
    }

    @FXML
    void onClientLoginClick(ActionEvent event) throws IOException {
        App.setRoot("ClientController");
    }

    @FXML
    void onExitButtonClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onSignUpClick(ActionEvent event) throws IOException {
        App.setRoot("ClientSignUpController");
    }

}
