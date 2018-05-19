
package memonapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class FXMLDocumentController implements Initializable {

    private Label label;

    @FXML
    private JFXTextField user;

    @FXML
    private JFXPasswordField pass;

    boolean failed = true;
    @FXML
    private JFXButton logOnSignUp;
    @FXML
    private JFXButton login;
       

    @FXML
    void clickedOnLogin(ActionEvent event) throws Exception {
        
        Connection c2 =DatabaseHandler.getInstance().getConnection();
        PreparedStatement ps3;
        String query3 = "select * from login";
        ps3 = c2.prepareStatement(query3);
        ResultSet rs1 = ps3.executeQuery();
        while (rs1.next()) {
            String u = rs1.getString("username");
            String p = rs1.getString("password");
            if (user.getText().equals(u) && pass.getText().equals(p)) {
                failed = false;
                DataProvider.getInstance().setUser(u);
                ps3.close();

                int count = 0;
                ps3 = c2.prepareStatement("select * from memories where username=?");
                ps3.setString(1, DataProvider.getInstance().getUser());
                ResultSet rs = ps3.executeQuery();
                while (rs.next()) {
                    count++;
                }
                if (count > 0) {
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
                    stage.setTitle("view");
                    stage.setScene(new Scene(root));
                } else {
                    Node source = (Node) event.getSource();
                    Stage stage = (Stage) source.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("AddMemory.fxml"));
                    stage.setTitle("addmemory");
                    stage.setScene(new Scene(root));
                }

                break;
            }
        }
        if (failed==true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Failed");
            alert.setContentText("Invalid User Please Try Again");
            alert.showAndWait();
        }

    }

    @FXML
    void clickedSignUP(ActionEvent event) throws IOException {

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage.setTitle("signup");
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
