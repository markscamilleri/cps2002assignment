import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mark Said Camilleri
 * @version 15/05/17.
 */
public class TeamTest {
    
    Team team;
    
    @Before
    public void setUp() throws Exception {
        team = new Team(10);
    }
    
    @Test
    public void isMapTileKnown() throws Exception {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Assert.assertFalse(team.isMapTileKnown(j, i));
            }
        }
    }
    
    @Test
    public void discoverTile() throws Exception {
        team.discoverTile(0,0);
        Assert.assertTrue(team.isMapTileKnown(0, 0));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(i != 0 && j != 0) {
                    Assert.assertFalse(team.isMapTileKnown(j, i));
                }
            }
        }
    }
    
}