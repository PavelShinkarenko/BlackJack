package services;

import domain.Card;
import domain.enums.Rank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private Deck deck;
    private List<Card> playerCards = new ArrayList<>();
    private List<Card> dealerCards = new ArrayList<>();
    private int playerPoints;
    private int dealerPoints;

    @Autowired
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Card> distribution() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cleanHands();
        for (int i = 0; i < 2; i++) {
            playerCards.add(deck.getCard());
            dealerCards.add(deck.getCard());
        }
        playerPoints = netPointsCount(playerCards);
        dealerPoints = netPointsCount(dealerCards);
        return deck.getCardsListOnHands();
    }

    public List<Card> hit() {
        playerCards.add(deck.getCard());
        playerPoints = netPointsCount(playerCards);
        return playerCards;
    }

    public List<Card> stand() {
        giveCardstoDealer();
        return dealerCards;
    }

    /*if return = (-1) - continue game, 1 - Push, 2.5 - Black Jack!*/
    public double checkAfterBet() {
        if (playerPoints == 21 && dealerPoints == 21) return 1d;
        else if (playerPoints == 21 && dealerPoints != 21) return 2.5d;
        return -1d;
    }

    /*if return = (-1) - continue game, 0 - lose, 1 - Push, 2 - win*/
    public int checkAfterHit() {
        if (playerPoints > 21) return 0;
        else if (playerPoints == 21) {
            giveCardstoDealer();
            if (dealerPoints == 21 && dealerCards.size() == 2) return 0;
            else if (dealerPoints == 21 ) return 1;
            else return 2;
        }
        return -1;
    }

    /*if return = 0 - lose, 1 - Push, 2 - win*/
    public int checkAfterStand() {
        if (playerPoints > dealerPoints || dealerPoints > 21) return 2;
        else if (playerPoints == dealerPoints) return 1;
        return 0;
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }

    private void giveCardstoDealer() {
        while (netPointsCount(dealerCards) <= 17) {
            System.out.println(netPointsCount(dealerCards));
            dealerCards.add(deck.getCard());
        }
        dealerPoints = netPointsCount(dealerCards);
    }

    public void cleanHands() {
        if (!playerCards.isEmpty() | !dealerCards.isEmpty()) {
            playerCards.clear();
            dealerCards.clear();
            deck.returnCardsToDeck();
        }
    }

    private int grossPointsCount(List<Card> cardsList) {
        int grossPoints = 0;
        for (int i = 0; i < cardsList.size(); i++) {
            grossPoints += cardsList.get(i).getRank().getValue();
        }
        return grossPoints;
    }

    public int netPointsCount(List<Card> cardsList) {
        int points = 0;
        int grossPoints = grossPointsCount(cardsList);
        int countAce = 0;
        for (int i = 0; i < cardsList.size(); i++) {
            if (cardsList.get(i).getRank() == Rank.ACE) countAce++;
        }
        if (countAce == 1 && grossPoints > 21 ) {
            points = grossPoints - 10;
        } else if (countAce == 2 && grossPoints > 32) {
            points = grossPoints - 20;
        } else if (countAce == 3 && grossPoints > 43) {
            points = grossPoints - 30;
        }else if (countAce == 4 && grossPoints > 54 ) {
            points = grossPoints - 40;
        }else points = grossPoints;
        return points;
    }


}
