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
    public static int turns;
    public static int mapSize;
    public static Player playerList[];
    private static Map map;
    private static Scanner scan = new Scanner(System.in);

    private static void getNumberOfPlayers(){
        do {
            System.out.println("Enter the number of players:");
            numOfPlayers = scan.nextInt();
        } while (!setNumPlayers(numOfPlayers));
    }

    private static void getMapSize(){
        System.out.println("Enter map size: ");
        mapSize = scan.nextInt();
    }

    private static void createPlayerList(){
        for (int i = 0; i < numOfPlayers; i++) {
            Player p = new Player(mapSize);
            playerList[i] = p;
        }
    }

    private static void getLandingTile(Player player, Map map){
        if (map.getTileType(player.position.x, player.position.y) == 't') {
            System.out.println("Congratulations, you have found the treasure");
            //break;
        }

        if (map.getTileType(player.position.x, player.position.y) == 'w') {
            System.out.println("OOPS, you got a water tile. You died! \nYou have respawned in your start position");
            player.position = player.getStartPosition();
            //break;
        }

        if (map.getTileType(player.position.x, player.position.y) == 'g') {
            System.out.println("You got a Grass tile! Wait for your turn.");
           // break;
        }
    }

    protected static void getNextMove(Player player){
        char move;
        do {
            System.out.println("Enter your move. \nU to move UP \nD to move DOWN \nL to move LEFT \nR to move Right");
            String input = scan.next();
            if(!input.trim().isEmpty()) {
                move = Character.toLowerCase(input.charAt(0));
            } else {
                move = ' '; // Makes the input loop
            }
            player.move(move);
            System.out.print("\n");
        }
        while (!(move == 'u' || move == 'd' || move == 'l' || move == 'r'));
    }
    
    public static void main(String[] args) {
        //Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        
        //char moveInput = ' ';
        int loopIndex = 0;
        
        //Ask for number of players
//        do {
//            System.out.println("Enter the number of players:");
//            numOfPlayers = scan.nextInt();
//        } while (!setNumPlayers(numOfPlayers));
        getNumberOfPlayers();
        playerList = new Player[numOfPlayers];
        
        // Ask user for map size
//        System.out.println("Enter map size: ");
//        mapSize = scan.nextInt();
        getMapSize();
        map = new Map(mapSize, mapSize);
        
        //create Player List
//        for (int i = 0; i < numOfPlayers; i++) {
//            Player p = new Player(mapSize);
//            playerList[i] = p;
//        }
        createPlayerList();
//
        //generate HTML Game Files for each Player
        generateHTMLFiles();
        
        // Loop till winning
        for (Player player : playerList) {
            Position previous = player.position;

    
            // Prompt user for input and check it.
            getNextMove(player);
//            do {
//                System.out.println("Enter your move. \nU to move UP \nD to move DOWN \nL to move LEFT \nR to move Right");
//                String input = scan.next();
//                if(!input.trim().isEmpty()) {
//                    moveInput = Character.toLowerCase(input.charAt(0));
//                } else {
//                    moveInput = ' '; // Makes the input loop
//                }
//                player.move(moveInput);
//                System.out.print("\n");
//            }
//            while (!(moveInput == 'u' || moveInput == 'd' || moveInput == 'l' || moveInput == 'r'));
            
            Position newPos = player.position;
            if (player.setPosition(newPos, map)) {
                player.position = newPos;
            } else {
                player.position = previous;
            }
            
            while (player.uncoveredTiles[player.position.x][player.position.y] == 0) {
                player.uncoveredTiles[player.position.x][player.position.y] = 1;
                getLandingTile(player, map);

                loopIndex++;

                if (loopIndex == playerList.length) {
                    loopIndex = 0;
                    player = playerList[0];
                    turns++;
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
                    if (playerList[playerIndex - 1].uncoveredTiles[i][j] == 0) {
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
