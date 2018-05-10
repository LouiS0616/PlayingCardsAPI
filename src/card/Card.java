package card;

import card.imitator.individual.IndividualCardImitator;

public abstract class Card implements Comparable<Card> {
    public abstract IndividualCardImitator getImitator();
}
