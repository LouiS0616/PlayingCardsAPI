import cards.ordered.Deck;
import cards.unordered.Hand;
import observer.PrintObserver;

class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Hand hand = new Hand(
                "Hand", new PrintObserver(), deck, 10
        );
        hand.printCards();
    }
}
