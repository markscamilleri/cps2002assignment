/**
 * @author denise
 * @version 12/04/2017.
 */
public class Map extends Game{
    int size;

    public boolean setMapSize(int x, int y){
        return true;
    }

    public void generate(){}

    public char getTileType(int x, int y){
        return 'x';
    }
}

