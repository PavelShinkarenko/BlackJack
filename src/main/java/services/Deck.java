package services;

import domain.Card;
import java.util.List;

public interface Deck {
    List<Card> getCardsListInDeck();
    List<Card> getCardsListOnHands();
    void fillTheDeck();
    Card getCard();
    void returnCardsToDeck();
}
