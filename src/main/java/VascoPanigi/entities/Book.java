package VascoPanigi.entities;

import com.github.javafaker.Faker;

public class Book extends Catalogue {
    protected String author;
    protected String genre;

    public Book(int totalPages, int publicationYear, String title, String isbn, String genre, String author) {
        super(totalPages, publicationYear, title, isbn);
        this.genre = genre;
        this.author = author;
    }

    public static String generateGenre() {
        Faker faker = new Faker();
        return faker.book().genre();
    }

    public static String generateAuthor() {
        Faker faker = new Faker();
        return faker.book().author();
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", totalPages=" + totalPages +
                ", publicationYear=" + publicationYear +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
