/**
 * @author denise
 * @version 12/05/2017.
 */
public class SafeMap extends Map{
    protected int numberOfWaterTiles;
    /**
     * Creates the map
     *
     * @param x the x-axis size
     * @param y the y-axis size
     * @throws IllegalArgumentException if the map size is too big
     *                                  (x or y > 50) or too small (x or y < 5)
     */
    public SafeMap(int x, int y){
        super(x, y);
    }

    /**
     * Generates the map, overrides Map.generate()
     */
    protected void generate() {

        map = new char[sizeX][sizeY];

        int totalTiles = sizeX*sizeY;

        //taking 10% of the number of tiles
        numberOfWaterTiles = (int) Math.floor((0.1) * (sizeX * sizeY));


        boolean treasure = false;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {

                int random = (int) (Math.random() * totalTiles);

                if(!treasure) {
                    map[i][j] = 't';
                    treasure = true;
                } else {
                    if(random < numberOfWaterTiles) {
                        map[i][j] = 'w';
                        numberOfWaterTiles--;
                    } else {
                        map[i][j] = 'g';
                    }
                }
            totalTiles--;
            }
        }
    }
}
