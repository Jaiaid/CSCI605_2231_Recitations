import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Demonstration of port number property of a connection endpoint
 * Namely Socket
 * 
 * We will show TCP and UDP protocol Socket here
 * For this two protocol the port number space is separate
 */
public class PortNumberExample {
    static InetSocketAddress inetAddress;
    static DatagramSocket dSocket;
    static Socket socket;
    public static void main(String args[]) {
        inetAddress = new InetSocketAddress("127.0.0.1", 55231);
        DatagramSocket dSocket = new DatagramSocket(0, ;
    }
}
