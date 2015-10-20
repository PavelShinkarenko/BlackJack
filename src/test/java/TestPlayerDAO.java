import dao.PlayerDAO;
import domain.Player;
import org.hibernate.HibernateException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateJdbcException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@Transactional(rollbackFor = Exception.class)
public class TestPlayerDAO {
    @Autowired
    private PlayerDAO playerDAO;
    @Autowired
    private HibernateTemplate template;
    private Player player;


    @Test
    public void testGetPlayerByName() throws Exception {
        player = new Player("Alex");
        template.save(player);
        List<Player> playersFromDB = playerDAO.getPlayerByName("Alex");
        Assert.assertEquals("Fail", player.getName(), playersFromDB.get(0).getName());
    }

    @Test
    public void testGetPlayerById() throws Exception {
        template.save((new Player("Jack")));
        player = playerDAO.getPlayerByName("Jack").get(0);
        Player newPlayers = playerDAO.getPlayerById(player.getId());
        Assert.assertEquals("Fail", player.getId(), newPlayers.getId());
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        template.save(new Player("Kurt"));
        player = playerDAO.getPlayerByName("Kurt").get(0);
        player.setCash(33d);
        player.setName("Darth Vader");
        playerDAO.addOrUpdatePlayer(player);
        Player changedPlayer = playerDAO.getPlayerByName("Darth Vader").get(0);
        Assert.assertEquals("Fail", player.getId(), changedPlayer.getId());
    }
    @Test
    public void testAddPlayer() throws Exception {
        List<Player> players =  template.getSessionFactory().getCurrentSession().createCriteria(Player.class).list();
        playerDAO.addOrUpdatePlayer(new Player("Tom"));
        List<Player> newPlayers = template.getSessionFactory().getCurrentSession().createCriteria(Player.class).list();
        Assert.assertTrue("Fail", players.size() + 1 == newPlayers.size());
    }



}
