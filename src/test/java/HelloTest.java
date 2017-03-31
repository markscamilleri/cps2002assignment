import org.junit.Assert;
import org.junit.Test;

/**
 * @author mark
 * @version 31/03/17.
 */
public class HelloTest {
    @Test
    public void testGetHello() throws Exception {
        Assert.assertEquals("Hello World", Hello.getHello());
    }
    
}