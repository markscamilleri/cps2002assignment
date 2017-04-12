import java.util.Scanner;

/**
 * Created by denise on 12/04/2017.
 */
public class Game extends Player{
    private int numOfPlayers;
    public int turns;
    public Player players[];

    public void startGame() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of players:");
        numOfPlayers = scan.nextInt();
        if (setNumPlayers(numOfPlayers)) {
            System.out.println("Enter map size: ");
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

    }
}
