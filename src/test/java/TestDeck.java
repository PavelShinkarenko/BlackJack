import org.junit.After;
import services.Deck;
import services.DeckImpl;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



@ContextConfiguration(locations = "/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
public class TestDeck {

    @Autowired
    private Deck deck;

    @After
    public void Teardown(){
        deck.returnCardsToDeck();
    }


    @Test
    public void testFillTheDeck(){
        int deckSize = deck.getCardsListInDeck().size();
        Assert.assertTrue("Fail", deckSize == 52);
    }

    @Test
    public void testGetCardNotNull(){
        Assert.assertNotNull("Fail", deck.getCard());
    }

    @Test
    public void testGetCard(){
        deck.getCard();
        Assert.assertTrue("Fail", deck.getCardsListOnHands().size() == 1);
    }

    @Test
    public void testReturnCardsToDeck(){
        deck.getCard();
        deck.returnCardsToDeck();
        Assert.assertTrue("Fail",  deck.getCardsListOnHands().size() == 0);
    }
}
