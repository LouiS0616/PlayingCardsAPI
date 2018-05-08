package card;

public abstract class Card implements Comparable<Card> {
    public abstract boolean isJoker();
    public abstract boolean isRankedCard();
}
