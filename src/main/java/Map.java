/**
 * @author denise
 * @version 12/04/2017.
 */
public class Map{
    private int sizeX;
    private int sizeY;
    
    private char map[][];
    
    /**
     *  Creates the map
     * @param x the x-axis size
     * @param y the y-axis size
     */
    public Map(int x, int y){
        
    }
    
    private boolean setMapSize(int x, int y){
        return true;
    }
    
    /**
     * Generates the map tiles.
     */
    private void generate(){
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
    }
    
    /**
     * @return An array with the map size {x,y}
     */
    public int[] getMapSize(){
        return new int[]{sizeX, sizeY};
    }
    
    /**
     * Returns the tile type at the position specified.
     * @param x The x coordinate of the map
     * @param y The y coordinate of the map
     * @return 'g' if it's a grass tile, 'w' if it's a water tile,
     *          't' if it's a treasure tile
     * @throws IndexOutOfBoundsException if either the x parameter or
     *          the y parameter is out of bounds.
     */
    public char getTileType(int x, int y) throws IndexOutOfBoundsException {
        return 'x';
    }
}

