import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A chat application using TCP socket between localhost application
 * 
 * The application server listens at 55281
 */
public class TCPSocketComExample extends Thread {
    ServerSocket sSocket;
    Socket socket;
    boolean connectionSuccess;
    boolean isClient;
    static int SERVERPORT = 55281;
    static int MAXATTEMPT = 10;

    public TCPSocketComExample(boolean isClient) {
        connectionSuccess = false;
        this.isClient = isClient;
        if (isClient) {
            // will try to connect 10 times
            int connectionAttempt = 1;
            while (connectionAttempt <= MAXATTEMPT) {
                try {
                    socket = new Socket("localhost", SERVERPORT);
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
                sSocket = new ServerSocket(SERVERPORT);
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
            while (!clientResponse.equals("exit")) {
                char[] buf = new char[1024];
                

                writer.write("hello");
                // read from client

                reader.read(buf);

                clientResponse = String.valueOf(buf);
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
                writer.write("hello");
                while (!clientResponse.equals("exit")) {
                    char[] buf = new char[1024];
                    
                    // read from client
                    reader.read(buf);
                    clientResponse = String.valueOf(buf);
                }

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
            new TCPSocketComExample(true).start();
        }
        else {
            new TCPSocketComExample(false).start();
        }
    }
}
