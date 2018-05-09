package card;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum Suit {
    SPADE, HART, DIAMOND, CLUB;

    public static Stream<Suit> stream() {
        return Arrays.stream(Suit.values());
    }

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
