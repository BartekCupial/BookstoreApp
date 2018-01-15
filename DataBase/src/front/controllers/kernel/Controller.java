package front.controllers.kernel;

import application.Main;
import back.implementation.*;
import front.controllers.scenes.LoginScene;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import back.util.DBConnect;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Controller implements Initializable, ControllerInterface{
    protected AddressImplementation addressImplementation = new AddressImplementation();
    protected AuthorImplementation authorImplementation = new AuthorImplementation();
    protected BookImplementation bookImplementation = new BookImplementation();
    protected DeliveredBookImplementation deliveredBookImplementation = new DeliveredBookImplementation();
    protected DeliveryImplementation deliveryImplementation = new DeliveryImplementation();
    protected OrderedBookImplementation orderedBookImplementation = new OrderedBookImplementation();
    protected OrderImplementation orderImplementation = new OrderImplementation();
    protected PeopleImplementation peopleImplementation = new PeopleImplementation();
    protected ProvidersImplementation providersImplementation = new ProvidersImplementation();
    protected SectionImplementation sectionImplementation = new SectionImplementation();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void BuildTableSQL(TableView tableview, String SQL){
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

    @Override
    public void SetComponentsToNull() {

    }

    @Override
    public void ChangeDataTextHandler(MouseEvent e){

    }

    @Override
    public void LogoutTextHandler(MouseEvent e) {
        LoginScene.userID="not";
        Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
    }

    @Override
    public void ExecuteQuerySQL(String SQL){
        Connection c;
        try{
            c = DBConnect.getConnection();
            ResultSet rs = c.createStatement().executeQuery(SQL);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in Query");
        }
    }
}
