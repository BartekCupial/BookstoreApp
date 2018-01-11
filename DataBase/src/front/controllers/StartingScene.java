package front.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

import static application.Main.closeProgram;


public class StartingScene implements Initializable {

    public Button ExitGameButton = new Button();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //System.out.println("View is now loaded!");
    }

    @FXML
    public void StartGameButtonHandler(){
        //Main.mainContainer.setScene(Main.ChoosingSceneID, Main.window);
    }

    @FXML
    public void ExitGameButtonHandler(){
        closeProgram();
    }
}