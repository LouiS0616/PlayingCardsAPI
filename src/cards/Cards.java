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

    // TODO: add$ 系のメソッドを一本化。今までの所属確認はpickFrom(Card, IndividualImitator)に移行。
    // TODO: ただし、オブザーバはどこかで捕まえないといけないかもしれない。 -> Cardsレイヤにupdateメソッドを置けばよい。
    // TODO: 所属確認は『Cardsに対する確認』とする。

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
            IndividualCardImitator imitator = from.peek();
            pickFrom(from, imitator);
        }
    }


    //
    // Specific drawing
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
