import java.util.Random;

/**
 * @author denise
 * @version 12/04/2017
 */

public class Player extends Position{

    Position p;
    public void init(int mapSize){
        Random random = new Random();
        p.x = random.nextInt(mapSize);
        p.y = random.nextInt(mapSize);
    }

    public void move(char direction){
        //I am assuming that the bottom left corner is coordinate 0,0
        //still need to check for mapsize
        switch(direction){
            case 'u':
            case 'U':
                p.y++;
                break;
            case 'd':
            case 'D':
                p.y--;
                break;
            case 'l':
            case 'L':
                p.x--;
                break;
            case 'r':
            case 'R':
                p.x++;
                break;
            default:
                break;
        }
    }

    public boolean setPosition(Position p, char tileType){
        if(tileType == 'g'){
            return true;
        }
        if(tileType == 't'){
            return true;
        }
        return false;
    }
}
