import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by denise on 12/04/2017.
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
        if(n<2 || n>8){
            return false;
        }
        return true;
    }

    public void generateHTMLFiles(){
        String pathToFile = "src/gamefiles/test.html";

        File file = new File(pathToFile);
        if(file.exists()) {
            file.delete();
        }
        //Write to file

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            String content = "<html>Hello. \nI am now writing this 2nd line</html>";

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
