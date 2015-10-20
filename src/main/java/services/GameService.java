package services;


import domain.Card;

import java.util.List;

public interface GameService {
    List<Card> distribution();
    List<Card> hit();
    List<Card> stand();
    double checkAfterBet();
    int checkAfterHit();
    int checkAfterStand();
    List<Card> getDealerCards();
    int netPointsCount(List<Card> cardsList);
    void cleanHands();



}
