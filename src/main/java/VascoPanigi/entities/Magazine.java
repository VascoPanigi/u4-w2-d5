package VascoPanigi.entities;

import VascoPanigi.enums.Periodicity;

import java.util.Random;

public class Magazine extends Catalogue {
    protected Periodicity periodicity;

    public Magazine(int totalPages, int publicationYear, String title, String isbn, Periodicity periodicity) {
        super(totalPages, publicationYear, title, isbn);
        this.periodicity = periodicity;
    }

    public static Periodicity generatePeriodicity() {
        Random random = new Random();
        Periodicity[] periocityValues = Periodicity.values();
        return periocityValues[random.nextInt(periocityValues.length)];
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicity=" + periodicity +
                ", totalPages=" + totalPages +
                ", publicationYear=" + publicationYear +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
