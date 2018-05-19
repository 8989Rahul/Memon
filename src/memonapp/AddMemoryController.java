
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


public class AddMemoryController implements Initializable {

    @FXML
    private JFXButton AddButton;

    @FXML
    private JFXButton Add;

    @FXML
    void clickedOnAdd(ActionEvent event) throws IOException {
                        Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
                stage.setTitle("Add");
                stage.setScene(new Scene(root, 1000, 580));

    }

    @FXML
    void clickedOnAddButton(ActionEvent event) throws IOException {
                        Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
                stage.setTitle("Add");
                stage.setScene(new Scene(root, 1000, 580));

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
