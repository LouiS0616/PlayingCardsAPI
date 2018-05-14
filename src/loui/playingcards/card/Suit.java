package loui.playingcards.card;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This enum means playing cards' suit.
 */
public enum Suit {
    SPADE, HEART, DIAMOND, CLUB;

    /**
     * @return stream of values.
     */
    public static Stream<Suit> stream() {
        return Arrays.stream(Suit.values());
    }

    /**
     * @return replaced to following letters; ♠, ♥, ♦ and ♣.
     */
    @Override
    public String toString() {
        return suitToMark_.get(this);
    }
    static private final Map<Suit, String> suitToMark_ = new HashMap<>();
    static {
        suitToMark_.put(SPADE,   "♠");
        suitToMark_.put(HEART,   "♥");
        suitToMark_.put(DIAMOND, "♦");
        suitToMark_.put(CLUB,    "♣");
    }
}
