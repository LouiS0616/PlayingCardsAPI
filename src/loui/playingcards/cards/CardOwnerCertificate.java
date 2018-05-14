package loui.playingcards.cards;

/**
 * This class certificate loui.playingcards.cards' owner.
 * This have to be extended carefully avoid to loui.playingcards.card duplication.
 */
public class CardOwnerCertificate {
    public CardOwnerCertificate(String name) {
        this.name_ = name;
    }

    /**
     * @param other Other certificate.
     * @return Whether OTHER is equivalent THIS or not.
     */
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
