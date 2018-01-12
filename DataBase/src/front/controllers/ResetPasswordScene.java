package front.controllers;

import application.Main;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import implementation.PeopleImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import records.People;

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

    PeopleImplementation peopleImplementation = new PeopleImplementation();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void BackTextHandler(MouseEvent e) {
        Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
    }

    public void ChangePasswordButtonHandler(ActionEvent a) {
        if(!passwordText.getText().equals(passwordText1.getText())){
            System.out.println("different passwords");
            return;
        }

        passwordText.getText();
        People people = (People) peopleImplementation.selectById(loginText.getText());

        if(!people.getMail().equals(mailText.getText()) || !people.getLogin().equals(loginText.getText())){
            System.out.println("wrong login and/or mail");
            return;
        }
        people.setPassword(passwordText.getText());
        peopleImplementation.update(people,loginText.getText());

        Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
    }
}
