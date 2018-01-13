package front.controllers;

import application.Main;
import com.jfoenix.controls.JFXButton;
import com.sun.org.apache.bcel.internal.generic.POP;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminPanelScene implements Initializable {

    @FXML
    private ComboBox<String> textSQL;
    @FXML
    private TextField selectSQL;
    @FXML
    private TableView tableView;
    @FXML
    private JFXButton executeButton;


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
        choices.add("Ludzie");
        choices.add("Zamowienia");
        choices.add("ZamowioneKsiazki");
        for (int i = 0; i < choices.size(); i++) {
            textSQL.getItems().add(choices.get(i));
        }
    }

    public void ViewButtonHandler(ActionEvent e) {
        for ( int i = 0; i<tableView.getColumns().size(); i++) {
            tableView.getColumns().clear();
        }
        String SQL = "SELECT * from " + textSQL.getValue();
        Main.BuildTableSQL(tableView, SQL);
    }

    public void SelectButtonHandler(ActionEvent e){
        for ( int i = 0; i<tableView.getColumns().size(); i++) {
            tableView.getColumns().clear();
        }
        Main.BuildTableSQL(tableView, selectSQL.getText());
    }

    public void LogoutTextHandler(MouseEvent e) {
        Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
    }

    public void LoadBackupButtonHandler(ActionEvent e) {
        restore();
    }

    public void MakeBackupButtonHandler(ActionEvent e) {
        executeBackUp();
    }

    public void DropDataBaseButtonHandler(ActionEvent e){
        dropDataBase();
    }

    private void executeBackUp(){
        try {
            Runtime.getRuntime().exec("mysqldump --defaults-extra-file=/home/bartek/.sqlpwd --routines --triggers BookstorCZ > db_backup.sql");
            System.out.println("backup made");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void restore(){
        try {
            Process runtimeProcess = Runtime.getRuntime().exec("mysql < db_backup.sql");
            int processStatus = runtimeProcess.waitFor();
            if (processStatus == 1) {
                System.out.println("failed");
            } else if (processStatus == 0) {
                System.out.println("success");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void dropDataBase(){
        Connection con = null;
        Statement statement = null;

        try {
            System.out.println("Deleting database...");
            statement = con.createStatement();

            statement.executeUpdate("drop database BookstorCZ");
            System.out.println("Database deleted...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void ChangeDataTextHandler(MouseEvent e) {
        ChangeYourDataScene.changeYourDataScene.FillTextFields();
        Main.mainContainer.setScene(Main.ChangeYourDataSceneID, Main.window);
    }

    public void ExecuteButtonHandler(ActionEvent e ) {
        //TODO:

        executeButton.setOnAction(ex -> {

        });
    }
}
