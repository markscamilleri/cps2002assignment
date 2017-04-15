import java.io.*;
import java.util.Scanner;

/**
 * @author denise
 * @version 13/04/2017.
 */

public class Game {
    public int numOfPlayers;
    public int turns;
    public int mapSize;
    public Player players[];

    /**
     * Checks that the number of players is valid
     * @param n the number of players
     * @return true if the number of players is between 2 and 8, false otherwise
     */

    public boolean setNumPlayers(int n){
        return !(n < 2 || n > 8);
    }

    /**
     * Generates Game HTML Files for each player
     */
    public void generateHTMLFiles(){
        int playerIndex = 1;

        //Deleting all previously created game files
        File dir = new File("src/gamefiles");
        for (File file: dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

        //Creating new game files according to the new game
        for (int i=0; i<numOfPlayers; i++, playerIndex++) {
            String filename = "map_player_" + playerIndex + ".html";
            String pathToFile = "src/gamefiles/"+filename;

            FileWriter fileWriter = null;
            BufferedWriter bufferedWriter = null;

            try {
                String content = "<html>Hello. \nThis is the html file for player " + playerIndex + "\nTesting if Java is cleaning files AGAIN</html>";

                fileWriter = new FileWriter(pathToFile, true);
                bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(content);

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
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            }
        }
    }

    public void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Map map;

        //Ask for Number of players
        System.out.println("Enter the number of players:");
        numOfPlayers = scan.nextInt();
        if (setNumPlayers(numOfPlayers)) {
            players = new Player[numOfPlayers];

            System.out.println("Enter map size: ");
            mapSize = scan.nextInt();
            map = new Map(mapSize, mapSize);

            for(int i=0; i<numOfPlayers; i++){
                Player p = new Player();
                players[i] = p;
            }
            generateHTMLFiles();
        } else {
            System.out.println("Enter the number of players:");
            numOfPlayers = scan.nextInt();
        }
    }
}
