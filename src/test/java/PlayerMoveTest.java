/**
 * @author denise
 * @version 12/04/2017.
 */
import org.junit.Assert;
import org.junit.Test;

public class PlayerMoveTest extends Player{

    @Test
    public void testPlayerMove() throws Exception{
        Assert.assertEquals('u', p.x = 0, p.y = 1);
        Assert.assertEquals('U', p.x = 0, p.y = 1);

        Assert.assertEquals('d', p.x = 0, p.y = 1);
        Assert.assertEquals('D', p.x = 0, p.y = 1);

        Assert.assertEquals('l', p.x = 0, p.y = 1);
        Assert.assertEquals('L', p.x = 0, p.y = 1);

        Assert.assertEquals('r', p.x = 0, p.y = 1);
        Assert.assertEquals('R', p.x = 0, p.y = 1);
    }
}
