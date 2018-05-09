package card;

public class Joker extends Card {
    private static int serialId_ = 0;
    private final int id_;

    public Joker() {
        this.id_ = ++serialId_;
    }

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
            Joker otherJoker = (Joker)other;
            return Integer.compare(
                this.id_, otherJoker.id_
            );
        }

        return 1;
    }
}
