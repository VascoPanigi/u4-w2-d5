package VascoPanigi.entities;

import java.util.List;

public class Archive {
    protected List<Catalogue> catalogue;

    public Archive(List<Catalogue> catalogue) {
        this.catalogue = catalogue;
    }

    public void addPublication(Catalogue newEntry) {
        this.catalogue.add(newEntry);
    }

    public List<Catalogue> getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(List<Catalogue> catalogue) {
        this.catalogue = catalogue;
    }
}
