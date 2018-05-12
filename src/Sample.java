import card.Suit;
import card.imitator.individual.IndividualCardImitator;
import card.imitator.individual.JokerImitator;
import card.imitator.wild.RankImitator;
import card.imitator.wild.SuitImitator;
import cards.Trash;
import cards.ordered.Deck;
import cards.sorted.Hand;
import observer.PrintObserver;

class Sample {
    public static void main(String[] args) {
        //
        // Make a deck.
        Deck deck = new Deck();

        //
        // Remove unnecessary cards.
        Trash trash = Trash.makeTrash();
        trash.divideFrom(
            deck,
            new RankImitator(11),
            new RankImitator(12),
            new RankImitator(13),
            new SuitImitator(Suit.CLUB)
        );

        //
        // Shuffle deck and make a hand with observer.
        //deck.shuffle();
        Hand hand = new Hand("My Hand", new PrintObserver());
        hand.setOwner(deck);

        hand.pickFrom(deck, 10);
        deck.pickFrom(hand, 10);
/*
        //
        // Draw joker from deck.
        IndividualCardImitator jokerImitator = new JokerImitator();
        if(deck.include(jokerImitator)) {
            hand.pickFrom(deck, jokerImitator);
        }
        else {
            System.out.println("You already have two jokers.");
        }

        //
        // Print info.
        hand.printCards();
        System.out.println(hand.countCard());
        Suit.stream()
            .forEach(
                suit -> System.out.println(
                    String.format("%s: %d.", suit, hand.countCard(new SuitImitator(suit)))
                )
            )
        ;*/
    }
}
