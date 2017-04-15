import org.junit.*;

import java.io.File;

/**
 * @author denise
 * @version 13/04/2017
 */
public class GameTest {
    private Game g;

    @Before
    public void gameSetUp() throws Exception {
        g = new Game();
        g.numOfPlayers = 5;
        g.generateHTMLFiles();
    }

    @Test
    public void testInputNumOfPlayers() throws Exception{
        Assert.assertFalse(g.setNumPlayers(1));
        Assert.assertTrue(g.setNumPlayers(2));
        Assert.assertTrue(g.setNumPlayers(3));
        Assert.assertTrue(g.setNumPlayers(8));
        Assert.assertFalse(g.setNumPlayers(9));
    }

    @Test
    public void testPlayerArray() throws Exception{
        Assert.assertEquals(g.numOfPlayers, g.players.length);
    }

    @Test
    public void testGeneratedHTMLFiles() throws Exception{
        int playerIndex = 1;

        for(int i=0; i<g.numOfPlayers; i++, playerIndex++) {
            String filename = "map_player_" + playerIndex + ".html";
            String pathToFile = "src/gamefiles/"+filename;

            File file = new File(pathToFile);
            Assert.assertTrue(file.exists());
        }
    }
}
