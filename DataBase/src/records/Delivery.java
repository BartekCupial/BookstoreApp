package records;

import kernel.AbstractRecord;

import java.sql.Date;
import java.util.Calendar;


public class Delivery extends AbstractRecord{
    private int ID;
    private String NIP;
    private String invoiceNumber;
    private String date;
    private String status;

    public Delivery(){

    }

    public Delivery(String NIP, String invoiceNumber, String status){
        this.NIP = NIP;
        this.invoiceNumber = invoiceNumber;
        this.status = status;
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        this.date = date.toString();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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
