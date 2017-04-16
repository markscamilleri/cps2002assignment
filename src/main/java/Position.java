/**
 * @author denise
 * @version 12/04/2017.
 */
public class Position {
    public int x;
    public int y;

    /**
     * Default constructor for Position
     */

    public Position(){
        x = 0;
        y = 0;
    }

    /**
     * Sets a position
     * @param x is the x-coordinate of the player
     * @param y is the y-coordinate of the player
     */

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
}