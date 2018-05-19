/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memonapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

    DatabaseHandler handler;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton signup;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField username;

    @FXML
    void clickedOnLogin(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setTitle("login");

        stage.setScene(new Scene(root, 1000, 580));

    }

    @FXML
    void clickedOnSignUp(ActionEvent event) {
        String Username = username.getText();
        String Password = password.getText();
        int rs3 = 0;
        Parent root = null;

        try {
            PreparedStatement ps3 = handler.getConnection().prepareStatement("insert into login values(?,?)");
            ps3.setString(1, Username);
            ps3.setString(2, Password);
            rs3 = ps3.executeUpdate();
        } catch (Exception e) {
        }

        if (rs3 > 0) {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            } catch (IOException ex) {
            }
            stage.setScene(new Scene(root));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            handler = DatabaseHandler.getInstance();
        } catch (Exception ex) {
        }
    }

}
