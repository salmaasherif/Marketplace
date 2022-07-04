package sample.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClientAccount implements Initializable {

    static String View_Acc_Info(String User_ID, PrintWriter out, BufferedReader in) throws IOException {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(5));
        out.flush();

        // sending the User_ID to server
        out.println(User_ID);
        out.flush();

        // displaying server reply to check whether data sent are valid or not
        return in.readLine();
    }

    static String deposit(String userID, String amount, PrintWriter out, BufferedReader in) throws IOException {
        // sending the FUNCTION ID to server
        out.println(String.valueOf(8));
        out.flush();

        // sending the user USERID to server
        out.println(userID);
        out.flush();

        // sending the itemID to server
        out.println(amount);
        out.flush();
        return in.readLine();
        // displaying server reply to check whether data sent are valid or not
    }
    @FXML
    private TableColumn<ModelTable, String> itemColumn;

    @FXML
    private TableColumn<ModelTable, String> priceColumn;

    @FXML
    private TableColumn<ModelTable, String> quantityColumn;

    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TextField amount;

    @FXML
    private Label totbalance;
    private Connection connect;
    private PreparedStatement statement;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try (Socket socket = new Socket("localhost", 1234)) {
            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
            String res = View_Acc_Info(User.username, out, in);
            totbalance.setText("$" + res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void onBackClick(ActionEvent event) throws IOException {
        App.setRoot("Categories");
    }
    public static boolean containsLetters(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        for (int i = 0; i < string.length(); ++i) {
            if (Character.isLetter(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    @FXML
    void onDepositClick(ActionEvent event) throws IOException {
        try (Socket socket = new Socket("localhost", 1234)) {
            // writing to server
            PrintWriter out = new PrintWriter(
                    socket.getOutputStream(), true);
            // reading from server
            BufferedReader in
                    = new BufferedReader(new InputStreamReader(
                            socket.getInputStream()));
            if(containsLetters(amount.getText())){
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("ERROR !");
                    String s = "Please Enter Valid Amount !";
                    alert.setContentText(s);
                    alert.show();
            }
            else if (Integer.parseInt(amount.getText()) >= 0 && Integer.parseInt(amount.getText()) < Integer.MAX_VALUE) {
                String d = deposit(User.username, amount.getText(), out, in);
                if (d.equals("success")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Message");
                    alert.setHeaderText("INFORMATION ALERT !");
                    String s = "Deposited Successfully.";
                    alert.setContentText(s);
                    alert.show();
                    App.setRoot("ClientAccount");
                }
            }
            out.println("exit");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
