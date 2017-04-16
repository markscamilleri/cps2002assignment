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
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        //Ask for Number of playerList
        do {
            System.out.println("Enter the number of players:");
            numOfPlayers = scan.nextInt();
        } while (!setNumPlayers(numOfPlayers));
        playerList = new Player[numOfPlayers];
        
        System.out.println("Enter map size: ");
        mapSize = scan.nextInt();
        map = new Map(mapSize, mapSize);
        
        for (int i = 0; i < numOfPlayers; i++) {
            Player p = new Player(mapSize);
            playerList[i] = p;
        }
        generateHTMLFiles();
    }
    
    /**
     * Checks that the number of playerList is valid
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
                    if(playerList[playerIndex-1].uncoveredTiles[i][j] == 0){
                        switch(map.getTileType(i,j)){
                            case 'g':
                                    colour = "green";
                                    break;
                            case 'w':
                                    colour = "blue";
                                    break;
                            case 't':
                                    colour = "gold";
                                    break;
                            default: //This should never be used.
                                    colour = "grey";
                                    break;
                        }
                    } else {
                        colour = "grey";
                    }
                    
                    String style = "style=\"width: 2em; height: 2em; text-align: center; font-size: 2em; background-color: " + colour + ";\"";
                    bufferedWriter.write("<td " + style + ">");
                    if (playerList[playerIndex-1].position.y == i && playerList[playerIndex-1].position.x == j)
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
}
