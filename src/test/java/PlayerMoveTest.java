/**
 * @author denise
 * @version 12/04/2017.
 */
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerMoveTest extends Player{

    /**
     *Testing moves: Up Down Left Right
     */
    @Before
    public void initialisePlayerPos() throws Exception{
        init(5);
        Assert.assertNotNull(p.x);
        Assert.assertNotNull(p.y);
    }

    @Test
    public void testPlayerMove_u() throws Exception{
        //up entering 'u';
        move('u');
        Assert.assertEquals(p.x, p.x++);
    }

    @Test
    public void testPlayerMove_U() throws Exception{
        //up entering 'U'
        move('U');
        Assert.assertEquals(p.y, p.y++);
    }

    @Test
    public void testPlayerMove_d() throws Exception{
        //down entering 'd'
        move('d');
        Assert.assertEquals(p.y, p.y--);
    }

    @Test
    public void testPlayerMove_D() throws Exception{
        //down entering 'D'
        move('D');
        Assert.assertEquals(p.y, p.y--);
    }

    @Test
    public void testPlayerMove_l() throws Exception{
        //left entering 'l'
        move('l');
        Assert.assertEquals(p.x, p.x--);
    }

    @Test
    public void testPlayerMove_L() throws Exception{
        //left entering 'L'
        move('L');
        Assert.assertEquals(p.x, p.x--);
    }

    @Test
    public void testPlayerMove_r() throws Exception{
        //right entering 'r'
        move('r');
        Assert.assertEquals(p.x, p.x++);
    }

    @Test
    public void testPlayerMove_R() throws Exception{
        //right entering 'R'
        move('R');
        Assert.assertEquals(p.x, p.x++);
    }
}
