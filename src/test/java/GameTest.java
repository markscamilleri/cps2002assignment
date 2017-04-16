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
        g.numOfPlayers = 5;
        g.players = new Player[g.numOfPlayers];
        g.mapSize = 8;
        for (int i = 0; i < g.numOfPlayers; i++) {
            g.players[i] = new Player();
            g.players[i].init(g.mapSize);
        }
        g.generateHTMLFiles();
    }

    @Test
    public void testInputTooLittlePlayers() throws Exception {
        Assert.assertFalse(g.setNumPlayers(1));
    }
    
    @Test
    public void testInput2Players() throws Exception {
        Assert.assertTrue(g.setNumPlayers(2));
    }
    
    @Test
    public void testInput3Players() throws Exception {
        Assert.assertTrue(g.setNumPlayers(3));
    }
    
    @Test
    public void testInput8Players() throws Exception {
        Assert.assertTrue(g.setNumPlayers(8));
    }
    
    @Test
    public void testInputTooMuchPlayers() throws Exception {
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
