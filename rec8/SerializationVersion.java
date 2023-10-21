import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * This is to demonstrate the Serialization of class 
 * using Serializable interface
 */
public class SerializationVersion implements Serializable {
    // this is needed to identify which version of the class
    // has saved the object in binary
    //
    // this number is stored in object binary format to indicate version
    // why it's necessary?
    // say, you have written an application which is deployed
    // that application data maybe saved as object
    //
    // now if you change your class definition for new 
    // version of application, should you load from previos version
    // class binary? You probably should not as that may cause
    // garbage data in class
    //
    // java check this number in saved object binary
    // if don't match will throw InvalidClassException
    //
    // to experiment, first save object with one UID,
    // then change this number, recompile and read from the
    // saved binary
    static final long serialVersionUID = 12345678L;
    private int field1;
    private int field2;

    SerializationVersion(int field1, int field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public String toString() {
        return "The object contains : " + field1 + ", " + field2;
    }

    /**
     * main method for serialization versioning example
     * pass cmd line argument "read" to
     * read from rather than write to ObjectBinary.bin
     *
     * @param
     * String args[] cmd line argument
     */
    public static void main(String args[]) {
        // from the given argument decide if to read or to write
        // by default it will write
        boolean readObject = false;
        if (args.length > 0 && args[0].equals("read")) {
            readObject = true;
        }

        // create a serializable class
        SerializationVersion sVersion = new SerializationVersion(10, 200);

        // object output stream will throw checked exception IOException
        try {
            if (!readObject) {
                ObjectOutputStream outputStream = new ObjectOutputStream(
                    new FileOutputStream("ObjectBinary.bin", false));
                outputStream.writeObject(sVersion);
                outputStream.close();
            }
            else {
                ObjectInputStream objectInputStream =
                new ObjectInputStream(new FileInputStream("ObjectBinary.bin"));
                SerializationVersion sVersion2 = 
                (SerializationVersion)objectInputStream.readObject();
                objectInputStream.close();
                System.out.println(sVersion2);
            }
        }
        catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
        }
    }
}
