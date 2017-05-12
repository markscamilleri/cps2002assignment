import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author denise
 * @version 13/04/2017.
 */

public class Game {
    static Map map;
    private static Scanner scan = new Scanner(System.in);
    
    
    //This class can't be instantiated
    private Game() {
    }
    
    public static void main(String[] args) {
        Player playerList[];
        
        scan.useDelimiter("\n");
        
        final int numOfPlayers = getNumberOfPlayers();
        
        final int mapSize = getMapSize();
        map = getMap(mapSize);
        
        playerList = createPlayerList(numOfPlayers, mapSize);
        
        boolean gameOver = false;
        while (!gameOver) {
            //generate HTML Game Files for each Player
            generateHTMLFiles(playerList);
            
            // Loop till winning
            for (int i = 0; i < playerList.length; i++) {
                Player player = playerList[i];
                Position previous = player.position;
                
                // Prompt user for input and check it.
                getNextMove(player, i);
                
                Position newPos = player.position;
                if (player.setPosition(newPos, map)) {
                    player.position = newPos;
                } else {
                    player.position = previous;
                }
                
                if (getLandingTile(player, map) == 't') {
                    gameOver = true;
                    System.out.println("Player " + (i + 1) + " wins!");
                }
            }
        }
    }
    
    /**
     * Asks the user for the number of players
     *
     * @return the number of players
     */
    public static int getNumberOfPlayers() {
        int input;
        do {
            System.out.println("Enter the number of players:");
            input = scan.nextInt();
        } while (!setNumPlayers(input));

        return input;
    }
    
    /**
     * Prompts the user for the map size and returns it
     *
     * @return the map size given by the user. Gurantees a legal amount
     */
    public static int getMapSize() {
        int mapSize;
        do {
            System.out.println("Enter map size: ");
            mapSize = scan.nextInt();
        } while (!Map.checkMapSize(mapSize));

        return mapSize;
    }
    
    /**
     * Creates the player list
     *
     * @param numOfPlayers the number of players that will be playing
     * @param mapSize      the size of the map that is used
     * @return the list of players
     */
    public static Player[] createPlayerList(final int numOfPlayers, final int mapSize) {
        Player[] players = new Player[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            Player p = new Player(mapSize);
            players[i] = p;
        }
        
        return players;
    }
    
    /**
     * Generates Game HTML Files for each player
     */
    public static void generateHTMLFiles(Player[] players) {
        //Deleting all previously created game files
        File dir = new File("src/gamefiles");
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
        
        //Creating new game files according to the new game
        for (int playerIndex = 1; playerIndex <= players.length; playerIndex++) {
            generateHTMLFile(players[playerIndex - 1], playerIndex);
        }
    }
    
    private static void getNextMove(Player player, int playerIndex) {
        char move;
        do {
            System.out.println("Player " + (playerIndex + 1) + "'s turn.");
            System.out.println("Enter your move.");
            System.out.println("U to move UP");
            System.out.println("D to move DOWN");
            System.out.println("L to move LEFT");
            System.out.println("R to move Right");
            String input = scan.next();
            
            if (!input.trim().isEmpty()) {
                move = Character.toLowerCase(input.charAt(0));
            } else {
                move = ' '; // Makes the input loop
            }
            player.move(move);
            System.out.print("\n");
        }
        while (!(move == 'u' || move == 'd' || move == 'l' || move == 'r'));
    }
    
    /**
     * Gets the tile type the user landed on
     *
     * @param player the player who's playing
     * @param map    the map used
     * @return the tile type
     */
    protected static char getLandingTile(Player player, Map map) {
        String action = "stepped";
        if (!player.uncoveredTiles[player.position.x][player.position.y]) {
            player.uncoveredTiles[player.position.x][player.position.y] = true;
            action = "found";
        }
        
        char type = map.getTileType(player.position.x, player.position.y);
        if (type == 't') {
            System.out.println("Congratulations, you have found the treasure");
        } else if (type == 'w') {
            System.out.println("OOPS, you " + action + " on a water tile. You died!");
            System.out.println("You have respawned in your start position");
            player.position = player.getStartPosition();
        } else if (type == 'g') {
            System.out.println("You " + action + " a Grass tile! Wait for your turn.");
        }
        
        return type;
    }
    
    /**
     * Checks that the number of playerList is valid
     *
     * @param n the number of playerList
     * @return true if the number of playerList is between 2 and 8, false otherwise
     */
    public static boolean setNumPlayers(int n) {
        return !(n < 2 || n > 8);
    }
    
    private static void generateHTMLFile(Player player, int playerIndex) {
        String filename = "map_player_" + playerIndex + ".html";
        String pathToFile = "src/gamefiles/" + filename;
        
        
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(pathToFile, true));
            bufferedWriter.write("<!DOCTYPE html>\n");
            bufferedWriter.write("<html>\n<body>\n");
            bufferedWriter.write("<h1>Player Map for player " + playerIndex + "</h1>\n<div>\n<table>");
            
            for (int y = map.getMapSize()[0] - 1; y >= 0; y--) {
                bufferedWriter.write("<tr>");
                for (int x = 0; x < map.getMapSize()[0]; x++) {
                    String colour;
                    if (player.uncoveredTiles[x][y]) {
                        colour = getColour(map.getTileType(x, y));
                    } else {
                        colour = "grey";
                    }
                    
                    String style = "style=\"width: 2em; height: 2em; text-align: center; font-size: 2em; background-color: " + colour + ";\"";
                    bufferedWriter.write("<td " + style + ">");
                    if (player.position.y == y && player.position.x == x)
                        bufferedWriter.write("&bull;");
                    bufferedWriter.write("</td>");
                }
                bufferedWriter.write("</tr>\n");
            }
            
            bufferedWriter.write("</table>\n</div>\n</body>\n</html>");
            bufferedWriter.flush();
        } catch (IOException ex) {
            ex.getMessage();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException ex) {
                    ex.getMessage();
                }
            }
        }
    }
    
    /**
     * Returns the HTML Colour value as a string
     *
     * @param tileType The type of tile uncovered
     * @return green, blue, gold or (but should never return) grey.
     */
    static String getColour(char tileType) {
        switch (tileType) {
            case 'g':
                return "green";
            case 'w':
                return "blue";
            case 't':
                return "gold";
            default: //This should never be used.
                return "grey";
        }
    }
    
    /**
     * Prompts the user for the map type and returns the generated map
     *
     * @param mapSize the map size
     * @return the generated map.
     */
    public static Map getMap(final int mapSize) {
        int mapType = -99;
        do {
            System.out.println("Enter the number corresponding to the map type");
            System.out.println("you want to use: \n");
            System.out.println("1 Safe Map");
            System.out.println("2 Hazardous Map");
            
            mapType = scan.nextInt();
        } while (mapType < 1 || mapType > 2);
        
        if (mapType == 1) {
            return new SafeMap(mapSize, mapSize);
        } else { // mapType is guaranteed to be a 1 or a 2.
            return new HazardousMap(mapSize, mapSize);
        }
    }
}
