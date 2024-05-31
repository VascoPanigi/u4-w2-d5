package VascoPanigi.entities;

import com.github.javafaker.Faker;

import java.util.Random;


public abstract class Catalogue {
    protected String isbn;
    protected String title;
    protected int publicationYear;
    protected int totalPages;

    public Catalogue(int totalPages, int publicationYear, String title, String isbn) {
        this.totalPages = totalPages;
        this.publicationYear = publicationYear;
        this.title = title;
        this.isbn = isbn;
    }

    public static String generateIsbn() {
        Faker faker = new Faker();
        return faker.code().isbn10(true);
    }

    public static String generateTitle() {
        Faker faker = new Faker();
        return faker.book().title();
    }

    public static int generateYear() {
        Random random = new Random();
        return random.nextInt(1950, 2024);
    }

    public static int generateTotalPages() {
        Random random = new Random();
        return random.nextInt(1, 2000);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
}
