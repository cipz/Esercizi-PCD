package merkleServer;

import java.net.*;
import java.io.*;

public class merkleServer {

    protected static merkleTree mTree = new merkleTree();

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        System.out.println("Starting server...");

        try {
            serverSocket = new ServerSocket(2323);
        } catch (IOException e) {
            System.err.println("Listening error on port: 2323");
            System.exit(1);
        }//try_catch

        Socket clientSocket;

        try {

            while(true){
                System.out.println("Waiting for connection...");
                clientSocket = serverSocket.accept();
                System.out.println("Connection request from: " + clientSocket.getInetAddress());
                new merkleServerThread(clientSocket, mTree);
            }//while

        } catch (IOException e) {

            System.err.println("Failed client connection");
            System.exit(1);

        }finally {

            serverSocket.close();

        }//try_catch_finally

    }//main
}//merkleServer