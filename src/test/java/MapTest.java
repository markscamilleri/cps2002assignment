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
        map = new Map(8, 8);
    }
    
    @Test
    public void testMapIsCreated() throws Exception {
        Assert.assertNotNull(map);
    }
    
    @Test
    public void testGetMapSize() {
        int[] size = map.getMapSize();
        
        Assert.assertTrue(size[0] == 8 && size[1] == 8);
    }
    
    @Test
    public void testGetTileTypeCorrect() {
        char type = map.getTileType(4, 4);
        
        Assert.assertTrue("Tile is not a grass, water or treasure type", type == 'g' || type == 'w' || type == 't');
    }
    
    @Test
    public void testGetTileTypeXZero() {
        char type = map.getTileType(0, 4);
        Assert.assertTrue("Tile is not a grass, water or treasure type", type == 'g' || type == 'w' || type == 't');
        
    }
    
    @Test
    public void testGetTileTypeYZero() {
        char type = map.getTileType(4, 0);
        Assert.assertTrue("Tile is not a grass, water or treasure type", type == 'g' || type == 'w' || type == 't');
    }
    
    @Test
    public void testGetTileTypeXYZero() {
        char type = map.getTileType(0, 0);
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
    public void testGetTileXYNegative() {
        map.getTileType(-1, -1);
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