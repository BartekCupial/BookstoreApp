package front.controllers;

import application.Main;
import implementation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import records.*;

import javafx.scene.control.TableView;


import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ClientPanelScene implements Initializable {

    @FXML
    private Text textMail;
    @FXML
    private Text textStreet;
    @FXML
    private Text textProvince;
    @FXML
    private Text textCountry;
    @FXML
    private Text textPostal;
    @FXML
    private Text textFirst;
    @FXML
    private Text textLast;
    @FXML
    private Text textPhone;
    @FXML
    private Text textBuilding;
    @FXML
    private Text textCity;
    @FXML
    private TableView ordersTable;
    @FXML
    private TableView booksTable;
    @FXML
    private TableView sectionsTable;
    @FXML
    private TableView authorsTable;
    @FXML
    private TableView basketTable;

    private String SQLQuery;
    private boolean checker = false;
    private boolean clicker = false;

    private ArrayList<Book> books = new ArrayList<>();

    private PeopleImplementation peopleImplementation = new PeopleImplementation();
    private AddressImplementation addressImplementation = new AddressImplementation();
    private BookImplementation bookImplementation = new BookImplementation();
    private OrderedBookImplementation orderedBookImplementation = new OrderedBookImplementation();
    private OrderImplementation orderImplementation = new OrderImplementation();




    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void OrderHandler(MouseEvent e) {
        Order order = new Order(LoginScene.userID);
        int orderID = orderImplementation.insertint(order);
        for(Book b: books){
            //TODO: instead of set count elements and than insert number of them
            OrderedBook orderedBook = new OrderedBook(b.getISBN(), orderID, 1);
            orderedBookImplementation.insert(orderedBook);
        }
        for ( int i = 0; i<basketTable.getColumns().size(); i++) {
            basketTable.getColumns().clear();
        }
        basketTable.refresh();
        //resets basket

    }

    public void AddToBasketHandler(MouseEvent e) {
        //Made that you only can order one book in one order
        if(checker && clicker) {
            TablePosition pos = (TablePosition) booksTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            TableColumn col = pos.getTableColumn();
            String data = (String) col.getCellObservableValue(booksTable.getItems().get(row)).getValue();

            Book book = (Book) bookImplementation.selectById(data);
            if(book.getISBN()==null){
                System.out.println("wrong column clicked!");
            }else {
                books.add(book);
                System.out.println("book added to bracket!");
            }
        }
    }

    public void ClearBasketHandler(MouseEvent e) {
        books.clear();
        for ( int i = 0; i<basketTable.getColumns().size(); i++) {
            basketTable.getColumns().clear();
        }
        basketTable.refresh();
    }

    public void ClickTableHandler(MouseEvent event){
        if(event.getButton().equals(MouseButton.PRIMARY)){
            clicker = true;
        }
    }

    public void LogoutTextHandler(MouseEvent e) { Main.mainContainer.setScene(Main.LoginSceneID, Main.window);
    }

    public void ChangeDataTextHandler(MouseEvent e) {
        ChangeYourDataScene.changeYourDataScene.FillTextFields();
        Main.mainContainer.setScene(Main.ChangeYourDataSceneID, Main.window);
    }

    public void OrdersHandler(Event e) {
        checker = true;
        for ( int i = 0; i<ordersTable.getColumns().size(); i++) {
            ordersTable.getColumns().clear();
        }
        SQLQuery = "SELECT * FROM Zamowienia WHERE IDzamawiajacego = " + '"' + LoginScene.userID + '"';
        Main.BuildTableSQL(ordersTable, SQLQuery);
    }

    public void BooksHandler(Event e) {
        checker = true;
        clicker = false;
        for ( int i = 0; i<booksTable.getColumns().size(); i++) {
            booksTable.getColumns().clear();
        }
        SQLQuery = "SELECT * FROM Ksiazki";
        Main.BuildTableSQL(booksTable, SQLQuery);
    }

    public void SectionsHandler(Event e) {
        checker = false;
        for ( int i = 0; i<sectionsTable.getColumns().size(); i++) {
            sectionsTable.getColumns().clear();
        }
        SQLQuery = "SELECT * FROM Dzialy";
        Main.BuildTableSQL(sectionsTable, SQLQuery);
    }

    public void AuthorsHandler(Event e) {
        checker = false;
        for ( int i = 0; i<authorsTable.getColumns().size(); i++) {
            authorsTable.getColumns().clear();
        }
        SQLQuery = "SELECT * FROM Autorzy";
        Main.BuildTableSQL(authorsTable, SQLQuery);
    }

    public void YourDataHandler(Event e) {
        checker = false;
        People people = (People) peopleImplementation.selectById(LoginScene.userID);
        Address address = (Address) addressImplementation.selectById(people.getAddress());

        textMail.setText(people.getMail());
        textFirst.setText(people.getFirstName());
        textLast.setText(people.getLastName());
        textPhone.setText(people.getPhone());

        textCity.setText(address.getCity());
        textBuilding.setText(address.getBuildingNumber());
        textStreet.setText(address.getStreet());
        textProvince.setText((address.getProvince()));
        textCountry.setText(address.getCountry());
        textPostal.setText(address.getPostalCode());
    }

    public void BasketHandler(Event e) {
        checker = false;
        for ( int i = 0; i<basketTable.getColumns().size(); i++) {
            basketTable.getColumns().clear();
        }
        String string = "";
        for (int i = 0; i < books.size(); i++){
            string = string + '"' + books.get(i).getISBN() + '"' + " OR Ksiazki.ISBN = ";
        }
        if(books.size()>0){
            string = string.substring(0, string.length() - 18);
        }else{
            string = "'1'";
        }
        SQLQuery = "SELECT Ksiazki.ISBN, Ksiazki.tytul, Autorzy.nazwisko FROM Ksiazki JOIN Autorzy ON (Ksiazki.autor = Autorzy.ID) WHERE Ksiazki.ISBN = " + string ;
        Main.BuildTableSQL(basketTable, SQLQuery);
    }
}
