package front.controllers;

import application.Main;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordScene implements Initializable {
    @FXML
    private JFXTextField loginText;
    @FXML
    private JFXTextField mailText;
    @FXML
    private JFXPasswordField passwordText;
    @FXML
    private JFXPasswordField passwordText1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void BackTextHandler(MouseEvent e) {
        Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
    }

    public void ChangePasswordButtonHandler(ActionEvent a) {
        //TODO: zapisa do bazy danych z pól
        loginText.getText();
        mailText.getText();
        passwordText.getText();
        passwordText1.getText();
        Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
    }
}
