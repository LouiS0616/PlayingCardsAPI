import card.Suit;
import card.imitator.individual.IndividualCardImitator;
import card.imitator.individual.JokerImitator;
import card.imitator.wild.SuitImitator;
import cards.ordered.Deck;
import cards.sorted.Hand;
import observer.PrintObserver;

class Main {
    public static void main(String[] args) {
        Deck deck = Deck.makeDeck();
        deck.shuffle();

        Hand clubs = new Hand("Clubs", new PrintObserver(), deck, 0);
        clubs.divideFrom(deck, new SuitImitator(Suit.CLUB));
        clubs.printCards();

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
