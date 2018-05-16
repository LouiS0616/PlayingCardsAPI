package nsr.loui.playingcards.cards;

/**
 * This class certificate cards' owner.
 *
 * Class extending this have to behave with utmost of care avoid to card duplication.
 * Try not to extend this class as much as possible.
 */
public class CardOwnerCertificate {
    /**
     * UNINITIALIZED certificate is prepared instead of null.
     */
    static CardOwnerCertificate UNINITIALIZED = new CardOwnerCertificate();

    private CardOwnerCertificate() {
        this.name_ = "UNINITIALIZED";
    }

    /**
     * @return whether THIS has been initialized or not.
     */
    boolean initialized() {
        return this != UNINITIALIZED;
    }

    /**
     * @param owner its owner.
     */
    CardOwnerCertificate(CardOwner owner) {
        this.name_ = owner.toString();
    }

    /**
     * @param other other certificate.
     * @return whether OTHER is equivalent THIS or not.
     */
    boolean isEquivalent(CardOwnerCertificate other) {
        return this.equals(other);
    }

    /**
     * @return its name.
     */
    @Override
    public String toString() {
        return this.name_;
    }

    //
    // Fields
    private final String name_;
}
