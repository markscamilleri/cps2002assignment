import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;


/**
 * @author denise
 * @version 13/04/2017
 */
public class GameTest {
    private int NUMBER_OF_PLAYERS;
    private int MAP_SIZE;
    
    private Player[] players;
    private Map map;
    
    @Before
    public void gameSetUp() throws Exception {
        NUMBER_OF_PLAYERS = 5;
        MAP_SIZE = 8;
        map = new Map(MAP_SIZE, MAP_SIZE);
        Game.map = map;
        players = Game.createPlayerList(NUMBER_OF_PLAYERS, MAP_SIZE);
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
    public void testGeneratedHTMLFiles() throws Exception{
        Game.generateHTMLFiles(players);
        
        for(int i=0; i < NUMBER_OF_PLAYERS; i++) {
            String filename = "map_player_" + (i+1) + ".html";
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
    
    @Test
    public void testGetLandingTile() throws Exception {
        for (Player player : players) {
            Assert.assertEquals(map.getTileType(player.position.x, player.position.y), Game.getLandingTile(player, map));
        }
    }
    
    @Test
    public void testCreatePlayerList() throws Exception {
       
        Player[] test = Game.createPlayerList(NUMBER_OF_PLAYERS, MAP_SIZE);
        
        Assert.assertEquals(NUMBER_OF_PLAYERS, test.length);
    
        for (Player player : test) {
            Assert.assertNotNull(player);
        }
    }
}
