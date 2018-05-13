package cards;

/**
 * This class have to be extended carefully avoid to card duplication.
 */
public class CardAffiliation {
    public CardAffiliation(String name) {
        this.name_ = name;
    }

    public boolean isEquivalent(CardAffiliation other) {
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
