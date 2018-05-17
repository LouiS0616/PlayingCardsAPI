package nsr.loui.playingcards.contrib;

import nsr.loui.playingcards.card.Card;
import nsr.loui.playingcards.card.comparator.CardComparator;
import nsr.loui.playingcards.card.comparator.DefaultCardComparator;
import nsr.loui.playingcards.cards.AutoSortedCards;
import nsr.loui.playingcards.cards.BaseCards;
import nsr.loui.playingcards.cards.Deck;
import nsr.loui.playingcards.cards.UnorderedCards;
import nsr.loui.playingcards.observer.Observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SwitchableAutoSortedCards <T extends Enum> extends AutoSortedCards {
    public SwitchableAutoSortedCards(
        String name, Observer observer, Map<T, CardComparator> tagToComparator, T defaultTag
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
                tag, new Deck(tag.toString() + "_DECK")
            );
        }

        if(tags_.contains(defaultTag)) {
            nowTag_ = defaultTag;
        }
        else {
            throw new RuntimeException();
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

    public void switchComparator(T tag) {
        nowTag_ = tag;
    }


    //
    // Fields
    private T nowTag_;

    private final List<T> tags_;
    private final Map<T, AutoSortedCards> sortedCardsMap_;
    private final Map<T, Deck> deckMap_;
}
