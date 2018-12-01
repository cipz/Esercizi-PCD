package merkleServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implementing a thread that acts as a server for a single client
 */
public class merkleServerThread extends Thread {

    private Socket clientSocket;
    private merkleTree mTree;

    /**
     *
     * Sole constructor of this class - marked public.
     *
     * @param clientSocket Socket: socket on which a communication with the client is required
     * @param mTree merkleTree: object that contains all the nodes of the tree
     */
    public merkleServerThread(Socket clientSocket, merkleTree mTree){

        this.clientSocket = clientSocket;
        this.mTree = mTree;
        start();

    }//merkleServerThread constructor

    /**
     * Method that contains the communication with the client
     * Can be concurrent with other clients
     */
    @Override
    public void run(){

        System.err.println ("New server thread started #" + currentThread().getId());

        try {

            // Creating input and output objects
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));

            // Reading input from client
            String inputLine = in.readLine();
            while ((inputLine != null) && (!inputLine.equals("close"))) {

                System.out.println ("#" + currentThread().getId() + " request to verify: " + inputLine);

                // Interacting with the merkleTree object to get the nodes needed to validate the client string
                List<String> outputNodes = mTree.getNodesForValidation(inputLine);
                String outputNodesString = outputNodes.stream().collect(Collectors.joining(","));

                // Sending list of nodes required to verify the requested transaction to the client
                out.println(outputNodesString);

                // Read new request
                inputLine = in.readLine();

            }//while

            System.err.println("Closing thread #" + currentThread().getId());

            // Closing the connection with the client
            clientSocket.close();
            out.close();
            in.close();

        } catch (IOException e) {
            System.err.println("I/O exception with client\nError: " + e);
        }//try_catch

    }//run

}//merkleServerThread