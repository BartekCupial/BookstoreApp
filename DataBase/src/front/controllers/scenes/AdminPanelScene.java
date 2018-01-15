package front.controllers.scenes;

import application.Main;
import com.jfoenix.controls.JFXButton;
import front.controllers.kernel.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminPanelScene extends Controller {

    @FXML
    private ComboBox<String> textSQL;
    @FXML
    private TextField selectSQL;
    @FXML
    private TableView tableView;
    @FXML
    private JFXButton executeButton;
    @FXML
    private TextField executeSQL;

    private ArrayList<String> choices = new ArrayList<>();
    PopupScene pop;

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
        //choices.add("Zamowienia");
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
        BuildTableSQL(tableView, SQL);
    }

    public void SelectButtonHandler(ActionEvent e){
        for ( int i = 0; i<tableView.getColumns().size(); i++) {
            tableView.getColumns().clear();
        }
        BuildTableSQL(tableView, selectSQL.getText());
    }

    public void LoadBackupButtonHandler(ActionEvent e) {
        restore();
    }

    public void MakeBackupButtonHandler(ActionEvent e) {
        Backupdbtosql();
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
        ExecuteQuerySQL(executeSQL.getText());
    }

    public void NewWorkerHandler(MouseEvent e) {
        Main.mainContainer.setScene(Main.RegistrationSceneID, Main.window);
    }

    public void NewProviderHandler(MouseEvent event) {
        pop = new PopupScene();
        pop.centerOnScreen();
        pop.show();
    }

    public static void Backupdbtosql() {
        try {

        /*NOTE: Getting path to the Jar file being executed*/
        /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            CodeSource codeSource = AdminPanelScene.class.getProtectionDomain().getCodeSource();
            File jarFile = new File(codeSource.getLocation().toURI().getPath());
            String jarDir = jarFile.getParentFile().getPath();


        /*NOTE: Creating Database Constraints*/
            String dbName = "BookstorCZ";
            String dbUser = "root";
            String dbPass = "root";

        /*NOTE: Creating Path Constraints for folder saving*/
        /*NOTE: Here the backup folder is created for saving inside it*/
            String folderPath = jarDir + "\\backup";

        /*NOTE: Creating Folder if it does not exist*/
            File f1 = new File(folderPath);
            f1.mkdir();

        /*NOTE: Creating Path Constraints for backup saving*/
        /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
            String savePath = "\"" + jarDir + "\\backup\\" + "backup.sql\"";

        /*NOTE: Used to create a cmd command*/
            String executeCmd = "mysqldump --defaults-extra-file=/home/bartek/.sqlpwd BookstorCZ > db_backup.sql";

        /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();

        /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }

        } catch (URISyntaxException | IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
    }
}
