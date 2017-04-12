import org.junit.Assert;
import org.junit.Test;

/**
 * @author denise
 * @version 12/04/2017
 */
public class GameTest extends Game{
    @Test
    public void testNumOfPlayers() throws Exception{
        Assert.assertFalse(setNumPlayers(1));
        Assert.assertTrue(setNumPlayers(2));
        Assert.assertTrue(setNumPlayers(3));
        Assert.assertTrue(setNumPlayers(8));
        Assert.assertFalse(setNumPlayers(9));
    }
}
