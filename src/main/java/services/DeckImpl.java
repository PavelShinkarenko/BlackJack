package services;

import domain.Card;
import domain.enums.Rank;
import domain.enums.Suit;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class DeckImpl implements Deck {
    private List<Card> cardsListInDeck;
    private List<Card> cardsListOnHands;


    public DeckImpl() {
        cardsListInDeck = new ArrayList<Card>();
        cardsListOnHands = new ArrayList<Card>();
        fillTheDeck();
    }


    public List<Card> getCardsListInDeck() {
        return cardsListInDeck;
    }

    public List<Card> getCardsListOnHands() {
        return cardsListOnHands;
    }


    public void fillTheDeck() {
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cardsListInDeck.add(new Card(rank, suit));
            }
        }
    }

    public Card getCard() {
        Random rand = new Random();
        Card card =  cardsListInDeck.remove(rand.nextInt(cardsListInDeck.size()));
        cardsListOnHands.add(card);
        return card;
    }

    public void returnCardsToDeck(){
        for (Card card : cardsListOnHands) {
            cardsListInDeck.add(card);
        }
        cardsListOnHands.clear();
    }
}
