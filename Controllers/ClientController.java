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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ClientController implements Initializable {

    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;

    static String send_ID_Password_Login(String userID, String password, PrintWriter out, BufferedReader in) throws IOException {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(0));
        out.flush();

        // sending the user USERID to server
        out.println(userID);
        out.flush();
        // sending the user password to server
        out.println(password);
        out.flush();
        // displaying server reply to check whether data sent are correct or not
        return in.readLine();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // establish a connection by providing host and port
        // number

    }

    @FXML
    private TextField email;

    @FXML
    private PasswordField pass;

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void onLoginClick(ActionEvent event) {
        try (Socket socket = new Socket("localhost", 1234)) {

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
            String status = send_ID_Password_Login(email.getText(), pass.getText(), out, in);
            System.out.println(status);
            if (status.equals("okay")) {
                User.setUsername(email.getText());
                User.setPass(pass.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("INFORMATION ALERT !");
                String s = "LOGGED IN Successfully.";
                alert.setContentText(s);
                alert.show();
                if((email.getText()).equals("administrator")){ App.setRoot("AdminOverview");}
                else App.setRoot("Categories");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Message !");
                alert.setHeaderText("ERROR !");
                String s = "FAILED. Try Again !";
                alert.setContentText(s);
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
