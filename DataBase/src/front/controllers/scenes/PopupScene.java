package front.controllers.scenes;

import application.Main;
import javafx.event.ActionEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopupScene extends Stage {
    public PopupScene() {
        this.setResizable(true);
        this.setTitle("Insert");
        //this.initStyle(StageStyle.UNDECORATED);
        this.setAlwaysOnTop(true);
        this.sizeToScene();
        this.setOpacity(0);
        this.initModality(Modality.APPLICATION_MODAL);
        Main.mainContainer.setScene(Main.PopupSceneID, this);
    }

    public void ExecuteButtonHandler(ActionEvent e) {

    }
}
