package nsr.loui.playingcards.cards;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.Suit;
import nsr.loui.playingcards.card.imitator.CardImitator;
import nsr.loui.playingcards.card.imitator.IndividualCardImitator;
import nsr.loui.playingcards.card.imitator.SuitImitator;
import nsr.loui.playingcards.card.imitator.WildCardImitator;
import nsr.loui.playingcards.exceptions.CardNotEnoughException;
import nsr.loui.playingcards.exceptions.CardNotFoundException;
import nsr.loui.playingcards.exceptions.OwnerCertificateImproperException;
import nsr.loui.playingcards.util.PyLikePrinter;
import nsr.loui.playingcards.util.StreamAble;

import java.util.stream.Collectors;


/**
 * This class means loui.playingcards.cards and handle loui.playingcards.card transfer safety way.
 */
public abstract class BaseCards implements Iterable<Card>, StreamAble<Card> {
    //
    // Generate methods
    BaseCards(String name) {
        this.name_ = name;
    }

    /**
     * Set owner when loui.playingcards.cards' owner has been uninitialized.
     * @param owner Owner what should have this loui.playingcards.cards.
     * @throws OwnerCertificateImproperException When owner is tried to overwrite.
     */
    protected final void setOwner(CardOwner owner) {
        if(this.certificate_ != null) {
            throw new OwnerCertificateImproperException("You can NOT reset loui.playingcards.card owner.");
        }

        this.certificate_ = owner.getCertificate();
    }


    /**
     * @param imitator Purpose loui.playingcards.card imitator.
     * @return Whether any loui.playingcards.card what is equivalent to imitator included in THIS or not.
     */
    public boolean include(CardImitator imitator) {
        return stream()
            .anyMatch(imitator::isEquivalent)
        ;
    }

    /**
     * @param imitator Purpose loui.playingcards.card imitator.
     * @return How many loui.playingcards.cards what is equivalent to imitator included in THIS.
     */
    public int countCard(WildCardImitator imitator) {
        return (int)stream()
            .filter(imitator::isEquivalent)
            .count()
        ;
    }

    /**
     * @return #loui.playingcards.cards
     */
    public abstract int countCard();


    //
    // Methods related drawing

    /**
     * This method MUST be called only by BaseCards#pickFrom(BaseCards, IndividualCardImitator).
     * Because this method does not check loui.playingcards.card certificate at all.
     * @param card Card you want to add THIS.
     */
    protected abstract void add(Card card);

    /**
     * This method behave randomly or not in accordance with its implementation.
     * @throws CardNotEnoughException When loui.playingcards.cards is empty.
     * @return A loui.playingcards.card imitator what mean drawable loui.playingcards.card.
     */
    protected abstract IndividualCardImitator peek() throws CardNotEnoughException;

    /**
     * This method MUST be called only by BaseCards#pickFrom(BaseCards, IndividualCardImitator).
     * Do not call this method thoughtless avoid to lost loui.playingcards.card, because this method treats raw that.
     * @param purpose Card imitator what you want to draw.
     * @throws CardNotFoundException When loui.playingcards.card is not included in loui.playingcards.cards.
     * @return Drawn loui.playingcards.card.
     */
    protected abstract Card draw(IndividualCardImitator purpose) throws CardNotFoundException;

    /**
     * This method is called when "FROM -CARD-{@literal >} THIS".
     * @param card Added loui.playingcards.card.
     * @param from Where loui.playingcards.card from.
     */
    protected abstract void update(Card card, BaseCards from);


    /**
     * In any way you draw loui.playingcards.card, you MUST use this method.
     * Because this method is responsible for check loui.playingcards.card affiliation.
     * @param from Where you want to pick a loui.playingcards.card from.
     * @param purpose What you want to draw.
     * @throws OwnerCertificateImproperException When certificates are difference between THIS and FROM.
     */
    public final void pickFrom(BaseCards from, IndividualCardImitator purpose) {
        if(this.certificate_ == null) {
            this.certificate_ = from.certificate_;
        }

        if(this.certificate_.isEquivalent(from.certificate_)) {
            Card card = from.draw(purpose);

            update(card, from);
            add(card);
        }
        else {
            throw new OwnerCertificateImproperException("You MUST NOT mix distinct deck.");
        }
    }


    //
    // Display methods
    @Override
    public String toString() {
        return this.name_;
    }

    private String toStringCards() {
        return stream()
            .map(Card::toString)
            .collect(Collectors.joining(", "))
        ;
    }
    private String toStringBreakdown() {
        return Suit.stream()
            .map(
                suit -> String.format("%s: %d", suit, countCard(new SuitImitator(suit)))
            )
            .collect(Collectors.joining(", "))
        ;
    }

    /**
     * Please use it for just print debugging.
     */
    public void printInfo() {
        new PyLikePrinter("\n").print(
            "----------------------------------------------------------------",
            "Name:        " + name_,
            "Affiliation: " + certificate_,
            "#Card:       " + countCard(),
            "Breakdown:   " + toStringBreakdown(),
            "",
            toStringCards(),
            "----------------------------------------------------------------"
        );
    }


    //
    // Fields
    private final String name_;
    private CardOwnerCertificate certificate_;
}
