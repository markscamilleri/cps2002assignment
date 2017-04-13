import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author mark
 * @version 13/04/17.
 */
public class MapTest {
    
    private Map map;
    
    @Before
    public void setUp() throws Exception {
        map = new Map(8,8);
    }
    
    @Test
    public void testMapIsCreated() throws Exception {
        Assert.assertNotNull(map);
    }
    
    @Test
    public void testGetMapSize(){
        Assert.assertArrayEquals(new int[]{8,8}, map.getMapSize());
    }
    
    @Test
    public void testGetTileTypeCorrect(){
        char type = map.getTileType(4,4);
        
        Assert.assertTrue("Tile is not a grass, water or treasure type", type == 'g' || type == 'w' || type == 't');
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileTypeXNegative() {
        map.getTileType(-1, 4);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileTypeYNegative() {
        map.getTileType(4, -1);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileTypeXYNegative() {
        map.getTileType(-1, -1);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileTypeXZero() {
        map.getTileType(0, 4);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileTypeYZero() {
        map.getTileType(4, 0);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileTypeXYZero() {
        map.getTileType(0, 0);
    }
    
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileTypeXTooBig() {
        map.getTileType(51, 4);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileTypeYTooBig() {
        map.getTileType(4, 51);
    }
    
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetTileTypeXYTooBig() {
        map.getTileType(51, 51);
    }
}