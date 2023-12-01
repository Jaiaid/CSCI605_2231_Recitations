import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * A chat application using UDP socket between localhost application
 * 
 * The application server listens at 55281
 */
public class UDPSocketComExample extends Thread {
    DatagramSocket ds;
    boolean connectionSuccess;
    boolean isClient;
    static int PORT1 = 55281;
    static int PORT2 = 55282;
    static int MAXATTEMPT = 10;

    public UDPSocketComExample(boolean isClient) {
        connectionSuccess = false;
        this.isClient = isClient;
        if (isClient) {
            // will try to connect 10 times
            int connectionAttempt = 1;
            while (connectionAttempt <= MAXATTEMPT) {
                try {
                    ds = new DatagramSocket(PORT1);
                    connectionSuccess = true;
                    break;
                }
                catch (Exception e) {
                    System.out.printf("server not found at attempt %d...\n", connectionAttempt);
                    // attempt connection at least 1s after
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
                connectionAttempt += 1;
            }
            if (connectionAttempt > MAXATTEMPT) {
                System.out.printf("server not found, will exit...");
            }
        }
        else {
            try {
                ds = new DatagramSocket(PORT2);
                connectionSuccess = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.printf("server creation failed, exiting");
            }
        }
    }

    public void run() {
        if (isClient) {
            try{
                // print client prop
                System.out.printf(
                    "Client connected from %s:%d\n",
                    ds.getRemoteSocketAddress().toString(), ds.getPort());

                // our protocol is client first reads from server; 
                // no stream I/O
                // just send and receive              
                while (true) {
                    char[] buf = new char[1024];
                    
                    reader.read(buf);
                    writer.write("hello");
                    // read from client

                    clientResponse = String.valueOf(buf);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }


        }
        else {
            try {
                // ServerSocket wait for connection through this 
                Socket clientConnection = sSocket.accept();
                // print client prop
                System.out.printf(
                    "Client connected from %s:%d\n",
                    clientConnection.getRemoteSocketAddress().toString(), clientConnection.getPort());

                InputStream in = clientConnection.getInputStream();
                OutputStream out = clientConnection.getOutputStream();
                // for convenience create a character stream
                InputStreamReader reader = new InputStreamReader(in);
                OutputStreamWriter writer = new OutputStreamWriter(out);

                // our protocol is server first writes hello to client;                
                String clientResponse = "continue";
                Scanner sc = new Scanner(System.in);
                writer.write("hello");
                while (!clientResponse.equals("exit")) {
                    char[] buf = new char[1024];
                    // read from client
                    reader.read(buf);
                    clientResponse = String.valueOf(buf);

                    // write something to client
                    System.out.print(">>>");
                    writer.write(sc.nextLine());
                }

                sc.close();
                sSocket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        // could shorten the block by using (args[0].equals("c")) in parameter
        if (args[0].equals("c")) {
            new UDPSocketComExample(true).start();
        }
        else {
            new UDPSocketComExample(false).start();
        }
    }
}
