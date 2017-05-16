import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Denise Buttigieg
 * @version 12/05/2017.
 */
public class SafeMapTest {
    private SafeMap safeMap;

    @Before
    public void setUp() throws Exception {
        safeMap = new SafeMap(8, 8);
    }

    @Test
    public void testMapIsCreated() throws Exception {
        Assert.assertNotNull(safeMap);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapXTooLarge() throws Exception {
        safeMap = new SafeMap(51, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapXTooSmall() throws Exception {
        safeMap = new SafeMap(4, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapYTooLarge() throws Exception {
        safeMap = new SafeMap(8, 51);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapYTooSmall() throws Exception {
        safeMap = new SafeMap(8, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapXYTooLarge() throws Exception {
        safeMap = new SafeMap(51, 51);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMapXYTooSmall() throws Exception {
        safeMap = new SafeMap(4, 4);
    }


    @Test
    public void testGetMapSize() {
        int[] size = safeMap.getMapSize();

        Assert.assertTrue(size[0] == 8 && size[1] == 8);
    }

    @Test
    public void testGetTileTypeCorrect() {
        char type = safeMap.getTileType(4, 4);

        Assert.assertTrue("Tile is not a grass, water or treasure type", type == 'g' || type == 'w' || type == 't');
    }

    @Test
    public void testGetTileTypeXZero() {
        char type = safeMap.getTileType(0, 4);
        Assert.assertTrue("Tile is not a grass, water or treasure type", type == 'g' || type == 'w' || type == 't');

    }

    @Test
    public void testGetTileTypeYZero() {
        char type = safeMap.getTileType(4, 0);
        Assert.assertTrue("Tile is not a grass, water or treasure type", type == 'g' || type == 'w' || type == 't');
    }

    @Test
    public void testGetTileTypeXYZero() {
        char type = safeMap.getTileType(0, 0);
        Assert.assertTrue("Tile is not a grass, water or treasure type", type == 'g' || type == 'w' || type == 't');
    }

    @Test
    public void testGetTileTypeXNegative() {
        char type = safeMap.getTileType(-1, 4);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }

    @Test
    public void testGetTileTypeYNegative() {
        char type = safeMap.getTileType(4, -1);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }

    @Test
    public void testGetTileXYNegative() {
        char type = safeMap.getTileType(-1, -1);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }

    @Test
    public void testGetTileTypeXTooBig() {
        char type = safeMap.getTileType(51, 4);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }

    @Test
    public void testGetTileTypeYTooBig() {
        char type = safeMap.getTileType(4, 51);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }

    @Test
    public void testGetTileTypeXYTooBig() {
        char type = safeMap.getTileType(51, 51);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }

    @Test
    public void test10PercentTiles() {
        Assert.assertTrue(safeMap.numberOfWaterTiles <= 0.1);
    }
}
