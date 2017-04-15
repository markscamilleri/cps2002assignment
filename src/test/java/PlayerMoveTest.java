/**
 * @author denise
 * @version 12/04/2017.
 */
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.management.counter.perf.PerfLongArrayCounter;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PlayerMoveTest{
    private Player player;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    /**
     *Testing moves: Up Down Left Right
     */
    @Before
    public void initialisePlayerPos() throws Exception{
        System.setOut(new PrintStream(outContent));
        player = new Player();
        player.init(5);
        Assert.assertNotNull(player.p.x);
        Assert.assertNotNull(player.p.y);
    }


    @Test
    public void testPlayerMove_u() throws Exception{
        //up entering 'u';
        player.move('u');
        Assert.assertEquals(player.p.x, player.p.x++);
        Assert.assertEquals("Moved UP", outContent.toString());
    }

    @Test
    public void testPlayerMove_U() throws Exception{
        //up entering 'U'
        player.move('U');
        Assert.assertEquals(player.p.y, player.p.y++);
        Assert.assertEquals("Moved UP", outContent.toString());
    }

    @Test
    public void testPlayerMove_d() throws Exception{
        //down entering 'd'
        player.move('d');
        Assert.assertEquals(player.p.y, player.p.y--);
        Assert.assertEquals("Moved DOWN", outContent.toString());
    }

    @Test
    public void testPlayerMove_D() throws Exception{
        //down entering 'D'
        player.move('D');
        Assert.assertEquals(player.p.y, player.p.y--);
        Assert.assertEquals("Moved DOWN", outContent.toString());
    }

    @Test
    public void testPlayerMove_l() throws Exception{
        //left entering 'l'
        player.move('l');
        Assert.assertEquals(player.p.x, player.p.x--);
        Assert.assertEquals("Moved LEFT", outContent.toString());
    }

    @Test
    public void testPlayerMove_L() throws Exception{
        //left entering 'L'
        player.move('L');
        Assert.assertEquals(player.p.x, player.p.x--);
        Assert.assertEquals("Moved LEFT", outContent.toString());
    }

    @Test
    public void testPlayerMove_r() throws Exception{
        //right entering 'r'
        player.move('r');
        Assert.assertEquals(player.p.x, player.p.x++);
        Assert.assertEquals("Moved RIGHT", outContent.toString());
    }

    @Test
    public void testPlayerMove_R() throws Exception{
        //right entering 'R'
        player.move('R');
        Assert.assertEquals(player.p.x, player.p.x++);
        Assert.assertEquals("Moved RIGHT", outContent.toString());
    }

    @Test
    public void testInvalidMove() throws Exception{
        player.move('w');
        Assert.assertEquals("invalid move", outContent.toString());
    }

    @After
    public void cleanStream() throws Exception{
        System.setOut(null);
    }
}
