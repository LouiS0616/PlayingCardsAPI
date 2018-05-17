package nsr.loui.playingcards.contrib.sample;

import nsr.loui.playingcards.card.RankedCard;
import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.card.comparator.DefaultCardComparator;
import nsr.loui.playingcards.card.comparator.JokersLastComparator;
import nsr.loui.playingcards.card.imitator.JokerImitator;
import nsr.loui.playingcards.cards.Deck;
import nsr.loui.playingcards.contrib.SwitchableAutoSortedCards;
import nsr.loui.playingcards.observer.Observer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Sample how to use Sample_SwitchableAutoSortedCards .
 */
class Sample_SwitchableAutoSortedCards {
    enum OrderType {
        DEFAULT,
        DAIFUGO_ORDER
    }

    public static void main(String[] args) {
        //
        // Prepare deck.
        Deck deck = new Deck();
        deck.shuffle();

        //
        // Make map of Comparators.
        Map<OrderType, CardComparator> tagToComparator = new HashMap<>();
        {
            tagToComparator.put(
                OrderType.DEFAULT, new DefaultCardComparator()
            );
            tagToComparator.put(
                OrderType.DAIFUGO_ORDER, new DaifugoComparator()
            );
        }

        //
        // Make cards.
        SwitchableAutoSortedCards<OrderType> cards = new SwitchableAutoSortedCards<>(
            "Cards", null, Observer.STUB, tagToComparator, OrderType.DEFAULT
        );
        cards.divideFrom(deck, new JokerImitator());
        cards.pickFrom(deck, 10);

        //
        // Switch and print.
        for(OrderType type: OrderType.values()) {
            cards.switchComparator(type);
            cards.printInfo();
        }
    }
}


/**
 * Comparator tuned for Japanese card-game "Daifugo".
 */
class DaifugoComparator extends JokersLastComparator {
    @Override
    protected int compare(RankedCard rankedCard1, RankedCard rankedCard2) {
        return Comparator
            .comparingInt(
                (RankedCard rankedCard) -> (rankedCard.getRank() + 10) % 13
            )
            .thenComparing(RankedCard::getSuit)
            .compare(rankedCard1, rankedCard2)
        ;
    }
}