/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memonapp;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class DeleteController implements Initializable {

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton delete;

    @FXML
    void clickedOnBack(ActionEvent event) throws IOException {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("AddMemory.fxml"));
                stage.setTitle("login");
                stage.setScene(new Scene(root, 1000, 580));

    }

    @FXML
    void clickedOnDelete(ActionEvent event) throws IOException {


    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
