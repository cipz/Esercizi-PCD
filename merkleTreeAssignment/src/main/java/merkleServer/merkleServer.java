package merkleServer;

import java.net.*;
import java.io.*;

/**
 * Class that implements the server object that listens for incoming client communication requests
 * and for each one opens a new Socket that is then passed to the serverThread object so that the server can
 * listen for other incoming connection requests
 *
 * */
public class merkleServer {

    private static merkleTree mTree = new merkleTree();

    public static void main(String[] args) throws IOException {

        // Trying to open the socket to listen for connections
        try (ServerSocket serverSocket = new ServerSocket(2323)) {

            System.out.println("Starting server...");

            try{

                // Always listen for a connection incoming from clients
                while (true){

                    System.out.println("Waiting for a new connection...");

                    //Blocking method that waits for a connection request to arrive
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