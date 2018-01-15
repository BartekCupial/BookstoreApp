package back.records;

import back.kernel.AbstractRecord;

import java.sql.Date;
import java.util.Calendar;

public class Order extends AbstractRecord{
    private int ID;
    private String orderID;
    private String date;
    private String status;

    public Order(){

    }

    public Order(String orderID){
        this.orderID = orderID;
        this.status = "Zlozone";
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        this.date = date.toString();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
