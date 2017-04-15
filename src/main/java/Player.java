import java.util.Random;

import static java.lang.Character.toLowerCase;

/**
 * @author denise
 * @version 13/04/2017
 */

public class Player{

    Position p;

    /**
     * Initialises the player and assigns a random position
     * @param mapSize the size of Map
     */
    public void init(int mapSize){
        Random random = new Random();
        p = new Position(random.nextInt(mapSize), random.nextInt(mapSize));
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
        toLowerCase(direction);
        boolean validMove = true;
        while(validMove) {
            switch (direction) {
                case 'u': {
                    p.y++;
                    System.out.println("Moved UP");
                    break;
                }
                case 'd': {
                    p.y--;
                    System.out.println("Moved DOWN");
                    break;
                }
                case 'l': {
                    p.x--;
                    System.out.println("Moved LEFT");
                    break;
                }
                case 'r': {
                    p.x++;
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
     * @param tileType the type of tile the player lands on
     * @return true if the tile is OK to land on, false otherwise
     */

    public boolean setPosition(Position p, char tileType){
        if(tileType == 'g'){
            return true;
        }
        return tileType == 't';
    }
}
