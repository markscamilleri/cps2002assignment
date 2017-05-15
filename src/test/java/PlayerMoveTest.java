/**
 * @author denise
 * @version 12/04/2017.
 */
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PlayerMoveTest{
    private Player player;
    private Map map;
    private ByteArrayOutputStream outContent;
    private final int MAP_SIZE = 5;
    
    /**
     *Testing moves: Up Down Left Right
     */
    @Before
    public void initialisePlayerPos() throws Exception{
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        map = new Map(MAP_SIZE, MAP_SIZE);
        Game.map = map;
        player = new Player(MAP_SIZE);
    }
    
    @Test
    public void testPlayerMove_u() throws Exception{
        //up entering 'u';
        Position pos = new Position(player.position.x, player.position.y+1);
        
        if(player.move('u')) {
            Assert.assertEquals(pos.y, player.position.y);
            Assert.assertEquals("Moved UP", outContent.toString());
        } else {
            Assert.assertEquals(pos.y-1, player.position.y);
            Assert.assertEquals("Invalid move\nYou're at the edge of the map", outContent.toString());
        }
    }

    @Test
    public void testPlayerMove_d() throws Exception{
        //down entering 'd'
        Position pos = new Position(player.position.x, player.position.y-1);
    
        if(player.move('d')) {
            Assert.assertEquals(pos.y, player.position.y);
            Assert.assertEquals("Moved DOWN", outContent.toString());
        } else {
            Assert.assertEquals(pos.y+1, player.position.y);
            Assert.assertEquals("Invalid move\nYou're at the edge of the map", outContent.toString());
        }
    }

    @Test
    public void testPlayerMove_l() throws Exception{
        Position pos = new Position(player.position.x-1, player.position.y);
    
        //entering l for left
        if(player.move('l')) {
            Assert.assertEquals(pos.x, player.position.x);
            Assert.assertEquals("Moved LEFT", outContent.toString());
        } else {
            Assert.assertEquals(pos.x+1, player.position.x);
            Assert.assertEquals("Invalid move\nYou're at the edge of the map", outContent.toString());
        }
    }

    @Test
    public void testPlayerMove_r() throws Exception{
        Position pos = new Position(player.position.x+1, player.position.y);
    
        //entering r for right
        if(player.move('r')) {
            Assert.assertEquals(pos.x, player.position.x);
            Assert.assertEquals("Moved RIGHT", outContent.toString());
        } else {
            Assert.assertEquals(pos.x-1, player.position.x);
            Assert.assertEquals("Invalid move\nYou're at the edge of the map", outContent.toString());
        }
    }
    @Test
    public void testInvalidMove() throws Exception{
        player.move('w');
        Assert.assertEquals("Invalid move", outContent.toString());
    }

    @Before
    public void setUpMap() throws Exception{
        map = new Map(5,5);
    }

    @Test
    public void testSetPositionTrue() throws Exception{
        Position p = new Position(2,3);
        Assert.assertTrue(player.setPosition(p, map));
    }

    @Test
    public void testSetPositionFalse() throws Exception{
        Position p = new Position(-1,2);
        Assert.assertFalse(player.setPosition(p, map));
    }
    
    @Test
    public void testGetStartXPosition() throws Exception{
        Position pos = player.getStartPosition();
        
        Assert.assertTrue(pos.x >= 0 && pos.x < MAP_SIZE);
    }
    
    @Test
    public void testGetStartYosition() throws Exception{
        Position pos = player.getStartPosition();
        
        Assert.assertTrue(pos.y >= 0 && pos.y < MAP_SIZE);
    }
    
    @After
    public void cleanStream() throws Exception{
        System.setOut(null);
    }
}
