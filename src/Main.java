import cards.ordered.Deck;
import cards.unordered.Hand;

class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Hand hand = new Hand("Hand", deck, 10);

        deck.printCards();
        hand.printCards();
    }
}
