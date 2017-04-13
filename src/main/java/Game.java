import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author denise
 * @version 13/04/2017.
 */
public class Game extends Player{
    protected int numOfPlayers;
    public int turns;
    public int mapSize;
    public Player players[];

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of players:");
        numOfPlayers = scan.nextInt();

        if (setNumPlayers(numOfPlayers)) {
            players = new Player[numOfPlayers];

            System.out.println("Enter map size: ");
            mapSize = scan.nextInt();

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

    public boolean setNumPlayers(int n){
        return !(n < 2 || n > 8);
    }

    public void generateHTMLFiles(){
        int playerIndex = 1;

        /**
         * Deleting all previously created game files
         */

        File dir = new File("src/gamefiles");
        for (File file: dir.listFiles()) {
            if (!file.isDirectory()) {
                file.delete();
            }
        }

        /**
         * Creating new game files according to the new game
         */

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
}
