import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

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

    @Test
    public void testGeneratedHTMLFiles() throws Exception{
        int playerIndex = 1;

        for(int i=0; i<numOfPlayers; i++, playerIndex++) {
            String filename = "map_player_" + playerIndex + ".html";
            String pathToFile = "src/gamefiles/"+filename;

            File file = new File(pathToFile);
            Assert.assertTrue(file.exists());
        }
    }
}
