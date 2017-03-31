import org.junit.Assert;
import org.junit.Test;

/**
 * @author mark
 * @version 31/03/17.
 */
public class HelloTest {
    @Test
    public void testGetHello() throws Exception {
        Assert.assertEquals("Hello Universe!!!", Hello.getHello());
    }

    @Test
    public void testGetName() throws Exception {
        Assert.assertEquals("Denise and Mark Assignment", Hello.getName());
    }

    @Test
    public void testGetDate() throws Exception {
        Assert.assertEquals("Friday 31 March, Happy Holiday!", Hello.getDate());
    }
    
    @Test
    public void testGetAnswerToLife() throws Exception {
        Assert.assertEquals(42, Hello.getAnswerToLife());
    }
}