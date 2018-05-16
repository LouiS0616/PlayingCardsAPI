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
        this.owner_ = null;
    }

    /**
     * @return whether THIS has been initialized or not.
     */
    public boolean initialized() {
        return this != UNINITIALIZED;
    }

    /**
     * @param owner its owner.
     */
    public CardOwnerCertificate(CardOwner owner) {
        this.name_ = owner.toString();
        this.owner_ = owner;
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
    // Fields
    private final String name_;
    private final CardOwner owner_;
}
