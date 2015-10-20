package dao;


import domain.Player;

import java.util.List;

public interface PlayerDAO {
     void addOrUpdatePlayer(Player player);
     List<Player> getPlayerByName(String name);
     Player getPlayerById(int id);

}
