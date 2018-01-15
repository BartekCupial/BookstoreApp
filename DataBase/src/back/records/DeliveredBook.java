package back.records;

import back.kernel.AbstractRecord;

public class DeliveredBook extends AbstractRecord {
    private int ID;
    private  String ISBN;
    private int deliveryID;
    private int number;

    public DeliveredBook(){

    }

    public DeliveredBook(String ISBN, int deliveryID, int number){
        this.ISBN = ISBN;
        this.deliveryID = deliveryID;
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

    public int getDeliveryID() {
        return deliveryID;
    }

    public void setDeliveryID(int deliveryID) {
        this.deliveryID = deliveryID;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
