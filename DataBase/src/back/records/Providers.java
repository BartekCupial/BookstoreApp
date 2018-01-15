package back.records;

import back.kernel.AbstractRecord;

public class Providers extends AbstractRecord {
    private String NIP;
    private String companyName;
    private String ownerFirstName;
    private String ownerLastName;
    private int address;
    private String phone;
    private String mail;
    private String accountNumber;

    public Providers(){

    }

    public Providers(String NIP, String companyName, String ownerFirstName, String ownerLastName, int address, String phone, String mail, String accountNumber){
        this.NIP = NIP;
        this.companyName = companyName;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.accountNumber = accountNumber;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
