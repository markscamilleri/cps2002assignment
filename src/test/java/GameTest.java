import org.junit.*;

import java.io.File;

/**
 * @author denise
 * @version 13/04/2017
 */
public class GameTest {

    @Before
    public void gameSetUp() throws Exception {
        Game.numOfPlayers = 5;
        Game.playerList = new Player[Game.numOfPlayers];
        Game.mapSize = 8;
        for (int i = 0; i < Game.numOfPlayers; i++) {
            Game.playerList[i] = new Player(Game.mapSize);
        }
        Game.generateHTMLFiles();
    }

    @Test
    public void testInputTooLittlePlayers() throws Exception {
        Assert.assertFalse(Game.setNumPlayers(1));
    }
    
    @Test
    public void testInput2Players() throws Exception {
        Assert.assertTrue(Game.setNumPlayers(2));
    }
    
    @Test
    public void testInput3Players() throws Exception {
        Assert.assertTrue(Game.setNumPlayers(3));
    }
    
    @Test
    public void testInput8Players() throws Exception {
        Assert.assertTrue(Game.setNumPlayers(8));
    }
    
    @Test
    public void testInputTooMuchPlayers() throws Exception {
        Assert.assertFalse(Game.setNumPlayers(9));
    }

    @Test
    public void testPlayerArray() throws Exception{
        Assert.assertEquals(Game.numOfPlayers, Game.playerList.length);
    }

    @Test
    public void testGeneratedHTMLFiles() throws Exception{
        int playerIndex = 1;

        for(int i=0; i<Game.numOfPlayers; i++, playerIndex++) {
            String filename = "map_player_" + playerIndex + ".html";
            String pathToFile = "src/gamefiles/"+filename;

            File file = new File(pathToFile);
            Assert.assertTrue(file.exists());
        }
    }
    
    @Test
    public void testGetColourForGrassTile() throws Exception {
        Assert.assertEquals("green", Game.getColour('g'));
    }
    
    @Test
    public void testGetColourForWaterTile() throws Exception {
        Assert.assertEquals("blue", Game.getColour('w'));
    }
    
    @Test
    public void testGetColourForTreasureTile() throws Exception {
        Assert.assertEquals("gold", Game.getColour('t'));
    }
    
    @Test
    public void testGetColourForUnknownOrInvalidTile() throws Exception {
        Assert.assertEquals("grey", Game.getColour(' '));
    }

}
