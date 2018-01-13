package front.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class PopupScene extends Application {
    Group root = new Group();
    public static PopupScene popupScene;
    @Override
    public void start(Stage primaryStage) throws Exception {
        popupScene = this;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Class.class
                .getResource("/front/fxml/PopupScene.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Insert");
        primaryStage.initStyle(StageStyle.UNDECORATED);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
