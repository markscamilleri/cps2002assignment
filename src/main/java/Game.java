import java.io.*;
import java.util.Scanner;

import static java.lang.Character.*;

/**
 * @author denise
 * @version 13/04/2017.
 */

public class Game {
    public int numOfPlayers;
    public int turns;
    public int mapSize;
    public Player playerList[];

    /**
     * Checks that the number of playerList is valid
     * @param n the number of playerList
     * @return true if the number of playerList is between 2 and 8, false otherwise
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
        char moveInput;
        int loopIndex = 0;

        //Ask for Number of playerList
        System.out.println("Enter the number of playerList:");
        numOfPlayers = scan.nextInt();

        if (setNumPlayers(numOfPlayers)) {
            playerList = new Player[numOfPlayers];

            System.out.println("Enter map size: ");
            mapSize = scan.nextInt();
            map = new Map(mapSize, mapSize);

            //create Player List
            for(int i=0; i<numOfPlayers; i++){
                Player p = new Player();
                p.init(mapSize);
                playerList[i] = p;
            }

            //generate HTML Game Files for each Player
            generateHTMLFiles();

            for(Player player: playerList){
                System.out.println("Enter your move. \nU to move UP \nD to move DOWN \nL to move LEFT \nR to move Right");
                moveInput = scan.next().charAt(0);
                toLowerCase(moveInput);

                if(moveInput=='u' || moveInput=='d' || moveInput=='l' || moveInput=='r'){
                    Position previous = player.position;
                    player.move(moveInput);
                    Position newPos = player.position;
                    if(player.setPosition(newPos, map)) {
                        player.position = newPos;
                    }

                    while(map.getTileType(player.position.x, player.position.y) == 'u') {

                        if (map.getTileType(player.position.x, player.position.y) == 't') {
                            System.out.println("Congratulations, you have found the treasure");
                            break;
                        }

                        if (map.getTileType(player.position.x, player.position.y) == 'w') {
                            System.out.println("OOPS, you got a water tile. You loose!");
                            break;
                        }

                        if (map.getTileType(player.position.x, player.position.y) == 'g') {
                            System.out.println("You got a Grass tile, pick your next move!");
                            break;
                        }
                    }
                }
                loopIndex++;

                if(loopIndex == playerList.length){
                    loopIndex = 0;
                    player = playerList[0];
                    turns++;
                }
            }
        } else {
            System.out.println("Enter the number of playerList:");
            numOfPlayers = scan.nextInt();
        }
    }
}
