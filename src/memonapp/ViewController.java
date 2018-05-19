
package memonapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ViewController implements Initializable {

    @FXML
    private JFXListView<String> listview;
    String item = "first";
    @FXML
    private JFXButton add;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection c2 = DatabaseHandler.getInstance().getConnection();
            PreparedStatement ps = c2.prepareStatement("select title from memories where username=?");
            ps.setString(1, DataProvider.getInstance().getUser());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                listview.getItems().add(rs.getString("title"));
                listview.setStyle("-fx-font-size: 1.8em ;");
                listview.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2) {
                    ObservableList array = listview.getSelectionModel().getSelectedItems();

                    //System.out.println(String.valueOf(array.get(0)));
//                    if((array.get(0)).equals(null)){
//                        System.out.println(String.valueOf(array.get(0))+" if");
                    try {
                        DataProvider.getInstance().setListTitle(String.valueOf(array.get(0)));
                        Node source = (Node) event.getSource();
                        Stage stage = (Stage) source.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getResource("ViewMemory.fxml"));
                        stage.setTitle("login");
                        stage.setScene(new Scene(root, 1000, 580));
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
//                    } else {
//                    System.out.println(String.valueOf(array.get(0))+" else");
//                    }
                    }

                });

            }
            

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    @FXML
    private void clickedOnAdd(ActionEvent event) throws IOException {
        
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Add.fxml"));
                stage.setTitle("Add");
                
                stage.setScene(new Scene(root, 1000, 580));        
    }

   

    }


