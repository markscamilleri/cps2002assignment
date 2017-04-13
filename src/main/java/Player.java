import java.util.Random;

import static java.lang.Character.toLowerCase;

/**
 * @author denise
 * @version 12/04/2017
 */

public class Player extends Position{

    Position p;

    public void init(int mapSize){
        Random random = new Random();
        p = new Position(random.nextInt(mapSize), random.nextInt(mapSize));
    }

    public void move(char direction){
        toLowerCase(direction);
        //I am assuming that the bottom left corner is coordinate 0,0
        //still need to check for mapsize
        switch(direction){
            case 'u':
                p.y++;
                break;
            case 'd':
                p.y--;
                break;
            case 'l':
                p.x--;
                break;
            case 'r':
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
