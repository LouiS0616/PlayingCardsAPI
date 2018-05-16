package nsr.loui.playingcards.cards;

/**
 * This class certificate cards' owner.
 *
 * Class extending this have to behave with utmost of care avoid to card duplication.
 * Try not to extend this class as much as possible.
 */
public class CardOwnerCertificate {
    /**
     * @param owner its owner.
     */
    public CardOwnerCertificate(CardOwner owner) {
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
     * @return its owner.
     */
    public CardOwner getOwner() {
        return this.owner_;
    }

    /**
     * @return its name.
     */
    @Override
    public String toString() {
        return this.owner_.toString();
    }

    //
    //
    private final CardOwner owner_;
}
