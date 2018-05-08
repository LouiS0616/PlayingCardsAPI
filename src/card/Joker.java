package card;

public class Joker extends Card {
    @Override
    public boolean isRankedCard() {
        return false;
    }
    @Override
    public boolean isJoker() {
        return true;
    }

    @Override
    public String toString() {
        return "Joker";
    }

    @Override
    public int compareTo(Card other) {
        if(other.isJoker()) {
            return 0;
        }

        return 1;
    }
}
