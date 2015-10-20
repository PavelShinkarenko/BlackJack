package services;


import domain.Player;

public interface PlayerService {
    int addPlayerAndGetId(String name);
    Player updatePlayerCashById(double cash, int id);
    Player getPlayerById(int id);
}
