import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mark Said Camilleri
 * @version 12/05/17.
 */
public class HazardousMapTest {
    private HazardousMap map;
    
    @Before
    public void setUp() throws Exception {
        map = new HazardousMap(8, 8);
    }
    
    @Test
    public void testMapIsCreated() throws Exception {
        Assert.assertNotNull(map);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMapXTooLarge() throws Exception {
        map = new HazardousMap(51, 8);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMapXTooSmall() throws Exception {
        map = new HazardousMap(4, 8);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMapYTooLarge() throws Exception {
        map = new HazardousMap(8, 51);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMapYTooSmall() throws Exception {
        map = new HazardousMap(8, 4);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMapXYTooLarge() throws Exception {
        map = new HazardousMap(51, 51);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testMapXYTooSmall() throws Exception {
        map = new HazardousMap(4, 4);
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
    
    @Test
    public void testGetTileTypeXNegative() {
        char type = map.getTileType(-1, 4);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }
    
    @Test
    public void testGetTileTypeYNegative() {
        char type = map.getTileType(4, -1);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }
    
    @Test
    public void testGetTileXYNegative() {
        char type = map.getTileType(-1, -1);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }
    
    @Test
    public void testGetTileTypeXTooBig() {
        char type = map.getTileType(51, 4);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }
    
    @Test
    public void testGetTileTypeYTooBig() {
        char type = map.getTileType(4, 51);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }
    
    @Test
    public void testGetTileTypeXYTooBig() {
        char type = map.getTileType(51, 51);
        Assert.assertTrue("Tile is not unknoown", type == 'u');
    }
    
    @Test
    public void test2535PercentWater() {
        double waterTiles = 0;
        double tiles = 0;
        
        for (int i = 0; i < map.sizeX; i++) {
            for (int j = 0; j < map.sizeY; j++) {
                if (map.getTileType(i, j) == 'w') {
                    waterTiles++;
                }
                tiles++;
            }
        }
        
        Assert.assertTrue("There are less than 25% water tiles", (waterTiles / tiles) >= 0.25);
        Assert.assertTrue("There are more than 35% water tiles", (waterTiles / tiles) <= 0.35);
    }
    
    @Test
    public void testThereIsATreasureTile() {
        
        boolean treasureTile = false;
        
        for (int i = 0; i < map.sizeY && !treasureTile; i++) {
            for (int j = 0; j < map.sizeX && !treasureTile; j++) {
                treasureTile = (map.getTileType(j, i) == 't');
            }
        }
        
        Assert.assertTrue(treasureTile);
    }
    
}