package front.controllers.scenes;

import application.Main;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import front.controllers.kernel.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import back.records.People;

import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordScene extends Controller {
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
