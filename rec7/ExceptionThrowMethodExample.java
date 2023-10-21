import java.io.FileNotFoundException;

/**
 * Custome exception
 */
class CustomCheckedException extends Exception {
    // constructor to initiate Super class
    CustomCheckedException() {
        //super("super");
    }

    // public String toString() {
    //     return "Custom rchecked exception thrown which is extended from " + super.getMessage();
    // }
}
/**
 * Custom runtime exception
 */
class CustomRuntimeException extends RuntimeException {
    public String toString() {
        return "Custom runtime exception thrown which is extended from " + super.getMessage();
    }
}
/**
 * custom error
 */
class CustomError extends Error {
    public String toString() {
        return "Custom Error thrown which is extended from " + super.getMessage();
    }
}

/**
 * Class containing exception throwing method
 * 
 * Both checked exception and runtime exceptions are demonstrated
 * We demonstrate that not try-catching checked exception 
 * will cause compile error
 * 
 * for runtime error it will not
 * the objective is that runtime exceptions should be fixed and investigated
 * not catched and do stop gap measure
 * 
 * Checked exceptions are descendent of Exception class 
 * but not descendent of RuntimeException (which itself is subclass of Exception)
 * 
 * Runtime exceptions are subclass of RuntimeException
 */
public class ExceptionThrowMethodExample {
    /**
     * method throwing checked exception
     * 
     * @param param
     * @return
     * @throws FileNotFoundException
     */
    public static int exceptionThrowCheckingException (int param) throws FileNotFoundException {
        return param;
    }
    /**
     * method throwing runtime exception
     * this is not a good practice 
     * if you do it with intention that method user will not be forced to handle exception
     * 
     * RuntimeExceptions are for exception which are numerous or related to programming error
     * they are to represent recoverable issue with programming
     * 
     * They can be numerous so catching them is problematic but you may catch them
     * 
     * @param param
     * @return
     * @throws NullPointerException
     */
    public static int exceptionThrowRuntimeException (int param) throws NullPointerException {
        return param;
    }

    /**
     * method throwing Error
     * Error and subclasses are to represent unrecoverable error
     * 
     * @param param
     * @return
     * @throws NullPointerException
     */
    public static int exceptionThrowError(int param) throws Error {
        return param;
    }
    public static void main (String args[]) {
        /* 
           following line will give error at comopile time
           because FileNotFoundException is checked exception or compile time exception
        */
        // exceptionThrowCheckingException(0);
        // following is fine
        try {
           throw new CustomCheckedException();
            // exceptionThrowCheckingException(0);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        // following line will not give errort at compile time
        // because the exception it throws is extended from RuntimException
        // which is not checked at compile time that if it is handled
        //
        // it is expected that runtime exceptions are generally unrecoverable
        // or numerous
        // so rather than cathcing and handle differently find the actual cause
        exceptionThrowRuntimeException(0);
        // try {
        //     exceptionThrowRuntimeException(0);
        // }
        // catch (Exception e) {
        //     System.out.println(e.getCause());
        //     e.printStackTrace(System.out);
        // }
        // compiler does not enforce to do try catch for Error throwers
        exceptionThrowError(0);
    }
}