import java.util.ArrayList;
import java.util.List;

/**
 * This is to demonstrate the difference between generic and wildcard
 * 
 */

class GenericContainer<T> {
    private T data;

    public GenericContainer (T data) {
        this.data = data;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

/**
 * following is not allowed
 * we can not create a class with wildcard
 * wildcard is only for scenario when we are not sure what will be the type
 * and also it does not matter
 * 
 * Can you think of scenario of class declaration where it will be generic and the
 * type don't matter
 * 
 * If there is you can do it through Object
 */
/*
class WildcardContainer<?> {
    private Object data;

    WildcardContainer (Object data) {
        this.data = data;
    }

    Object getData() {
        return this.data;
    }
    
    void setData(Object data) {
        this.data = data;
    }
}
*/


public class GenericVsWildcard {

    /**
     * but we can use the wildcard when we are not sure about parameter
     * and it can be anything
     * 
     * Following method just takes an argument and return it
     * We could do it with Object, right?
     * 
     * Say, we want to bound the parameter type, for example List interface
     * but list interface is generic, we want to make it allowed for all element type
     * 
     * We use wildcard here
     */
    static List<?> trivialMethod(List<?> list) {
        return list;
    }

    /**
     * But could not we do it with object as everything is object?
     * See the problem in main
     * 
     */
    static List<Object> trivialMethodObject(List<Object> list) {
        return list;
    }

    public static void main(String args[]) {
        // let's create a WildcardContainer type for the following String Object
        String str = "Hello World";
        // WildcardContainer containerWildcard = new WildcardContainer(str);
        // now to print it
        ArrayList<String> arrayList = new ArrayList<String>(10);
        trivialMethod(arrayList);
        // can we pass arrayList to trivialMethodObject?
        // following line will give error as trivialMethodObject strictly expects Object
        // if we want to pass List of anything we don't have alternative to wildcard
        // although String is subclass of Object
        // Here we want subtype of List<Object>
        // In generic for subtyping we need ?/wildcard
        // for detail of subtyping check
        // https://docs.oracle.com/javase/tutorial/java/generics/subtyping.html
        trivialMethodObject(arrayList);
        System.out.println(arrayList);
    }
}
