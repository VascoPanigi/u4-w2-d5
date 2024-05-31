package VascoPanigi;


import VascoPanigi.entities.Book;
import VascoPanigi.entities.Catalogue;
import VascoPanigi.entities.Magazine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {
    static List<Catalogue> catalogue = new ArrayList<>();
    static List<Book> books = new ArrayList<>();
    static List<Magazine> magazines = new ArrayList<>();


    public static void main(String[] args) {
        initializeCatalogue();

        // ---------------------------------catalogue visualization----------------------------------
        for (Catalogue item : catalogue) {
            System.out.println("Title: " + item.getTitle());
            System.out.println("ISBN: " + item.getIsbn());
            System.out.println("Publication Year: " + item.getPublicationYear());
            System.out.println("Total Pages: " + item.getTotalPages());

            if (item instanceof Book book) {
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Genre: " + book.getGenre());
            } else if (item instanceof Magazine magazine) {
                System.out.println("Periodicity: " + magazine.getPeriodicity());
            }

            System.out.println();
        }
        //---------------------------------add element-------------------------------------------
        Magazine magazine = new Magazine(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Magazine.generatePeriodicity());
        Book book = new Book(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Book.generateGenre(), Book.generateAuthor());

        addItem(magazine);
        addItem(book);

        //---------------------------------search with ISBN-------------------------------------------

        System.out.println(catalogue);

        removeItemWithIsbn();
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

    public static void addItem(Catalogue item) {
        catalogue.add(item);
    }
    
    public static void removeItemWithIsbn() {
        Scanner scanner = new Scanner(System.in);


        while (true) {
            System.out.println("Insert the ISBN of the book you wish to remove: ");
            System.out.println("If you want to stop the operation, type 'stop'.");

            String userIsbn = scanner.nextLine();

            if (userIsbn.equalsIgnoreCase("stop")) {
                System.out.println("Thank you and have a great day :D");
                scanner.close();
                return;
            } else {
                boolean removed = catalogue.removeIf(item -> item.getIsbn().equals(userIsbn));

                if (removed) {
                    System.out.println("Item with ISBN " + userIsbn + " has been removed :D.");
                    System.out.println();
                } else {
                    System.out.println("No item with ISBN " + userIsbn + " found. Try again");
                    System.out.println();
                }
            }
        }
    }

}
