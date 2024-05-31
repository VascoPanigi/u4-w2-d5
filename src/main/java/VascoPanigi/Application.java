package VascoPanigi;


import VascoPanigi.entities.Book;
import VascoPanigi.entities.Catalogue;
import VascoPanigi.entities.Magazine;

import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static Scanner scanner = new Scanner(System.in);
    static List<Catalogue> catalogue = new ArrayList<>();
    static List<Book> books = new ArrayList<>();
    static List<Magazine> magazines = new ArrayList<>();

    public static void main(String[] args) {
        initializeCatalogue();

        System.out.println("Welcome to our store! :D");
        System.out.println();

        while (true) {
            System.out.println("Which operation do you wish to perform?");
            System.out.println("1-See our catalogue, 2-Generate an element, 3-remove with ISBN, 4-search with ISBN, 5-search for publication year, 6-search by author");
            System.out.println("Type 0 to exit.");


            try {
                int userChoice = scanner.nextInt();
                scanner.nextLine();


                switch (userChoice) {
                    case 0:
                        System.out.println("See you space cowboy!");
                        scanner.close();
                        return;
                    case 1:
                        System.out.println("This is our catalogue.");
                        for (Catalogue item : catalogue) {
                            printCatalogueItemDetails(item);
                            System.out.println();
                        }
                        System.out.println();
                        break;
                    case 2:
                        addItem();
                        break;
                    case 3:
                        removeItemWithIsbn();
                        break;
                    case 4:
                        searchItemWithIsbn();
                        break;
                    case 5:
                        searchByPublicationYear();
                        break;
                    case 6:
                        searchByAuthor();
                    default:
                        System.out.println("Invalid choice, try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }


        // ---------------------------------catalogue initialization----------------------------------


//        //---------------------------------add element-------------------------------------------
//        THE FOLLOWING CODE DOESNT WORK ANYMORE CUZ I DELETED THE ARGUMENT IN THE ADDITEM CLASS
//        Magazine magazine = new Magazine(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Magazine.generatePeriodicity());
//        Book book = new Book(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Book.generateGenre(), Book.generateAuthor());
//
//        addItem(magazine);
//        addItem(book);
//

        //---------------------------------remove with ISBN-------------------------------------------


//        removeItemWithIsbn();

        //---------------------------------search with ISBN-------------------------------------------
//        searchItemWithIsbn();

        //---------------------------------search for Year-------------------------------------------

//        searchByPublicationYear();

        //---------------------------------search by author-------------------------------------------

//        searchByAuthor();

        //---------------------------------UX optimization-------------------------------------------


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

    public static void addItem() {

        while (true) {
            System.out.println("Which item do you wish to add? Press 0 to exit the operation.");
            System.out.println("1-book, 2-magazine");

            try {
                int userChoice = scanner.nextInt();
                scanner.nextLine();

                if (userChoice != 0) {
                    switch (userChoice) {
                        case 1:
                            Book book = new Book(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Book.generateGenre(), Book.generateAuthor());
                            catalogue.add(book);
                            System.out.println("Book successfully added.");
                            System.out.println();
                            break;
                        case 2:
                            Magazine magazine = new Magazine(Catalogue.generateTotalPages(), Catalogue.generateYear(), Catalogue.generateTitle(), Catalogue.generateIsbn(), Magazine.generatePeriodicity());
                            catalogue.add(magazine);
                            System.out.println("Magazine successfully added.");
                            System.out.println();
                            break;
                        default:
                            System.out.println("Invalid choice, try again.");
                            break;
                    }
                } else {
                    return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }

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
        while (true) {
            System.out.println("Insert the publication year of the book you wish to search: ");
            System.out.println("If you want to stop the operation, type 0.");
            try {
                int userInput = scanner.nextInt();

                if (userInput == 0) {
                    System.out.println("See you, space cowboy.");
                    return null;
                }

                List<Catalogue> matchingItems = catalogue.stream().filter(item -> item.getPublicationYear() == userInput).collect(Collectors.toList());

                for (Catalogue item : matchingItems) {
                    printCatalogueItemDetails(item);
                    System.out.println();
                }
                return matchingItems;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }


    public static void searchByAuthor() {
        while (true) {
            System.out.println("Insert the author you wish to search: ");
            System.out.println("If you want to stop the operation, type 'stop'.");

            String searchedAuthor = scanner.nextLine();
            if (searchedAuthor.equalsIgnoreCase("stop")) {
                System.out.println("See you, space cowboy.");
                break;
            }

            List<Catalogue> results = catalogue.stream()
                    .filter(item -> item instanceof Book)
                    .map(item -> (Book) item)
                    .filter(book -> book.getAuthor().equalsIgnoreCase(searchedAuthor))
                    .collect(Collectors.toList());

            if (results.isEmpty()) {
                System.out.println("No items found for this author. :(");
            } else {
                System.out.println("Items found for the author " + searchedAuthor + ":");
                for (Catalogue item : results) {
                    printCatalogueItemDetails(item);
                    System.out.println();
                }
            }
        }

    }
}