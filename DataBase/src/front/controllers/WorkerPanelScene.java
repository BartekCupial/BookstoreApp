package front.controllers;

import application.Main;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkerPanelScene implements Initializable {

    @FXML
    private ComboBox<String> textSQL;
    @FXML
    private TextField selectSQL;
    @FXML
    private TableView tableView;

    private ArrayList<String> choices = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choices.add("Adresy");
        choices.add("Autorzy");
        choices.add("Dzialy");
        choices.add("Dotowarowanie");
        choices.add("Dostawcy");
        choices.add("Dostawy");
        choices.add("Ksiazki");
        choices.add("ZamowioneKsiazki");
        for (int i = 0; i < choices.size(); i++) {
            textSQL.getItems().add(choices.get(i));
        }
    }

    public void LogoutTextHandler(MouseEvent e) {
        LoginScene.userID="not";
        Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
    }


    public void ChangeDataTextHandler(MouseEvent e) {
        ChangeYourDataScene.changeYourDataScene.FillTextFields();
        Main.mainContainer.setScene(Main.ChangeYourDataSceneID, Main.window);
    }

    public void ViewButtonHandler(ActionEvent e) {
        for ( int i = 0; i<tableView.getColumns().size(); i++) {
            tableView.getColumns().clear();
        }
        String SQL = "SELECT * from " + textSQL.getValue();
        Main.BuildTableSQL(tableView, SQL);
    }

    public void SelectButtonHandler(ActionEvent e) {
        for ( int i = 0; i<tableView.getColumns().size(); i++) {
            tableView.getColumns().clear();
        }
        Main.BuildTableSQL(tableView, selectSQL.getText());
    }

    public void ExecuteButtonHandler(ActionEvent e) {
        //TODO:

    }
}
