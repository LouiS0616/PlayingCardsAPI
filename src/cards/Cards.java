package cards;

import card.Card;
import card.imitator.individual.IndividualCardImitator;
import card.imitator.wild.WildCardImitator;
import exceptions.CardNotEnoughException;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Cards extends BaseCards {
    //
    // Generate methods
    protected Cards(String name, Observer observer) {
        super(name);
        this.observer_ = observer;
    }


    //
    // Methods related drawing
    protected abstract void add$all_check_has_done(Card card);

    // TODO: add$ 系のメソッドを一本化。今までの所属確認はpickFrom(Card, IndividualImitator)に移行。
    // TODO: ただし、オブザーバはどこかで捕まえないといけないかもしれない。 -> Cardsレイヤにupdateメソッドを置けばよい。
    // TODO: 所属確認は『Cardsに対する確認』とする。
    @Override
    protected final void add$owner_is_already_checked(Card card, Cards from) {
        this.observer_.update(Observer.Type.ADD, card, this, from);
        from.observer_.update(Observer.Type.PICK, card, from, this);

        add$all_check_has_done(card);
    }


    // TODO: this == from は呼び出し元の責任とする。Docに明記の上、チェックを削除。
    //
    // Randomly drawing
    public void pickFrom(Cards from, int num) throws CardNotEnoughException {
        if(this == from) {
            return;
        }

        if(from.countCard() < num) {
            throw new CardNotEnoughException();
        }

        for(int i = 0; i < num; ++i) {
            Card card = from.draw();
            this.add$any_check_is_undone(card, from);
        }
    }


    //
    // Specific drawing

    // TODO: BaseCardsに移動させ、必ずとおるメソッドとして扱う。
    public void pickFrom(Cards from, IndividualCardImitator purpose) {
        if(this == from) {
            return;
        }

        Card card = from.draw(purpose);
        this.add$any_check_is_undone(card, from);
    }

    public void divideFrom(Cards from, WildCardImitator wildPurpose) {
        if(this == from) {
            return;
        }

        // DO make list first avoid to java.util.ConcurrentModificationException.
        List<IndividualCardImitator> purposes = from.stream()
            .filter(wildPurpose::isEquivalent)
            .map(Card::getIndividualImitator)
            .collect(Collectors.toList())
        ;

        purposes.forEach(imitator -> pickFrom(from, imitator));
    }
    public void divideFrom(Cards from, WildCardImitator... wildPurposes) {
        for(WildCardImitator wildPurpose: wildPurposes) {
            divideFrom(from, wildPurpose);
        }
    }

    //
    //
    private final Observer observer_;
}
