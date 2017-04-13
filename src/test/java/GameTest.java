import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author denise
 * @version 13/04/2017
 */
public class GameTest extends Game{
    @Test
    public void testInputNumOfPlayers() throws Exception{
        Assert.assertFalse(setNumPlayers(1));
        Assert.assertTrue(setNumPlayers(2));
        Assert.assertTrue(setNumPlayers(3));
        Assert.assertTrue(setNumPlayers(8));
        Assert.assertFalse(setNumPlayers(9));
    }

    @Before
    public void gameSetUp() throws Exception{
        startGame();
    }

    @Test
    public void testPlayerArray() throws Exception{
        Assert.assertEquals(numOfPlayers, players.length);
    }
}
