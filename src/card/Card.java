package card;

public abstract class Card implements Comparable<Card> {
    public final boolean isJoker() {
        return this instanceof Joker;
    }
    public final boolean isRankedCard() {
        return this instanceof RankedCard;
    }
}
