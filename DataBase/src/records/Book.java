package records;

import kernel.AbstractRecord;

public class Book extends AbstractRecord{
    private String ISBN;
    private String title;
    private int author;
    private int section;
    private int number;
    private String published;
    private int year;
    private int price;
    private String description;

    public Book(){

    }
    public Book(String ISBN, String title, int author, int section, int number, String published, int year, int price, String description){
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.section = section;
        this.number = number;
        this.published = published;
        this.year = year;
        this.price = price;
        this.description = description;
    }


    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
