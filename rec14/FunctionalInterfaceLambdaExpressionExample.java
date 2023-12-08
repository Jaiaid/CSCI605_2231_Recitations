/**
 * This example demonstrates how lambda can be used to create a interface object
 * to define single functionality interface using lambda expression
 * single functionality means the interface has only one method to implement
 * 
 * interface Iface {
 *  public void method1();
 * }
 *
 * Iface ifInterface = () -> {}
 */
public class FunctionalInterfaceLambdaExpressionExample {
    // a interface with void functionality
    static interface VoidOps {
        public void op();
    };
    // a interface with int returning two int argument taking functionlity
    static interface MathOps {
        public int op(int a, int b);
    }

    /**
     * @param args cmd line arguments
     */
    public static void main(String args[]) {
        // lambda expression does not necessarily need to be single line
        VoidOps voidOps = () -> { 
            System.out.println("Hello from void op lambda");
        };
        
        MathOps mathOps = (a, b) -> {
            System.out.println("Hello from math op lambda");
            // you have to explicitly say return;
            return a + b;
        };

        // now we can call them like object
        voidOps.op();
        int x = mathOps.op(4, 5);
        System.out.printf("Math ops result : %d\n", x);
    }
}
