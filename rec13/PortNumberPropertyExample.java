import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;

/**
 * Demonstration of port number property of a connection endpoint
 * Namely Socket
 * Socket is connection endpoint primitve for Host to Host layer of DoD model
 * Host to Host and Internet layer are closely related
 * So, you have to use Internet Layer endpoint address (IP adress) to create Socket
 * 
 * Socket is differntiated by two things
 * 1. which protocol it belongs to
 * 2. which port number it is using in the protocol
 * 
 * We will show TCP and UDP protocol Socket here
 * For this two protocol the port number space is separate
 */
public class PortNumberPropertyExample {
    // for udp socket
    static DatagramSocket dSocket;
    // for tcp socket
    // it can be used to create datagram socket but that's deprecated
    static ServerSocket socket;
    // port for both
    static int PORT = 55281;
    public static void main(String args[]) {
        try {
            // what is the local address
            // InetAddress Class is used to create InetAddress for particular host
            // It does not have any visible constructor
            System.out.printf("Local address: %s\n", InetAddress.getLocalHost().getHostName());
            // Let's open a datagram/udp socket
            // we have to define two things
            // 1. which host
            // 2. what is the port number (unique per protocol)
            dSocket = new DatagramSocket(PORT, InetAddress.getLocalHost());
            // similary for tcp socket
            // note we have two endpoint object here
            // ServerSocket and Socket
            // that's because TCP is connection oriented
            // so for a TCP endpoint, you either
            // 1. wait for other to connect to you
            // 2. try to connect with others
            // Here we only wants to show example of TCP socket without any remote connection
            // Hence, we are using ServerSocket
            // try to create Socket, you will see that we have to provide with whom to connect
            // https://stackoverflow.com/questions/2004531/what-is-the-difference-between-socket-and-serversocket
            socket = new ServerSocket(PORT);
            
            System.out.printf("socket port = %d, udp socket port = %d\n", socket.getLocalPort(), dSocket.getLocalPort());

            // now let's try to create a Socket on same port
            try {
                ServerSocket socket1 = new ServerSocket(PORT);
                socket1.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.printf("Another TCP socket creation on port %d failed\n", PORT);
            }

            // now let's try to create a Datagram Socket on same port
            try {
                DatagramSocket dSocket1 = new DatagramSocket(PORT, InetAddress.getLocalHost());
                dSocket1.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.printf("Another Datagram socket creation on port %d failed\n", PORT);
            }

            // now how many port are there?
            // 0-65535
            // what will happen if try to open to 65536?
            // now let's try to create a Datagram Socket on same port
            try {
                DatagramSocket dSocket1 = new DatagramSocket(65536, InetAddress.getLocalHost());
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.printf("Datagram socket creation on port 65536 failed\n");
            }

            socket.close();
            dSocket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
