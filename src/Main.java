import card.imitator.CardImitator;
import card.imitator.JokerImitator;
import cards.ordered.Deck;
import cards.unordered.Hand;
import observer.PrintObserver;

class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Hand hand = new Hand(
            "Hand", new PrintObserver(), deck, 0
        );
        hand.printCards();

        CardImitator imitator = new JokerImitator();
        hand.pickFrom(deck, imitator);
        hand.pickFrom(deck, imitator);

        deck.printCards();
        hand.printCards();
    }
}
