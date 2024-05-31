package VascoPanigi;


import VascoPanigi.entities.Book;
import VascoPanigi.entities.Catalogue;
import VascoPanigi.entities.Magazine;

import java.util.ArrayList;
import java.util.List;

public class Application {
    static List<Catalogue> catalogue = new ArrayList<>();
    static List<Book> books = new ArrayList<>();
    static List<Magazine> magazines = new ArrayList<>();


    public static void main(String[] args) {
        initializeCatalogue();

        System.out.println(catalogue);
    }

    public static void initializeCatalogue() {
        generateBooks();
        generateMagazines();

        catalogue.addAll(books);
        catalogue.addAll(magazines);
    }

    public static void generateBooks() {
        for (int i = 0; i < 10; i++) {
            Book book = new Book(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Book.generateGenre(), Book.generateAuthor());
            books.add(book);
        }
    }

    public static void generateMagazines() {
        for (int i = 0; i < 10; i++) {
            Magazine magazine = new Magazine(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Magazine.generatePeriodicity());
            magazines.add(magazine);
        }
    }

}
