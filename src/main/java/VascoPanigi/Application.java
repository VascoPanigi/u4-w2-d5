package VascoPanigi;


import VascoPanigi.entities.Book;
import VascoPanigi.entities.Catalogue;
import VascoPanigi.entities.Magazine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Application {
    public static Scanner scanner = new Scanner(System.in);
    static List<Catalogue> catalogue = new ArrayList<>();
    static List<Book> books = new ArrayList<>();
    static List<Magazine> magazines = new ArrayList<>();

    public static void main(String[] args) {
        initializeCatalogue();

        // ---------------------------------catalogue visualization----------------------------------
        for (Catalogue item : catalogue) {
            printCatalogueItemDetails(item);

            System.out.println();
        }
        //---------------------------------add element-------------------------------------------
        Magazine magazine = new Magazine(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Magazine.generatePeriodicity());
        Book book = new Book(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Book.generateGenre(), Book.generateAuthor());

        addItem(magazine);
        addItem(book);

        //---------------------------------remove with ISBN-------------------------------------------


        removeItemWithIsbn();

        //---------------------------------search with ISBN-------------------------------------------
        searchItemWithIsbn();

        //---------------------------------search for Year-------------------------------------------

        searchByPublicationYear();


        scanner.close();
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

    public static void printCatalogueItemDetails(Catalogue item) {

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
    }

    public static void removeItemWithIsbn() {
        while (true) {
            System.out.println("Insert the ISBN of the book you wish to remove: ");
            System.out.println("If you want to stop the operation, type 'stop'.");

            String userIsbn = scanner.nextLine();

            if (userIsbn.equalsIgnoreCase("stop")) {
                System.out.println("Thank you and have a great day :D");
                return;
            } else {
                Optional<Catalogue> searchResult = catalogue.stream()
                        .filter(item -> item.getIsbn().equals(userIsbn))
                        .findFirst();

                if (searchResult.isPresent()) {
                    Catalogue result = searchResult.get();
                    System.out.println("Are you really sure you want to remove this book? " + result.getTitle());
                    System.out.println("1-yes, 2-no");
                    int twoStepVerification = Integer.parseInt(scanner.nextLine());

                    switch (twoStepVerification) {
                        case 1:
                            boolean removed = catalogue.removeIf(item -> item.getIsbn().equals(userIsbn));
                            if (removed) {
                                System.out.println("Item with ISBN " + userIsbn + " has been removed :D.");
                                System.out.println();
                            }
                            break;
                        case 2:
                            System.out.println("The item was not removed.");
                            System.out.println();
                            break;
                        default:
                            System.out.println("No item with ISBN " + userIsbn + " found. Try again");
                            System.out.println();
                            break;
                    }
                }
            }
        }
    }


    public static Catalogue searchItemWithIsbn() {
        while (true) {
            System.out.println("Insert the ISBN of the book you wish to search: ");
            System.out.println("If you want to stop the operation, type 'stop'.");

            String userIsbn = scanner.nextLine();
            if (userIsbn.equalsIgnoreCase("stop")) {
                System.out.println("See you, space cowboy.");
                return null;
            }

            Optional<Catalogue> searchResult = catalogue.stream()
                    .filter(item -> item.getIsbn().equals(userIsbn))
                    .findFirst();

            if (searchResult.isPresent()) {

                Catalogue result = searchResult.get();
                if (result instanceof Book) {
                    System.out.println("Book found! :D");
                } else {
                    System.out.println("Magazine found! :D");

                }
                printCatalogueItemDetails(result);
                System.out.println();

            } else {
                System.out.println("There's nothing with this ISBN in our magazine. Try again.");

            }
        }
    }


    public static List<Catalogue> searchByPublicationYear() {

        System.out.println("Insert the publication year of the book you wish to search: ");
        System.out.println("If you want to stop the operation, type 0.");

        int userInput = scanner.nextInt();

        if (userInput == 0) {
            System.out.println("See you, space cowboy.");
            return null;
        }

        List<Catalogue> matchingItems = new ArrayList<>();

        matchingItems = catalogue.stream().filter(item -> item.getPublicationYear() == userInput).collect(Collectors.toList());

        for (Catalogue item : matchingItems) {
            printCatalogueItemDetails(item);
            System.out.println();
        }
        return matchingItems;
    }

}
