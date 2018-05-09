package card;

import cards.Observer;
import util.ClosedIntRange;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RankedCard extends Card {
    public class InvalidRankException extends RuntimeException {
        InvalidRankException(String message) {
            super(message);
        }
    }
    private RankedCard(Suit suit, int rank) {
        if(!validRankRange.contains(rank)) {
            throw new InvalidRankException(
                String.format("Input rank(%d) is not in range %s.", rank, validRankRange)
            );
        }

        this.suit_ = suit;
        this.rank_ = rank;
    }

    @Override
    public String toString() {
        return String.format(
                "%s%s",
                suit_, rankToMark_.getOrDefault(rank_, String.valueOf(rank_))
        );
    }

    @Override
    public int compareTo(Card other) {
        if(other.isJoker()) {
            return -1;
        }

        return Comparator
            .comparingInt(RankedCard::getRank)
            .thenComparing(RankedCard::getSuit)
            .compare(this, (RankedCard)other)
        ;
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof RankedCard)) {
            return false;
        }

        RankedCard other = (RankedCard)obj;
        return other.rank_ == this.rank_
            && other.suit_ == this.suit_
        ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(
            this.rank_, this.suit_
        );
    }

    public enum Suit {
        SPADE, HART, DIAMOND, CLUB;

        @Override
        public String toString() {
            return suitToMark_.get(this);
        }

        static private final Map<Suit, String> suitToMark_ = new HashMap<>();
        static {
            suitToMark_.put(SPADE,   "♠");
            suitToMark_.put(HART,    "♥");
            suitToMark_.put(DIAMOND, "♦");
            suitToMark_.put(CLUB,    "♣");
        }
    }

    public Suit getSuit() {
        return suit_;
    }
    public int getRank() {
        return rank_;
    }

    //
    public static List<Card> generate() {
        if(defaultCardList_ != null) {
            return defaultCardList_;
        }

        defaultCardList_ = new ArrayList<>();
        for(int i: validRankRange) {
            for(Suit suit: Suit.values()) {
                defaultCardList_.add(new RankedCard(suit, i));
            }
        }

        return defaultCardList_;
    }
    private static List<Card> defaultCardList_ = null;

    //
    //
    private Suit suit_;
    private int rank_;

    private static final Map<Integer, String> rankToMark_ = new HashMap<>();
    static {
        rankToMark_.put( 1, "A");
        rankToMark_.put(11, "J");
        rankToMark_.put(12, "Q");
        rankToMark_.put(13, "K");
    }

    private static final ClosedIntRange validRankRange = new ClosedIntRange(1, 13);
}
