package front.controllers.scenes;

import com.jfoenix.controls.JFXTextField;
import front.controllers.kernel.Controller;
import back.implementation.AddressImplementation;
import back.implementation.PeopleImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import back.records.Address;
import back.records.People;

import java.net.URL;
import java.util.ResourceBundle;

import static front.controllers.scenes.LoginScene.SwitchReturn;

public class ChangeYourDataScene extends Controller {
    @FXML
    private JFXTextField mailText;
    @FXML
    private JFXTextField streetText;
    @FXML
    private JFXTextField provinceText;
    @FXML
    private JFXTextField countryText;
    @FXML
    private JFXTextField phoneText;
    @FXML
    private JFXTextField buildingNumberText ;
    @FXML
    private JFXTextField cityText;
    @FXML
    private JFXTextField postalCodeText;

    public static ChangeYourDataScene changeYourDataScene;


    People people = (People) peopleImplementation.selectById(LoginScene.userID);
    Address address = (Address) addressImplementation.selectById(people.getAddress());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        changeYourDataScene = this;

    }

    public void changeButtonHandler(ActionEvent e) {
        people.setMail(mailText.getText());
        people.setPhone(phoneText.getText());
        peopleImplementation.update(people, people.getLogin());

        address.setCity(cityText.getText());
        address.setCountry(countryText.getText());
        address.setProvince(provinceText.getText());
        address.setPostalCode(postalCodeText.getText());
        address.setBuildingNumber(buildingNumberText.getText());
        address.setStreet(streetText.getText());

        addressImplementation.update(address, address.getID());
        SwitchReturn(people);
    }

    public void BackTextHandler(MouseEvent e) {
        People people = (People) peopleImplementation.selectById(LoginScene.userID);
        SwitchReturn(people);
    }

    void FillTextFields(){
        people = (People) peopleImplementation.selectById(LoginScene.userID);
        address = (Address) addressImplementation.selectById(people.getAddress());
        mailText.setText(people.getMail());
        streetText.setText(address.getStreet());
        provinceText.setText(address.getProvince());
        countryText.setText(address.getCountry());
        phoneText.setText(people.getPhone());
        buildingNumberText.setText(address.getBuildingNumber());
        cityText.setText(address.getCity());
        postalCodeText.setText(address.getPostalCode());
    }
}
