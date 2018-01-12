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

import static application.Main.closeProgram;

public class LoginScene implements Initializable {
    @FXML
    private JFXTextField loginText;
    @FXML
    private JFXPasswordField passwordText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void NextButtonHandler(){
        loginText.getText();
        passwordText.getText();
    }

    public void registrationHandler(MouseEvent e) {
        Main.mainContainer.setScene(Main.RegistrationSceneID, Main.window);
    }

    public void passwordHandler(MouseEvent e) {
        Main.mainContainer.setScene(Main.ResetPasswordSceneID, Main.window);
    }

    public void exitButtonHandler(ActionEvent e) {
        closeProgram();
    }
}
