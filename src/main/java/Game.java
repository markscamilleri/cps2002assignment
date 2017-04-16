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
    public static int numOfPlayers;
    public static int mapSize;
    public static Player playerList[];
    private static Map map;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        
        char moveInput;
        
        //Ask for number of players
        do {
            System.out.println("Enter the number of players:");
            numOfPlayers = scan.nextInt();
        } while (!setNumPlayers(numOfPlayers));
        playerList = new Player[numOfPlayers];
        
        // Ask user for map size
        System.out.println("Enter map size: ");
        mapSize = scan.nextInt();
        map = new Map(mapSize, mapSize);
        
        //create Player List
        for (int i = 0; i < numOfPlayers; i++) {
            Player p = new Player(mapSize);
            playerList[i] = p;
        }
        
        boolean gameOver = false;
        while (!gameOver) {
            //generate HTML Game Files for each Player
            generateHTMLFiles();
            
            for (int i = 0; i < playerList.length; i++) {
                Player player = playerList[i];
                Position previous = new Position(player.position.x, player.position.y);
                
                // Prompt user for input and check it.
                do {
                    System.out.println("Player " + (i + 1) + "'s turn.");
                    System.out.println("Enter your move.");
                    System.out.println("U to move UP");
                    System.out.println("D to move DOWN");
                    System.out.println("L to move LEFT");
                    System.out.println("R to move Right");
                    
                    String input = scan.next();
                    if (!input.trim().isEmpty()) {
                        moveInput = Character.toLowerCase(input.charAt(0));
                    } else {
                        moveInput = ' '; // Makes the input loop
                    }
                    player.move(moveInput);
                    System.out.print("\n");
                }
                while (!(moveInput == 'u' || moveInput == 'd' || moveInput == 'l' || moveInput == 'r'));
                
                Position newPos = player.position;
                if (player.setPosition(newPos, map)) {
                    player.position = newPos;
                } else {
                    player.position = previous;
                }
                
                String action = "stepped";
                if (!player.uncoveredTiles[player.position.x][player.position.y]) {
                    player.uncoveredTiles[player.position.x][player.position.y] = true;
                    action = "found";
                }
                
                if (map.getTileType(player.position.x, player.position.y) == 't') {
                    System.out.println("Congratulations, you have found the treasure");
                    gameOver = true;
                    System.out.println("Player " + (i + 1) + " wins!");
                } else if (map.getTileType(player.position.x, player.position.y) == 'w') {
                    System.out.println("OOPS, you " + action + " on a water tile. You died!");
                    System.out.println("You have respawned in your start position");
                    player.position = player.getStartPosition();
                } else if (map.getTileType(player.position.x, player.position.y) == 'g') {
                    System.out.println("You " + action + " a Grass tile! Wait for your turn.");
                }
            }
        }
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
    
    /**
     * Generates Game HTML Files for each player
     */
    public static void generateHTMLFiles() {
        //Deleting all previously created game files
        File dir = new File("src/gamefiles");
        for (File file : dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }
        
        //Creating new game files according to the new game
        for (int playerIndex = 1; playerIndex <= numOfPlayers; playerIndex++) {
            generateHTMLFile(playerIndex);
        }
    }
    
    private static void generateHTMLFile(int playerIndex) {
        String filename = "map_player_" + playerIndex + ".html";
        String pathToFile = "src/gamefiles/" + filename;
        
        
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(pathToFile, true));
            bufferedWriter.write("<!DOCTYPE html>\n");
            bufferedWriter.write("<html>\n<body>\n");
            bufferedWriter.write("<h1>Player Map for player " + playerIndex + "</h1>\n<div>\n<table>");
            
            for (int i = 0; i < mapSize; i++) {
                bufferedWriter.write("<tr>");
                for (int j = 0; j < mapSize; j++) {
                    String colour;
                    if (playerList[playerIndex - 1].uncoveredTiles[i][j]) {
                        colour = getColour(map.getTileType(i, j));
                    } else {
                        colour = "grey";
                    }
                    
                    String style = "style=\"width: 2em; height: 2em; text-align: center; font-size: 2em; background-color: " + colour + ";\"";
                    bufferedWriter.write("<td " + style + ">");
                    if (playerList[playerIndex - 1].position.y == i && playerList[playerIndex - 1].position.x == j)
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
}
