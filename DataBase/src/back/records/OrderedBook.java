package back.records;

import back.kernel.AbstractRecord;

public class OrderedBook extends AbstractRecord {
    private int ID;
    private String ISBN;
    private int orderID;
    private int number;

    public OrderedBook(){

    }

    public OrderedBook(String ISBN, int orderID, int number){
        this.ISBN = ISBN;
        this.orderID = orderID;
        this.number = number;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
