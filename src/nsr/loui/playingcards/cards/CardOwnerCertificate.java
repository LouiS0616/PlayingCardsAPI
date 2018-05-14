package nsr.loui.playingcards.cards;

/**
 * This class certificate cards' owner.
 *
 * Class extending this have to behave with utmost of care avoid to card duplication.
 * Try not to extend this class as much as possible.
 */
public class CardOwnerCertificate {
    /**
     * @param name its name.
     */
    public CardOwnerCertificate(String name) {
        this.name_ = name;
    }

    /**
     * @param other other certificate.
     * @return whether OTHER is equivalent THIS or not.
     */
    public boolean isEquivalent(CardOwnerCertificate other) {
        return this == other;
    }

    /**
     * @return its name.
     */
    @Override
    public String toString() {
        return this.name_;
    }

    //
    //
    private final String name_;
}
