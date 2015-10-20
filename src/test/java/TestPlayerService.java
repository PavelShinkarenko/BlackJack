import domain.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.PlayerService;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class TestPlayerService {

    @Autowired
    private PlayerService playerService;
    private int id;


    @Test
    public void testAddPlayerAndGetId() {
        id = playerService.addPlayerAndGetId("Pavel");
        Player player = playerService.getPlayerById(id);
        Assert.assertEquals("Fail", "Pavel", player.getName());
    }

    @Test
    public void testGetPlayerById() {
        id = playerService.addPlayerAndGetId("Jora");
        Player player = playerService.getPlayerById(id);
        Assert.assertTrue("Fail", player.getId() == id);
    }

    @Test
    public void testUpdatePlayerCashById() {
        id = playerService.addPlayerAndGetId("Wolf");
        playerService.updatePlayerCashById(33d, id);
        Player player = playerService.getPlayerById(id);
        Assert.assertTrue("Fail", player.getCash() == 33d);
    }


}
