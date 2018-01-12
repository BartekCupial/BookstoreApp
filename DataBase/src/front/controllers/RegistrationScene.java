package front.controllers;

import application.Main;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import implementation.AddressImplementation;
import implementation.PeopleImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import records.Address;
import records.People;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationScene implements Initializable {
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

    AddressImplementation addressImplementation = new AddressImplementation();
    PeopleImplementation peopleImplementation = new PeopleImplementation();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void registerButtonHandler(ActionEvent e) {
        //TODO: wywalanie adresu z bazy, jeśli duplicate entry
        int lastID = 0;
        if(!passwordText.getText().equals(password1Text.getText())){
            System.out.println("different passwords");
            return;
        }

        Address address = new Address(streetText.getText(), buildingNumberText.getText(), postalCodeText.getText(),
                cityText.getText(), provinceText.getText(), countryText.getText());
        lastID = addressImplementation.insertint(address);


        People person = new People(loginText.getText(), firstNameText.getText(), lastNameText.getText(),
                lastID, phoneText.getText(), mailText.getText(), passwordText.getText(), "Klient" );

        peopleImplementation.insert(person);

        Main.mainContainer.setScene(Main.LoginSceneID, Main.window);


    }

    public void BackTextHandler(MouseEvent e) {
        Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
    }
}
