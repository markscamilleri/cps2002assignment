/**
 * @author mark
 * @version 31/03/17.
 */
public class Hello {
    public static final String getHello() {
        return "Hello Universe!!!";
    }

    public static final String getName() {
        return "Denise and Mark Assignment";
    }

    public static final String getDate() {
        return "Friday 31 March, Happy Holiday!";
    }

    public static final short getAnswerToLife() {
        return 42;
    }
    
    public static void main(String[] args) {
        System.out.println(getHello());
        System.out.println(getName());
        System.out.println(getDate());
    }

}
