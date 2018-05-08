import cards.ordered.Deck;
import cards.unordered.Hand;

class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();

        Hand hand = new Hand(deck, 10);

        System.out.println(deck);
        System.out.println(hand);
    }
}
