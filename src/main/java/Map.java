/**
 * @author Mark Said Camilleri
 * @version 12/04/2017.
 */
public class Map {
    protected int sizeX;
    protected int sizeY;
    
    protected char map[][];

    /**
     * Default constructor for Map class
     */
    public Map(){

    }

    /**
     * Creates the map
     *
     * @param x the x-axis size
     * @param y the y-axis size
     * @throws IllegalArgumentException if the map size is too big
     *                                  (x or y &gt; 50) or too small (x or y &lt; 5)
     */
    public Map(int x, int y) throws IllegalArgumentException {
        if (!setMapSize(x, y))
            throw new IllegalArgumentException("Invalid map size");
        generate();
    }
    
    /**
     * Sets the size of the map.
     *
     * @param x the x-axis size
     * @param y the y-axis size
     * @return true if the map size was set sucessfully, false otherwise
     */
    private boolean setMapSize(int x, int y) {
        if (!checkMapSize(x) || !checkMapSize(y)) return false;
        else {
            sizeX = x;
            sizeY = y;
            return true;
        }
    }
    
    /**
     * Generates the map tiles.
     */
    protected void generate() {
        map = new char[sizeX][sizeY];
        
        boolean treasure = false;
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                int random; // 0 = grass, 1 = water, 2 = treasure
                
                // If treasure was already generated, then it is not
                // generated again
                if (!treasure) {
                    random = (int) (Math.random() * 3);
                } else {
                    random = (int) (Math.random() * 2);
                }
                
                if (random == 0) {
                    map[i][j] = 'g';
                } else if (random == 1) {
                    map[i][j] = 'w';
                } else {
                    map[i][j] = 't';
                    treasure = true;
                }
            }
        }
        
        // To gurantee that there is a treasure tile
        if(!treasure) {
            int treasureRow = (int) (Math.random() * map.length);
            int treasureCol = (int) (Math.random() * map.length);
            
            map[treasureRow][treasureCol] = 't';
        }
    }
    
    /**
     * Tests to see if the map size is valid
     *
     * @param mapSize the size to check
     * @return true if the size is valid, false if it isn't
     */
    public static boolean checkMapSize(int mapSize) {
        return mapSize >= 5 && mapSize <= 50;
    }
    
    /**
     * @return An array with the map sizes {x,y}
     */
    public int[] getMapSize() {
        return new int[]{sizeX, sizeY};
    }
    
    /**
     * Returns the tile type at the position specified.
     *
     * @param x The x coordinate of the map
     * @param y The y coordinate of the map
     * @return 'g' if it's a grass tile, 'w' if it's a water tile,
     * 't' if it's a treasure tile, 'u' if the tile is out of bounds
     */
    public char getTileType(int x, int y) throws IndexOutOfBoundsException {
        if (x >= sizeX || y >= sizeY || x < 0 || y < 0) {
            return 'u';
        } else {
            return map[x][y];
        }
    }
}

