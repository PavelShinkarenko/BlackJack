package services;


import dao.PlayerDAO;
import domain.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerDAO playerDAO;

    @Autowired
    public void setPlayerDAO(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    @Override
    public int addPlayerAndGetId(String name){
        Player player;
        List<Player> playersList = playerDAO.getPlayerByName(name);
        if (playersList.size()==0) {
            player = new Player(name);
            playerDAO.addOrUpdatePlayer(player);
            player = playerDAO.getPlayerByName(name).get(0);
        }
        else {
            player = playersList.get(0);
        }
        return player.getId();
    }

    @Override
    public Player updatePlayerCashById(double cash, int id){
        Player player = playerDAO.getPlayerById(id);
        player.setCash(cash);
        playerDAO.addOrUpdatePlayer(player);
        return player;
    }

    @Override
    public Player getPlayerById(int id) {
        return playerDAO.getPlayerById(id);
    }

}
