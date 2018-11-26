package merkleServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public class merkleServerThread extends Thread {

    private Socket clientSocket;
    private merkleTree mTree;

    public merkleServerThread(Socket clientSocket, merkleTree mTree){

        this.clientSocket = clientSocket;
        this.mTree = mTree;
        start();

    }//merkleServerThread constructor

    @Override
    public void run(){

        System.err.println ("New server thread started #" + currentThread().getId());

        try {

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));

            String inputLine = in.readLine();

            while ((inputLine != null) && (!inputLine.equals("close"))) {

                System.out.println ("#" + currentThread().getId() + " request to verify: " + inputLine);

                List<String> outputNodes = mTree.getNodesForValidation(inputLine);
                String outputNodesString = outputNodes.stream().collect(Collectors.joining(","));

                // Sending list of nodes required to verify the requested transaction to the client
                out.println(outputNodesString);

                // Read new request
                inputLine = in.readLine();

            }//while

            System.err.println("Closing thread #" + currentThread().getId());

            clientSocket.close();
            out.close();
            in.close();

        } catch (IOException e) {
            System.err.println("I/O exception with client");
            System.exit(1);
        }//try_catch

    }//run

}//merkleServerThread