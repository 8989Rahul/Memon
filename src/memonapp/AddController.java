
package memonapp;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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


public class AddController implements Initializable {



    @FXML
    private JFXButton back;

    @FXML
    private JFXButton resetbutton;

    @FXML
    private JFXTextField idtitle;

    @FXML
    private JFXTextArea iddescription;

    @FXML
    private JFXButton savebutton;

    @FXML
    void clickedOnReset(ActionEvent event) {
        idtitle.setText(null);
        iddescription.setText(null);
    }

    @FXML
    void clickedOnSave(ActionEvent event) throws SQLException, Exception {
        String title = idtitle.getText();
        String desc = iddescription.getText();
        if(!title.equals("")|| !desc.equals("")) {
        Connection c2=DatabaseHandler.getInstance().getConnection();
        PreparedStatement ps2;
        String query = "insert into memories values(?,?,?)";
          ps2 = c2.prepareStatement(query);
          ps2.setString(1,title);
          ps2.setString(2,desc);
          ps2.setString(3, DataProvider.getInstance().getUser());
          int rs=ps2.executeUpdate();
          if(rs==1)
          {
              Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
                stage.setTitle("signup");
                stage.setScene(new Scene(root));
          }else
          {
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Success");
             alert.setContentText("Your ");
             alert.showAndWait();
          }
        }
    }

    @FXML
    void clickedOnback(ActionEvent event) throws IOException {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
                stage.setTitle("view");
                
                stage.setScene(new Scene(root, 1000, 580));

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
