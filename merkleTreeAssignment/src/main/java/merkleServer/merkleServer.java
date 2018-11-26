package merkleServer;

import java.net.*;
import java.io.*;

// Reference site: https://www.cs.uic.edu/~troy/spring05/cs450/sockets/socket.html
public class merkleServer {

    protected static merkleTree mTree = new merkleTree();

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(2323)) {

            System.out.println("Starting server...");

            try{

                // Always listen for a connection incoming from clients
                while (true){

                    System.out.println("Waiting for a new connection...");
                    Socket clientSocket = serverSocket.accept();

                    // When a new connection arrives a new Thread is created
                    System.out.println("Connection request from: " + clientSocket.getInetAddress());
                    new merkleServerThread(clientSocket, mTree);

                }//while

            }catch (IOException e){
                System.err.println("Failed client connection\nError: " + e);
            }finally {

                // In case of Exceptions after these are catched, the serverSocket is closed
                serverSocket.close();

            }//try_catch

        } catch (IOException e) {
            System.err.println("Error listening error on port: 2323\nError: " + e);
        }//try_catch

    }//main
}//merkleServer