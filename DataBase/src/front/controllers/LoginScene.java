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

import static application.Main.closeProgram;

public class LoginScene implements Initializable {
    @FXML
    private JFXTextField loginText;
    @FXML
    private JFXPasswordField passwordText;

    PeopleImplementation peopleImplementation = new PeopleImplementation();
    People p;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        madeAdmin();
    }

    public void NextButtonHandler(){
        loginText.getText();
        passwordText.getText();

        p = (People) peopleImplementation.selectById(loginText.getText());
        if(p==null){
            System.out.println("wrong password or login");
            return;
        }
        if(!p.getPassword().equals(passwordText.getText())){
            System.out.println("wrong password or login");
            return;
        }
        switch (p.getPosition()) {
            case "Admin":
                Main.mainContainer.setScene(Main.AdminSceneID, Main.window);
                break;
            case "Pracownik":
                Main.mainContainer.setScene(Main.WorkerSceneID, Main.window);
                break;
            case "Klient":
                Main.mainContainer.setScene(Main.ClientSceneID, Main.window);
                break;
        }
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

    private void madeAdmin(){
        People admin = (People) peopleImplementation.selectById("Admin");
        if(admin==null){
            admin.setLogin("Admin");
            admin.setPassword("Admin");
            admin.setMail("admin@gmail.com");
            admin.setPosition("Admin");
            admin.setPhone("666");
            admin.setFirstName("Bartek");
            admin.setLastName("Admin");
            admin.setAddress(0);
            peopleImplementation.insert(admin);
        }

    }
}
