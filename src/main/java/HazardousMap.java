/**
 * @author Mark Said Camilleri
 * @version 12/05/17.
 */
public class HazardousMap extends Map {
    /**
     * Creates the map
     *
     * @param x the x-axis size
     * @param y the y-axis size
     * @throws IllegalArgumentException if the map size is too big
     *                                  (x or y > 50) or too small (x or y < 5)
     */
    public HazardousMap(int x, int y) throws IllegalArgumentException {
        super(x, y);
    }
    
    @Override
    protected void generate() {
        map = new char[sizeX][sizeY];
        
        int noOfTilesLeft = sizeX * sizeY;
        
        /* This is guaranteed to be 25% - 35% of the number of tiles in the map
         * Since Math.random() is guaranteed to be between 0 and 1
         * The following logic can be used to gurantee the percentage:
         *
         * x = Math.random() ==> 0    <= x < 1
         * x = x * 0.1       ==> 0    <= x < 0.1    (0.1 = 0.35 - 0.25)
         * x = x + 0.25      ==> 0.25 <= x < 0.35
         * Let x be the percentage
         */
        double percentage = (Math.random() * (0.35 - 0.25)) + 0.25;
        int numberOfWaterTilesLeft = ((int) Math.floor(percentage * sizeX * sizeX));
    
        boolean treasure = false;
        
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                
                int random = ((int) (Math.random() * noOfTilesLeft));
                
                if(random < numberOfWaterTilesLeft){
                    map[i][j] = 'w';
                    numberOfWaterTilesLeft--;
                } else {
                    if(!treasure && Math.random() < 0.5){
                        map[i][j] = 't';
                        treasure = true;
                    } else {
                        map[i][j] = 'g';
                    }
                }
                
                noOfTilesLeft--;
            }
        }
    }
}
