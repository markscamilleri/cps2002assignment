/**
 * @author Mark Said Camilleri
 * @version 15/05/17.
 */
public class Team {
    private boolean[][] uncoveredTiles;
    
    /**
     * @param mapSize the size of the map
     */
    public Team(int mapSize) {
        setTilesToFalse(mapSize);
    }

    /**
     * Creates an empty map for a team
     *
     * @param mapSize the size of the Map
     */
    private void setTilesToFalse(int mapSize) {
        uncoveredTiles = new boolean[mapSize][mapSize];
        for (int y = 0; y < uncoveredTiles.length; y++) {
            for (int x = 0; x < uncoveredTiles[y].length; x++) {
                uncoveredTiles[x][y] = false;
            }
        }
    }
    
    /**
     * @param x the x-coordinate in the map
     * @param y the y-coordinate in the map
     * @return true if the tile is uncovered, false otherwise
     */
    public boolean isMapTileKnown(int x, int y) {
        return uncoveredTiles[x][y];
    }
    
    /**
     * Flags the tile given as discovered
     *
     * @param x the x-coordinate in the map
     * @param y the y-coordinate in the map
     */
    public void discoverTile(int x, int y) {
        uncoveredTiles[x][y] = true;
    }

    /**
     * Gets the total number of map tiles
     *
     * @return total number of tiles on the map
     */
    public int getMapSize() {
        return uncoveredTiles.length;
    }
}
