
import implementation.*;
import records.*;


public class App {
    public static void main(String [] args){


//        SectionImplementation Simp = new SectionImplementation();
//        Section section = new Section("Literatura brzydka");
//        Simp.insert(section);
//        //Simp.update(section, 1);
//        //Simp.delete(1);
//
//        AuthorImplementation Aimp = new AuthorImplementation();
//        Author autor = new Author("Pwałe", "Mickiewicz");
//        Aimp.insert(autor);
//
//
//        BookImplementation imp = new BookImplementation();
//        Book book = new Book("ASDQ123", "Dziady", 1, 1, 1, "BOR Recordsxd", 1937, 37, "dziadostwo");
//        imp.insert(book);


        AddressImplementation Adimp = new AddressImplementation();
        Address address = new Address("Krolewska", "37", "11-111", "Wrocław", "Dolnoslaskie", "Bieszczady");
        Adimp.insert(address);
        //Adimp.delete(1);
        Adimp.update(address, 3);

//        PeopleImplementation Pimp = new PeopleImplementation();
//        People people = new People("wera222", "Weronika", "Ziemianek", 1, "EZ@gmai.com", "fajerweki", "123", "Admin");
//        Pimp.insert(people);
//
//        OrderImplementation Oimp = new OrderImplementation();
//        //Order order = new Order("wera222", "Zlozone");
//        //Oimp.insert(order);
//
//        OrderedBookImplementation OBimp = new OrderedBookImplementation();
//        OrderedBook orderedBook = new OrderedBook("ASDQ123", 1, 1);
//        OBimp.insert(orderedBook);
//
//        ProvidersImplementation Primp = new ProvidersImplementation();
//        Providers providers = new Providers("NIP", "IBM", "Bill", "Gates", 1, "111111", "IBM@gmial.com", "22222");
//        Primp.insert(providers);
//
//        DeliveryImplementation Dimp = new DeliveryImplementation();
//        Delivery delivery = new Delivery("NIP", "qweq113", "Zlozono");
//        Dimp.insert(delivery);
//
//        DeliveredBookImplementation DBimp = new DeliveredBookImplementation();
//        DeliveredBook deliveredBook = new DeliveredBook("ASDQ123", 1, 1);
//        DBimp.insert(deliveredBook);

    }
}
