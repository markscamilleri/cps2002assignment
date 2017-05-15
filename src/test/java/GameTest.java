import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;


/**
 * @author denise
 * @version 13/04/2017
 */
public class GameTest {
    private int NUMBER_OF_PLAYERS;
    private int MAP_SIZE;
    
    private Team[] teams;
    private Player[] players;
    private Map map;
    
    private final InputStream stdin = System.in;
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();

    
    
    @Before
    public void gameSetUp() throws Exception {
        NUMBER_OF_PLAYERS = 5;
        MAP_SIZE = 8;
        map = new Map(MAP_SIZE, MAP_SIZE);
        Game.map = map;
        
        teams = Game.createTeamList(NUMBER_OF_PLAYERS, MAP_SIZE);
        players = Game.createPlayerList(NUMBER_OF_PLAYERS, MAP_SIZE, teams);
    
        System.setOut(new PrintStream(output));
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
    public void testGeneratedPlayerHTMLFiles() throws Exception{
        Game.generateHTMLFiles(teams, players);
        
        for(int i=0; i < NUMBER_OF_PLAYERS; i++) {
            String filename = "map_player_" + (i+1) + ".html";
            String pathToFile = "gamefiles/"+filename;
            
            File file = new File(pathToFile);
            Assert.assertTrue(file.exists());
        }
    }
    
    @Test
    public void testGeneratedTeamHTMLFiles() throws Exception{
        teams = Game.createTeamList(NUMBER_OF_PLAYERS/2, MAP_SIZE);
        players = Game.createPlayerList(NUMBER_OF_PLAYERS, MAP_SIZE, teams);
    
        Game.generateHTMLFiles(teams, players);
        
        for(int i=0; i < NUMBER_OF_PLAYERS/2; i++) {
            String filename = "map_team_" + (i+1) + ".html";
            String pathToFile = "gamefiles/"+filename;
            
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
        Player[] test = Game.createPlayerList(NUMBER_OF_PLAYERS, MAP_SIZE, teams);
        
        Assert.assertEquals(NUMBER_OF_PLAYERS, test.length);
    
        for (Player player : test) {
            Assert.assertNotNull(player);
        }
    }

    @Test
    public void testCreateTeamList() throws Exception {
        Team[] test = Game.createTeamList(NUMBER_OF_PLAYERS, MAP_SIZE);
    
        Assert.assertEquals(NUMBER_OF_PLAYERS, test.length);
    
        for (Team team : test) {
            Assert.assertNotNull(team);
        }
    }
    
    
    
    @After
    public void tearDown() throws Exception {
        System.setIn(stdin);
        System.setOut(stdout);
    }
}
