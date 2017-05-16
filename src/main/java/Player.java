import java.util.Random;

/**
 * @author Denise Buttigieg
 * @version 13/04/2017
 */

public class Player {
    
    public Position position;
    private Position startPosition;
    private Team team;
    
    /**
     * Initialises the player and assigns a random position
     * This constructor is to be used for solo games
     *
     * @param mapSize the size of Map
     */
    public Player(int mapSize) {
        team = new Team(mapSize);
        
        startAtRandomTile(mapSize, team);
    }

    /**
     * Gets random starting position
     *
     * @param mapSize size of the Map
     * @param team    the team the player is allocated to
     */
    private void startAtRandomTile(int mapSize, Team team) {
        Random random = new Random();
        do {
            position = new Position(random.nextInt(mapSize), random.nextInt(mapSize));
        } while (Game.map.getTileType(position.x, position.y) != 'g');
        startPosition = new Position(position.x, position.y);
        team.discoverTile(position.x, position.y);
    }
    
    /**
     * Initialises the player and assigns a random position
     * This constructor is to be used for collaborative games
     *
     * @param mapSize the size of Map
     * @param team the team to assign players to
     */
    public Player(int mapSize, Team team) {
        this.team = team;
        
        startAtRandomTile(mapSize, team);
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
     * @return true if the move is valid, false otherwise
     */
    public boolean move(char direction) {
        switch (direction) {
            case 'u':
                if (position.y + 1 >= team.getMapSize()) {
                    System.out.println("Invalid move");
                    System.out.print("You're at the edge of the map");
                    return false;
                } else {
                    position.y++;
                    System.out.print("Moved UP");
                    return true;
                }
            case 'd':
                if (position.y - 1 < 0) {
                    System.out.println("Invalid move");
                    System.out.print("You're at the edge of the map");
                    return false;
                } else {
                    position.y--;
                    System.out.print("Moved DOWN");
                    return true;
                }
            case 'l':
                if (position.x - 1 < 0) {
                    System.out.println("Invalid move");
                    System.out.print("You're at the edge of the map");
                    return false;
                } else {
                    position.x--;
                    System.out.print("Moved LEFT");
                    return true;
                }
            case 'r':
                if (position.x + 1 >= team.getMapSize()) {
                    System.out.println("Invalid move");
                    System.out.print("You're at the edge of the map");
                    return false;
                } else {
                    position.x++;
                    System.out.print("Moved RIGHT");
                    return true;
                }
            default:
                System.out.print("Invalid move");
                return false;
        }
    }
    
    /**
     * Checks the position of the player
     *
     * @param p the position of the player
     * @param map the map
     * @return true if the tile is OK to land on, false otherwise
     */
    
    public boolean setPosition(Position p, Map map) {
        return map.getTileType(p.x, p.y) == 'g' || map.getTileType(p.x, p.y) == 'w' || map.getTileType(p.x, p.y) == 't';
    }
    
    /**
     * Returns the a copy of the start position.
     *
     * @return a deep copy of the player's start position
     */
    public Position getStartPosition() {
        return new Position(startPosition.x, startPosition.y);
    }

    /**
     * Gets the team containing players
     *
     * @return team with players
     */
    public Team getTeam() {
        return team;
    }
}
