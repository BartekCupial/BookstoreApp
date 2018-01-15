package front.controllers.scenes;

import application.Main;
import front.controllers.kernel.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkerPanelScene extends Controller {

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

    public void ChangeDataTextHandler(MouseEvent e) {
        ChangeYourDataScene.changeYourDataScene.FillTextFields();
        Main.mainContainer.setScene(Main.ChangeYourDataSceneID, Main.window);
    }

    public void ViewButtonHandler(ActionEvent e) {
        for ( int i = 0; i<tableView.getColumns().size(); i++) {
            tableView.getColumns().clear();
        }
        String SQL = "SELECT * from " + textSQL.getValue();
        BuildTableSQL(tableView, SQL);
    }

    public void SelectButtonHandler(ActionEvent e) {
        for ( int i = 0; i<tableView.getColumns().size(); i++) {
            tableView.getColumns().clear();
        }
        BuildTableSQL(tableView, selectSQL.getText());
    }

    public void ExecuteButtonHandler(ActionEvent e) {
        //TODO:

    }
}
