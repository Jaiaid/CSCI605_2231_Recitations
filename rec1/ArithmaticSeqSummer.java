/*
 * ArithmaticSummer.java
 *
 * Version:
 *  1
 *
 * Revisions:
 *
 */

/**
 * ArithmaticSummer.java.
 * It contains a utility class containing only main method
 * Example code to show output
 *
 * @author jm5071
 */

public class ArithmaticSeqSummer {
    /**
     * Constructor
     * 
     * @return
     */
    ArithmaticSeqSummer () {
        System.out.println("ArithmaticSeqSummer object created");
    }
    
    /**
     * 
     * @param startTerm
     * @param interval
     * @param termCount
     * @return
     */
    public int seqSumCalc ( int startTerm, int interval, int termCount) {
        System.out.printf(
            "ArithmaticSeqSummer object is interacted with to calculate sequence:\n"
        );
        System.out.printf("%d + (%d + %d) + ... + (%d + %d * %d)\n"
        , startTerm, startTerm, interval, startTerm, termCount - 1, interval);
        return termCount * startTerm
        + ( termCount * ( termCount - 1 ) * interval ) / 2;
    }

    /**
     * main method.
     *
     * @param args cmd line arguments (ignored)
     */
    public static void main( String[] args ) {
        ArithmaticSeqSummer summer = new ArithmaticSeqSummer();
        System.out.println(args.length);
        if (args.length == 3) {
            System.out.println("Appropriate number of argument provided");
            // is the coding style correct?
            int result = summer.seqSumCalc(
                Integer.parseInt(args[0])
                , Integer.parseInt(args[1])
                , Integer.parseInt(args[2])
            );
            System.out.printf("Sequence sum = %d\n", result);
        }
        else {
            System.out.println("3 argument expected");
        }
        return;
    }
}
