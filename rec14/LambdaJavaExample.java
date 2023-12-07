import java.util.ArrayList;
import java.util.List;

/**
 * This program is to demonstrate different lambda expression
 * lambda expression can be used to pass around functioanlity
 * (i.e. a piece of code) as data
 * 
 * Why to use it?
 * => Mainly you will use it to do syntactic suger (my personal opinion)
 * But it can also be used for scenario like
 * 1. Avoiding annonymous class which may seem too verbose for small functionality
 * 2. Creating an functional interface object (same can be done by annonymous class)
 * 
 */
public class LambdaJavaExample {
    /** 
     * a quite general functional interface
     * it's only method takes a generic parameter and return int
     */
    interface FuncInterface<T extends Object> {
        int op(Object param);
    }
    /** 
     * we want to get a sequence of ops which we will execute in seq
     * how can we get a sequence of ops? class/interface perhaps
     * can we make it a little less verbose? 
     * when calling the function and passing parameters, not when
     * writing the function
     * 
     */
    public static int seqOps(
        List<FuncInterface<? extends Object>> opList,
        List<? extends Object> paramList)
    {
        int paramCounter = 0;
        int result = 0;
        for (FuncInterface<? extends Object> op : opList) {
            result += op.op(paramList.get(paramCounter));
            paramCounter += 1;
        }
        return result;
    }

    /**
     * 
     * @param args cmd line arguments
     */
    public static void main(String args[]) {
        ArrayList<FuncInterface<? extends Object>> opList = new ArrayList<>();
        opList.add( str -> ((String)str).length() );
        opList.add( integer -> ((Integer)integer).intValue() );

        ArrayList<Object> paramList = new ArrayList<>();
        paramList.add("Hello");
        paramList.add(Integer.valueOf(4));
        System.out.println(seqOps(opList, paramList));
    }
}
