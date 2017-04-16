/**
 * @author denise
 * @version 12/04/2017.
 */

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {
    Position pos;
    
    @Before
    public void setUp() throws Exception{
        pos = new Position(0,0);
    }
    
    @Test
    public void testPositionExists() throws Exception{
        Assert.assertNotNull(pos);
    }
    
    @Test
    public void testPositonXCorrect() throws Exception{
        Assert.assertEquals(0,pos.x);
    }
    
    @Test
    public void testPositonYCorrect() throws Exception{
        Assert.assertEquals(0,pos.y);
    }
}
