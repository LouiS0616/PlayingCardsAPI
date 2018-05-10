import card.Suit;
import card.imitator.wild.RankImitator;
import card.imitator.wild.SuitImitator;
import card.imitator.wild.WildJokerImitator;
import cards.ordered.Deck;
import cards.sorted.Hand;
import observer.PrintObserver;

class Main {
    public static void main(String[] args) {
        Deck deck = Deck.makeDeck();
        deck.shuffle();

        Hand hand = new Hand("Hand", new PrintObserver(), deck, 0);
        hand.divideFrom(deck, new SuitImitator(Suit.CLUB));
        hand.divideFrom(deck, new RankImitator(13));
        hand.divideFrom(deck, new WildJokerImitator());
        hand.printCards();



        /*
        Hand hand = new Hand(
            "Hand", new PrintObserver(), deck, 10
        );
        hand.printCards();

        IndividualCardImitator imitator = new JokerImitator();
        if(deck.include(imitator)) {
            hand.pickFrom(deck, imitator);
        }
        else {
            System.out.println("Already you have two jokers.");
        }

        hand.printCards();
        */
    }
}
