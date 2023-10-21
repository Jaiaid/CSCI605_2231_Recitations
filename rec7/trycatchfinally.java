/**
 * To demonstrate finally behavior
 */
public class trycatchfinally {
    /**
     * throw exception based on input argument
     * pass 0 to throw otherwise dont
     * 
     * @param param int param to decide if to throw error or not
     */
    public static void throwerMethod(int param) {
        try {
            if (param == 0) {
                throw new Exception();
            }
            System.out.println("Exception not thrown");
            // return before finally will it execute?
            return;
        }
        catch (Exception e) {
            System.out.println("Exception thrown");
            // return before finally, will it execute?
            return;
        }
        finally {
            System.out.println("finally block executed");
        }
    }
    public static void main(String args[]) {
        // this call will throw exception and catch
        throwerMethod(0);
        // this won't
        throwerMethod(1);
    }
}
