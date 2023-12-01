import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

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
        if (!connectionSuccess) {
            return;
        }

        if (isClient) {
            try{
                // print client prop
                System.out.printf(
                    "Client connected to %s:%d\n",
                    socket.getRemoteSocketAddress().toString(), socket.getPort());

                InputStream in = socket.getInputStream();
                OutputStream out = socket.getOutputStream();
                // for convenience create a character stream
                InputStreamReader reader = new InputStreamReader(in);
                OutputStreamWriter writer = new OutputStreamWriter(out);

                Scanner sc = new Scanner(System.in);
                // our protocol is client first reads from server;                
                while (!socket.isClosed()) {
                    char[] buf = new char[1024];
                    int len = reader.read();
                    // read from client
                    String serverResponse = String.copyValueOf(buf, 0, len);
                    if (serverResponse.equals("done")) {
                        break;
                    }

                    System.out.printf("Server: %s %d\n", serverResponse, serverResponse.length());
                    System.out.printf(">>>");
                    writer.write(sc.nextLine());
                    writer.flush();
                }

                sc.close();
                socket.close();
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
                writer.flush();
                while (!clientConnection.isClosed() && !clientResponse.equals("exit")) {
                    char[] buf = new char[1024];
                    // read from client
                    int len = reader.read(buf);
                    clientResponse = String.copyValueOf(buf, 0, len);
                    System.out.printf("Client: %s %d\n", clientResponse, clientResponse.length());

                    // write something to client
                    System.out.print(">>>");
                    writer.write(sc.nextLine());
                    writer.flush();
                }
                writer.write("done");

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
            new TCPSocketComExample(true).start();
        }
        else {
            new TCPSocketComExample(false).start();
        }
    }
}
