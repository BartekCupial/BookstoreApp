package application;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;

import front.MyScene;
import javafx.util.Callback;
import util.DBConnect;

public class Main extends Application {
    //public static Group root = new Group();
    public static Stage window;
    public static MyScene mainContainer;
    public static String LoginSceneID = "login";
    public static String LoginSceneFile = "/front/fxml/LoginScene.fxml";
    public static String AdminSceneID = "admin";
    public static String AdminSceneFile = "/front/fxml/AdminPanelScene.fxml";
    public static String ClientSceneID = "client";
    public static String ClientSceneFile = "/front/fxml/ClientPanelScene.fxml";
    public static String WorkerSceneID = "worker";
    public static String WorkerSceneFile = "/front/fxml/WorkerPanelScene.fxml";
    public static String RegistrationSceneID = "worker";
    public static String RegistrationSceneFile = "/front/fxml/RegistrationScene.fxml";
    public static String ResetPasswordSceneID = "reset";
    public static String ResetPasswordSceneFile = "/front/fxml/ResetPasswordScene.fxml";
    public static String ChangeYourDataSceneID = "change";
    public static String ChangeYourDataSceneFile = "/front/fxml/ChangeYourDataScene.fxml";



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        mainContainer = new MyScene();
        Image icon = new Image(getClass().getResourceAsStream("/assets/User.png"));
        window.getIcons().add(icon);
        window.setResizable(false);
        window.setTitle("BookstorCZ");
        //window.initStyle(StageStyle.UNDECORATED);
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        loadScreens();
        mainContainer.setScene(Main.LoginSceneID, window);
    }

    public static void closeProgram(){
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Confirmation");
//        alert.setHeaderText("Look, a Confirmation Dialog");
//        alert.setContentText("Do you really want to exit?");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK){
//            window.close();
//        }
            window.close();
    }

    private void loadScreens(){
        mainContainer.loadScreen(Main.LoginSceneID, Main.LoginSceneFile);
        mainContainer.loadScreen(Main.WorkerSceneID, Main.WorkerSceneFile);
        mainContainer.loadScreen(Main.ClientSceneID, Main.ClientSceneFile);
        mainContainer.loadScreen(Main.AdminSceneID, Main.AdminSceneFile);
        mainContainer.loadScreen(Main.RegistrationSceneID, Main.RegistrationSceneFile);
        mainContainer.loadScreen(Main.ResetPasswordSceneID, Main.ResetPasswordSceneFile);
        mainContainer.loadScreen(Main.ChangeYourDataSceneID, Main.ChangeYourDataSceneFile);
    }


    public static void BuildTableSQL(TableView tableview, String SQL){
        Connection c ;
        ObservableList<ObservableList> data;

        data = FXCollections.observableArrayList();
        try{
            c = DBConnect.getConnection();
            ResultSet rs = c.createStatement().executeQuery(SQL);
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);

            }

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    row.add(rs.getString(i));
                }
                data.add(row);

            }
            tableview.setItems(data);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
}