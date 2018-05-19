
package memonapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
import javafx.stage.Stage;


public class ViewMemoryController implements Initializable {

    @FXML
    private JFXButton back;

    @FXML
    private JFXButton updatebutton;

    @FXML
    private JFXTextField id_title;

    @FXML
    private JFXTextArea id_description;

    @FXML
    private JFXButton deletebutton;

    @FXML
    void clickedOnDelete(ActionEvent event) throws IOException {
        try {
            Connection c2 = DatabaseHandler.getInstance().getConnection();
            PreparedStatement ps = c2.prepareStatement("delete from memories where title=? and username=?");
            ps.setString(1, id_title.getText());
            ps.setString(2, DataProvider.getInstance().getUser());
            int res = ps.executeUpdate();
            if (res > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText(id_title.getText() + " is Deleted !");
                alert.showAndWait();
                 Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        stage.setTitle("Memory");
        stage.setScene(new Scene(root));
            }
        } catch (Exception e) {
        }

       
    }

    @FXML
    void clickedOnUpdate(ActionEvent event) {

        try {
            Connection c2 = DatabaseHandler.getInstance().getConnection();
            PreparedStatement ps = c2.prepareStatement("update memories set title=? , memory=? where title=? and username=?");
            ps.setString(1, id_title.getText());
            ps.setString(2, id_description.getText());
            ps.setString(3, DataProvider.getInstance().getListTitle());
            ps.setString(4, DataProvider.getInstance().getUser());
            int res = ps.executeUpdate();
            if (res > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText(id_title.getText() + " updated successfully !");
                alert.showAndWait();
            }
        } catch (Exception e) {
        }
    }

    @FXML
    void clickedOnback(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        stage.setTitle("view");
        stage.setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_title.requestFocus();
        try {
            id_title.setText(DataProvider.getInstance().getListTitle());
            Connection c2 = DatabaseHandler.getInstance().getConnection();
            PreparedStatement ps = c2.prepareStatement("select memory from memories where title=?");
            ps.setString(1, DataProvider.getInstance().getListTitle());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_description.setText(rs.getString("memory"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
