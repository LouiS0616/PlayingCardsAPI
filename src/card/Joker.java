package card;

import java.util.Objects;

public class Joker extends Card {
    private static int serialId_ = 0;
    private final int id_;

    public Joker() {
        this.id_ = ++serialId_;
    }

    //
    // Basically methods
    @Override
    public String toString() {
        return "Joker";
    }

    @Override
    public int compareTo(Card other) {
        if(other instanceof Joker) {
            Joker otherJoker = (Joker)other;
            return Integer.compare(
                this.id_, otherJoker.id_
            );
        }

        return 1;
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(!(obj instanceof Joker)) {
            return false;
        }

        Joker other = (Joker)obj;
        return this.id_ == other.id_;
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(id_);
    }
}
