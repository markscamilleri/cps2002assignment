/**
 * @author denise
 * @version 12/04/2017.
 */
public class Map extends Game{
    private int sizeX;
    private int sizeY;
    
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
    private static void generate(){}
    
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

