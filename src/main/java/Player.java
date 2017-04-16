import java.util.Random;

/**
 * @author denise
 * @version 13/04/2017
 */

public class Player {
    
    public Position position;
    public int[][] uncoveredTiles;
    
    /**
     * Initialises the player and assigns a random position
     *
     * @param mapSize the size of Map
     */
    public Player(int mapSize) {
        Random random = new Random();
        position = new Position(random.nextInt(mapSize), random.nextInt(mapSize));
        uncoveredTiles = new int[mapSize][mapSize];
        for (int i = 0; i < uncoveredTiles.length; i++) {
            for (int j = 0; j < uncoveredTiles[i].length; j++) {
                uncoveredTiles[i][j] = 1;
            }
        }
    }
    
    /**
     * Moves the player according to his command
     * 'u': Go Up
     * 'd': Go Down
     * 'l' Go Left
     * 'r': Go Right
     * Inputs are accepted in lower or upper case
     *
     * @param direction the direction the user wants to move
     */
    public void move(char direction){
        switch (direction) {
            case 'u': {
                position.y++;
                System.out.print("Moved UP");
                break;
            }
            case 'd': {
                position.y--;
                System.out.print("Moved DOWN");
                break;
            }
            case 'l': {
                position.x--;
                System.out.print("Moved LEFT");
                break;
            }
            case 'r': {
                position.x++;
                System.out.print("Moved RIGHT");
                break;
            }
            default: {
                System.out.print("Invalid move");
                break;
            }
        }
    }
    
    /**
     * Checks the position of the player
     *
     * @param p the position of the player
     * @return true if the tile is OK to land on, false otherwise
     */
    
    public boolean setPosition(Position p, Map map) {
        if (map.getTileType(p.x, p.y) == 'g' || map.getTileType(p.x, p.y) == 'w' || map.getTileType(p.x, p.y) == 't') {
            return true;
        }
        return false;
    }
}
