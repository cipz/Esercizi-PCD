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

        System.out.println ("New server thread started " + currentThread().getId());

        try {

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));

            String inputLine = in.readLine();

            while (inputLine != null) {

                if(inputLine.equals("Close"))
                    break;

                //https://www.cs.uic.edu/~troy/spring05/cs450/sockets/EchoServer2b.java
                // TODO: ricevere dal client la stirnga da validare, simulare la richiesta di creazione dei nodi, mandarli come singola stirnga separati da ,

                System.out.println (currentThread().getId() + " server: " + inputLine);

                List<String> outputNodes = mTree.getNodesForValidation(inputLine);
                String outputNodesString = outputNodes.stream().collect(Collectors.joining(","));

                out.println(outputNodesString);

                inputLine = in.readLine();

            }//while

            out.close();
            in.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("I/O exception with client");
            System.exit(1);
        }//try_catch

    }//run

}//merkleServerThread