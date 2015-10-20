import domain.Card;
import domain.enums.Rank;
import domain.enums.Suit;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.GameService;
import services.GameServiceImpl;

import java.util.ArrayList;
import java.util.List;

@ContextConfiguration("/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class TestGameService {

    @Autowired
    private GameService gameService;

    List<Card> cards;


    @Before
    public void setUp() {
        cards = gameService.distribution();

    }

    @Test
    public void testDistribution() {
        Assert.assertTrue(cards.size() == 4);
    }

    @Test
    public void testDistributionNotNull() {
        Assert.assertNotNull(cards);
    }

    @Test
    public void testHit() {
        Assert.assertTrue(gameService.hit().size() > 2);
    }

    @Test
    public void testHitNotNull() {
        Assert.assertNotNull(gameService.hit().size());
    }

    @Test
    public void testStand() {
        Assert.assertTrue(gameService.stand().size() >= 2);
    }

    @Test
    public void testStandNotNull() {
        Assert.assertNotNull(gameService.stand());
    }

    @Test
    public void testGetDealerCards() {
        Assert.assertTrue(gameService.getDealerCards().size() >= 2);
    }

    @Test
    public void testGetDealerCardsNotNull() {
        Assert.assertNotNull(gameService.getDealerCards());
    }

    @Test
    public void testNetPointsCount() {
        cards = new ArrayList<Card>();
        cards.add(new Card(Rank.ACE, Suit.CLUBS));
        cards.add(new Card(Rank.KING, Suit.CLUBS));
        int points = gameService.netPointsCount(cards);
        Assert.assertTrue(points == 21);
    }

    @Test
    public void testNetPointsCountWithTwoAce() {
        cards = new ArrayList<Card>();
        cards.add(new Card(Rank.ACE, Suit.CLUBS));
        cards.add(new Card(Rank.ACE, Suit.DIAMONDS));
        cards.add(new Card(Rank.FIVE, Suit.CLUBS));
        cards.add(new Card(Rank.SIX, Suit.DIAMONDS));
        int points = gameService.netPointsCount(cards);
        Assert.assertTrue(points == 13);
    }

    @Test
    public void testCleanHands() {
        gameService.cleanHands();
        Assert.assertTrue(gameService.getDealerCards().isEmpty());
    }


}
