import java.util.Random;

import static java.lang.Character.toLowerCase;

/**
 * @author denise
 * @version 13/04/2017
 */

public class Player{

    public Position position;

    /**
     * Initialises the player and assigns a random position
     * @param mapSize the size of Map
     */
    public void init(int mapSize){
        Random random = new Random();
        position = new Position(random.nextInt(mapSize), random.nextInt(mapSize));
    }

    /**
     * Moves the player according to his command
     * 'u': Go Up
     * 'd': Go Down
     * 'l' Go Left
     * 'r': Go Right
     * Inputs are accepted in lower or upper case
     * @param direction the direction the user wants to move
     */
    public void move(char direction){
        boolean validMove = true;
        while(validMove) {
            switch (direction) {
                case 'u': {
                    position.y++;
                    System.out.println("Moved UP");
                    break;
                }
                case 'd': {
                    position.y--;
                    System.out.println("Moved DOWN");
                    break;
                }
                case 'l': {
                    position.x--;
                    System.out.println("Moved LEFT");
                    break;
                }
                case 'r': {
                    position.x++;
                    System.out.println("Moved RIGHT");
                    break;
                }
                default: {
                    validMove = false;
                    System.out.println("Invalid move");
                    break;
                }
            }
        }
    }

    /**
     * Checks the position of the player
     * @param p the position of the player
     * @return true if the tile is OK to land on, false otherwise
     */

    public boolean setPosition(Position p, Map map){
        if (map.getTileType(p.x, p.y)=='g' || map.getTileType(p.x, p.y)=='w' || map.getTileType(p.x, p.y)=='t'){
            return true;
        }
        return false;
    }
}
