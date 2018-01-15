package front.controllers.scenes;

import application.Main;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import front.controllers.kernel.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import back.records.Address;
import back.records.People;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationScene extends Controller {
    @FXML
    private JFXTextField loginText;
    @FXML
    private JFXTextField mailText;
    @FXML
    private JFXTextField firstNameText;
    @FXML
    private JFXTextField lastNameText;
    @FXML
    private JFXTextField phoneText;
    @FXML
    private JFXTextField streetText;
    @FXML
    private JFXTextField buildingNumberText;
    @FXML
    private JFXTextField cityText;
    @FXML
    private JFXTextField provinceText;
    @FXML
    private JFXTextField countryText;
    @FXML
    private JFXTextField postalCodeText;
    @FXML
    private JFXPasswordField passwordText;
    @FXML
    private JFXPasswordField password1Text;

    private String status="";
    private People person;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void registerButtonHandler(ActionEvent e) {

        person = (People) peopleImplementation.selectById(LoginScene.userID);
        if(person.getPosition().equals("Admin")){
            status = "Pracownik";
        }else{
            status = "Klient";
        }

        int lastID;
        if(!passwordText.getText().equals(password1Text.getText())){
            System.out.println("different passwords");
            return;
        }
        person = (People) peopleImplementation.selectById(loginText.getText());
        if(person.getLogin().equals("")){
            System.out.println("login already taken");
            return;
        }
        Address address = new Address(streetText.getText(), buildingNumberText.getText(), postalCodeText.getText(),
                cityText.getText(), provinceText.getText(), countryText.getText());
        lastID = addressImplementation.insertint(address);

        person = new People(loginText.getText(), firstNameText.getText(), lastNameText.getText(),
                lastID, phoneText.getText(), mailText.getText(), passwordText.getText(), status );
        peopleImplementation.insert(person);

        SetComponentsToNull();
        if(status.equals("Klient")){
            Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
        }else{
            Main.mainContainer.setScene(Main.AdminSceneID, Main.window);
        }

    }

    public void BackTextHandler(MouseEvent e) {
        person = (People)peopleImplementation.selectById(LoginScene.userID);

        if(LoginScene.userID.equals("not")){
            Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
        }else{
            Main.mainContainer.setScene(Main.AdminSceneID, Main.window);
        }

    }

    @Override
    public void SetComponentsToNull(){
        loginText.setText("");
        mailText.setText("");
        firstNameText.setText("");
        lastNameText.setText("");
        phoneText.setText("");
        streetText.setText("");
        buildingNumberText.setText("");
        cityText.setText("");
        provinceText.setText("");
        countryText.setText("");
        postalCodeText.setText("");
        password1Text.setText("");
        passwordText.setText("");
    }
}
