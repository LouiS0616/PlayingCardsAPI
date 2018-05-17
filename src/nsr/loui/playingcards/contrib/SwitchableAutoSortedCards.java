package nsr.loui.playingcards.contrib;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.card.comparator.DefaultCardComparator;
import nsr.loui.playingcards.cards.AutoSortedCards;
import nsr.loui.playingcards.cards.BaseCards;
import nsr.loui.playingcards.cards.Deck;
import nsr.loui.playingcards.observer.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Experimental class to order-switchable auto sorted cards.
 * This class has several deck instances for each order-rule, so it spend memory.
 *
 * @param <T> Enum type act as tag to each comparators.
 */
public class SwitchableAutoSortedCards <T extends Enum> extends AutoSortedCards {
    /**
     * @param name this hands' name
     * @param deckName if you pass null, name related tag's that used instead.
     * @param observer observer. DON'T pass null, DO use stub instead.
     * @param tagToComparator map binds tag to comparator.
     * @param defaultTag to indicate default order mode. it must be included in above map.
     */
    public SwitchableAutoSortedCards(
        String name, String deckName, Observer observer, Map<T, CardComparator> tagToComparator, T defaultTag
    ) {
        super(name, observer, new DefaultCardComparator());

        int size             = tagToComparator.size();
        this.tags_           = new ArrayList<>(size);
        this.sortedCardsMap_ = new HashMap  <>(size);
        this.deckMap_        = new HashMap  <>(size);

        for(T tag: tagToComparator.keySet()) {
            tags_.add(tag);

            sortedCardsMap_.put(
                tag,
                new AutoSortedCards(name, Observer.STUB, tagToComparator.get(tag))
            );
            deckMap_.put(
                tag, new Deck(deckName == null ? tag.toString() + "_DECK" : deckName)
            );
        }

        if(tags_.contains(defaultTag)) {
            nowTag_ = defaultTag_ = defaultTag;
        }
        else {
            throw new RuntimeException("Default tag must be concluded in map-key tags.");
        }
    }

    @Override
    protected void update(Card card, BaseCards from) {
        super.update(card, from);

        for(T tag: tags_) {
            sortedCardsMap_.get(tag).pickFrom(
                deckMap_.get(tag), card.getIndividualImitator()
            );
        }
    }

    @Override
    public void printInfo() {
        sortedCardsMap_.get(nowTag_).printInfo();
    }

    /**
     * Switch to order bound to designate tag.
     * @param tag tag to set. if tag is not noticed in advance, this method do nothing other than print err.
     */
    public void switchComparator(T tag) {
        if(tags_.contains(tag)) {
            nowTag_ = tag;
        }
        else {
            System.err.println("You tried to set tag what not noticed in advance.");
        }
    }
    /**
     * Switch to default order.
     */
    public void switchDefaultComparator() {
        nowTag_ = defaultTag_;
    }


    //
    // Fields
    private T nowTag_;
    private final T defaultTag_;

    private final List<T> tags_;
    private final Map<T, AutoSortedCards> sortedCardsMap_;
    private final Map<T, Deck> deckMap_;
}
