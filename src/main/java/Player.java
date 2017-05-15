import java.util.Random;

/**
 * @author denise
 * @version 13/04/2017
 */

public class Player {
    
    private Position startPosition;
    public Position position;
    private Team team;
    
    /**
     * Initialises the player and assigns a random position
     * This constructor is to be used for solo games
     *
     * @param mapSize the size of Map
     */
    public Player(int mapSize) {
        team = new Team(mapSize);
        
        Random random = new Random();
        do {
            position = new Position(random.nextInt(mapSize), random.nextInt(mapSize));
        } while(Game.map.getTileType(position.x, position.y) != 'g');
        startPosition = new Position(position.x, position.y);
    }
    
    /**
     * Initialises the player and assigns a random position
     * This constructor is to be used for collaborative games
     *
     * @param mapSize the size of Map
     */
    public Player(int mapSize, Team team) {
        this.team = team;
        
        Random random = new Random();
        do {
            position = new Position(random.nextInt(mapSize), random.nextInt(mapSize));
        } while(Game.map.getTileType(position.x, position.y) != 'g');
        startPosition = new Position(position.x, position.y);
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
        return map.getTileType(p.x, p.y) == 'g' || map.getTileType(p.x, p.y) == 'w' || map.getTileType(p.x, p.y) == 't';
    }
    
    /**
     * Returns the a copy of the start position.
     * @return a deep copy of the player's start position
     */
    public Position getStartPosition(){
        return new Position(startPosition.x, startPosition.y);
    }
    
    public Team getTeam() {
        return team;
    }
}
