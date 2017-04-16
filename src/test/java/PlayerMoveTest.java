/**
 * @author denise
 * @version 12/04/2017.
 */
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PlayerMoveTest{
    private Player player;
    private Map map;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    /**
     *Testing moves: Up Down Left Right
     */
    @Before
    public void initialisePlayerPos() throws Exception{
        System.setOut(new PrintStream(outContent));
        player = new Player();
        player.init(5);
        Assert.assertNotNull(player.position.x);
        Assert.assertNotNull(player.position.y);
    }


    @Test
    public void testPlayerMove_u() throws Exception{
        //up entering 'u';
        player.move('u');
        Assert.assertEquals(player.position.x, player.position.x++);
        Assert.assertEquals("Moved UP", outContent.toString());
    }

    @Test
    public void testPlayerMove_U() throws Exception{
        //up entering 'U'
        player.move('U');
        Assert.assertEquals(player.position.y, player.position.y++);
        Assert.assertEquals("Moved UP", outContent.toString());
    }

    @Test
    public void testPlayerMove_d() throws Exception{
        //down entering 'd'
        player.move('d');
        Assert.assertEquals(player.position.y, player.position.y--);
        Assert.assertEquals("Moved DOWN", outContent.toString());
    }

    @Test
    public void testPlayerMove_D() throws Exception{
        //down entering 'D'
        player.move('D');
        Assert.assertEquals(player.position.y, player.position.y--);
        Assert.assertEquals("Moved DOWN", outContent.toString());
    }

    @Test
    public void testPlayerMove_l() throws Exception{
        //left entering 'l'
        player.move('l');
        Assert.assertEquals(player.position.x, player.position.x--);
        Assert.assertEquals("Moved LEFT", outContent.toString());
    }

    @Test
    public void testPlayerMove_L() throws Exception{
        //left entering 'L'
        player.move('L');
        Assert.assertEquals(player.position.x, player.position.x--);
        Assert.assertEquals("Moved LEFT", outContent.toString());
    }

    @Test
    public void testPlayerMove_r() throws Exception{
        //right entering 'r'
        player.move('r');
        Assert.assertEquals(player.position.x, player.position.x++);
        Assert.assertEquals("Moved RIGHT", outContent.toString());
    }

    @Test
    public void testPlayerMove_R() throws Exception{
        //right entering 'R'
        player.move('R');
        Assert.assertEquals(player.position.x, player.position.x++);
        Assert.assertEquals("Moved RIGHT", outContent.toString());
    }

    @Test
    public void testInvalidMove() throws Exception{
        player.move('w');
        Assert.assertEquals("invalid move", outContent.toString());
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

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetPositionFalse() throws Exception{
        Position p = new Position(-1,2);
        Assert.assertFalse(player.setPosition(p, map));
        Assert.assertEquals("x-coordinate is less than 0", outContent.toString());
    }

    @After
    public void cleanStream() throws Exception{
        System.setOut(null);
    }
}
