package sample.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static sample.market.ClientController.send_ID_Password_Login;

public class ClientSignUpController {

    static String send_ID_Password_CreateAccount(String userID, String password,
            String ConfirmPassword, String fname, String lname,
            PrintWriter out, BufferedReader in) throws IOException {
        if (password.equals(ConfirmPassword)) {
            // sending the FUNCTION ID to server
            out.println(String.valueOf(1));
            out.flush();

            // sending the user data to server
            out.println(userID);
            out.flush();
            out.println(password);
            out.flush();
            out.println(fname);
            out.flush();
            out.println(lname);
            out.flush();
            return in.readLine();
        } else {
            // displaying server reply to check whether data sent are correct or not
            return "not same";
        }
    }
    @FXML
    private TextField address;

    @FXML
    private Button backButton;

    @FXML
    private TextField email;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private PasswordField pass1;

    @FXML
    private PasswordField pass2;

    @FXML
    private TextField phone;

    @FXML
    private Button signupButton;

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    void onSignUpClick(ActionEvent event) {
        try (Socket socket = new Socket("localhost", 1234)) {

            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);

            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
            String status = send_ID_Password_CreateAccount(email.getText(), pass1.getText(),
                    pass2.getText(), fname.getText(), lname.getText(), out, in);
            if (status.equals("okay")) {
                User.setUsername(email.getText());
                User.setPass(pass1.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText("INFORMATION ALERT !");
                String s = "Registered Successfully.";
                alert.setContentText(s);
                alert.show();
                App.setRoot("Categories");
            }
            else if(status.equals("not okay")){
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
