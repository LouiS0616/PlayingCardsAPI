package nsr.loui.playingcards;

import nsr.loui.playingcards.card.RankedCard;
import nsr.loui.playingcards.card.Suit;
import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.card.comparator.DefaultCardComparator;
import nsr.loui.playingcards.card.comparator.JokersLastComparator;
import nsr.loui.playingcards.card.imitator.JokerImitator;
import nsr.loui.playingcards.card.imitator.RankImitator;
import nsr.loui.playingcards.card.imitator.SuitImitator;
import nsr.loui.playingcards.card.imitator.UnitedImitator;
import nsr.loui.playingcards.cards.Deck;
import nsr.loui.playingcards.contrib.SwitchableAutoSortedCards;
import nsr.loui.playingcards.observer.Observer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class ContribSample$Switchable {
    enum OrderType {
        DEFAULT,
        JOKER_IS_LAST$SUIT_NATURAL$RANK_NATURAL,
    }

    public static void main(String[] args) {
        Map<OrderType, CardComparator> tagToComparator = new HashMap<>();
        {
            tagToComparator.put(
                OrderType.DEFAULT, new DefaultCardComparator()
            );
            tagToComparator.put(
                OrderType.JOKER_IS_LAST$SUIT_NATURAL$RANK_NATURAL,
                new JokersLastComparator() {
                    @Override
                    protected int compare(RankedCard rankedCard1, RankedCard rankedCard2) {
                        return Comparator
                            .comparing(RankedCard::getSuit)
                            .thenComparingInt(RankedCard::getRank)
                            .compare(rankedCard1, rankedCard2)
                        ;
                    }
                }
            );
        }

        SwitchableAutoSortedCards<OrderType> cards = new SwitchableAutoSortedCards<>(
            "Cards", Observer.STUB, tagToComparator, OrderType.DEFAULT
        );

        Deck deck = new Deck();
        UnitedImitator imitator = new UnitedImitator(
            new JokerImitator(),
            new RankImitator(10),
            new SuitImitator(Suit.CLUB),
            new SuitImitator(Suit.SPADE)
        );

        cards.divideFrom(deck, imitator);

        cards.printInfo();
        cards.switchComparator(OrderType.JOKER_IS_LAST$SUIT_NATURAL$RANK_NATURAL);
        cards.printInfo();
    }
}
