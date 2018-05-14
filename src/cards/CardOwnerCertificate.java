package cards;

/**
 * This class have to be extended carefully avoid to card duplication.
 */
public class CardOwnerCertificate {
    public CardOwnerCertificate(String name) {
        this.name_ = name;
    }

    public boolean isEquivalent(CardOwnerCertificate other) {
        return this == other;
    }

    @Override
    public String toString() {
        return this.name_;
    }

    //
    //
    private final String name_;
}
