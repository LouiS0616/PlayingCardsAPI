package nsr.loui.playingcards.contrib.sample;

import nsr.loui.playingcards.card.RankedCard;
import nsr.loui.playingcards.card.Suit;
import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.card.comparator.DefaultCardComparator;
import nsr.loui.playingcards.card.comparator.JokersFirstComparator;
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

/**
 * Sample how to use Sample_SwitchableAutoSortedCards .
 */
class Sample_SwitchableAutoSortedCards {
    enum OrderType {
        DEFAULT,
        JOKER_IS_LAST$SUIT_NATURAL$RANK_NATURAL,
        JOKER_IS_LAST$RANK_NATURAL$SUIT_REVERSE,
        JOKER_IS_FIRST$RANK_REVERSE$SUIT_REVERSE
    }

    public static void main(String[] args) {
        //
        // Prepare deck and sample imitator.
        Deck deck = new Deck();
        UnitedImitator imitator = new UnitedImitator(
            new JokerImitator(),
            new RankImitator(10),
            new SuitImitator(Suit.CLUB),
            new SuitImitator(Suit.SPADE)
        );

        //
        // Make map of Comparators.
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
            tagToComparator.put(
                OrderType.JOKER_IS_LAST$RANK_NATURAL$SUIT_REVERSE,
                new JokersLastComparator() {
                    @Override
                    protected int compare(RankedCard rankedCard1, RankedCard rankedCard2) {
                        int rankCompared =
                            Comparator.comparingInt(RankedCard::getRank).compare(rankedCard1, rankedCard2);
                        if(rankCompared != 0) {
                            return rankCompared;
                        }

                        return
                            -1 * Comparator.comparing(RankedCard::getSuit).compare(rankedCard1, rankedCard2);
                    }
                }
            );
            tagToComparator.put(
                OrderType.JOKER_IS_FIRST$RANK_REVERSE$SUIT_REVERSE,
                new JokersFirstComparator() {
                    private final DefaultCardComparator comp = new DefaultCardComparator();

                    @Override
                    protected int compare(RankedCard rankedCard1, RankedCard rankedCard2) {
                        return comp.reversed().compare(rankedCard1, rankedCard2);
                    }
                }
            );
        }

        //
        // Make cards.
        nsr.loui.playingcards.contrib.SwitchableAutoSortedCards<OrderType> cards = new nsr.loui.playingcards.contrib.SwitchableAutoSortedCards<>(
            "Cards", Observer.STUB, tagToComparator, OrderType.DEFAULT
        );
        cards.divideFrom(deck, imitator);

        //
        // Switch and print.
        for(OrderType type: OrderType.values()) {
            cards.switchComparator(type);
            cards.printInfo();
        }
    }
}
