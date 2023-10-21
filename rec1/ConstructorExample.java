/*
 * HelloWorld.java
 *
 * Version:
 *  1
 *
 * Revisions:
 *
 */

/**
 * Example Class containainig constructor example showing main method.
 *
 * @author jm5071
 */

public class ConstructorExample {
    /**
     * main method.
     *
     * @param args
     */
    public static void main( String[] args ) {
        A a = new A( 4 );

        // changing pubState
        System.out.println("Changing pubState of object 'a'");
        a.pubState = 10;

        System.out.println("object 'a' public state : "
                            + String.valueOf(a.pubState));
        // privState in private attribute
        // therefore, public getter method is called.
        System.out.println("object 'a' private state : "
                            + String.valueOf(a.getPrivState()));

        return;
    }
}

/**
 * internal class whose construction will be shown.
 */

class A {
    /**
     * public attribute.
     */
    public int pubState;
    /**
     * private attribute.
     */
    private int privState;

    /**
     * Class A constructor.
     *
     * @param priv int to initiate priv_state attribute
     */
    A( int priv ) {
        pubState = 0;
        privState = priv;
        System.out.println("Constructing class A with");
        System.out.println("pub state : " + String.valueOf(pubState));
        System.out.println("priv state : " + String.valueOf(privState));
    }

    /**
     * public getter method for privState attribute.
     *
     * @return privState attribute
     */
    int getPrivState() {
        return privState;
    }
}
