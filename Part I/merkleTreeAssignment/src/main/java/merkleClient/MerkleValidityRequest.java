package merkleClient;

import javax.sound.midi.SysexMessage;
import java.io.*;
import java.net.*;
import java.util.*;

public class MerkleValidityRequest {

	/**
	 * IP address of the authority
	 * */
	private final String authIPAddr;
	/**
	 * Port number of the authority
	 * */
	private final int  authPort;
	/**
	 * Hash value of the merkle tree root. 
	 * Known before-hand.
	 * */
	private final String mRoot;
	/**
	 * List of transactions this client wants to verify 
	 * the existence of.
	 * */
	private List<String> mRequests;
	
	/**
	 * Sole constructor of this class - marked private.
	 * */
	private MerkleValidityRequest(Builder b){
		this.authIPAddr = b.authIPAddr;
		this.authPort = b.authPort;
		this.mRoot = b.mRoot;
		this.mRequests = b.mRequest;
	}
	
	/**
	 * <p>Method implementing the communication protocol between the client and the authority.</p>
	 * <p>The steps involved are as follows:</p>
	 * 		<p>0. Opens a connection with the authority</p>
	 * 	<p>For each transaction the client does the following:</p>
	 * 		<p>1.: asks for a validityProof for the current transaction</p>
	 * 		<p>2.: listens for a list of hashes which constitute the merkle nodes contents</p>
	 * 	<p>Uses the utility method {@link #isTransactionValid(String, String, List<String>) isTransactionValid} </p>
	 * 	<p>method to check whether the current transaction is valid or not.</p>
	 * */
	public Map<Boolean, List<String>> checkWhichTransactionValid() throws IOException {

		Map<Boolean, List<String>> transactionValidityMap = new HashMap<>();
		transactionValidityMap.put(true, new ArrayList<>());
		transactionValidityMap.put(false, new ArrayList<>());

		PrintWriter out;
		BufferedReader in;

		try (Socket serverSocket = new Socket(authIPAddr, authPort)) {

			System.out.println("New connection to: " + authIPAddr + " : " + authPort);

			// Opening input / output channels
			out = new PrintWriter(serverSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

			for (String currentTransaction : mRequests){

				System.out.print("Verifying " + currentTransaction + " : ");

				// Sending transaction to server
				out.println(currentTransaction);

				// Get list of nodes from server
				String nodesFromServer = in.readLine();
				List<String> merkleNodes = Arrays.asList(nodesFromServer.split(","));

				// Check the validity of the transaction
				Boolean currentTransactionValidity = isTransactionValid(currentTransaction, merkleNodes);
				System.out.println(currentTransactionValidity );

				// Put the transaction in the map
				transactionValidityMap.get(currentTransactionValidity).add(currentTransaction);

			}//for

			// Sending close message to the server
			out.println("close");

			// Closing the connection to the server
			serverSocket.close();
			out.close();
			in.close();

		} catch (UnknownHostException e) {
			System.err.println("Unknown Host: " + authIPAddr + "\nError: " + e);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O from: " + authIPAddr + "\nError: " + e);
		}//try_catch_catch

		return transactionValidityMap;

	}//checkWhichTransactionValid

	/**
	 * 	Checks whether a transaction 'merkleTx' is part of the merkle tree.
	 * 
	 *  @param merkleTx String: the transaction we want to validate
	 *  @param merkleNodes String: the hash codes of the merkle nodes required to compute 
	 *  the merkle root
	 *  
	 *  @return: boolean value indicating whether this transaction was validated or not.
	 * */
	private boolean isTransactionValid(String merkleTx, List<String> merkleNodes) {

		// Hashing the merkle transaction that need validation
		String hashedMerkleTx = HashUtil.md5Java(merkleTx);

		List<String> newMerkleNodes = new ArrayList<>();

		newMerkleNodes.add(hashedMerkleTx);
		newMerkleNodes.addAll(merkleNodes);

		String computedRoot = newMerkleNodes.stream().reduce((x, y) -> HashUtil.md5Java(x.concat(y))).get();

		// If the computed root is equal to the given root then returns true
		return computedRoot.equals(mRoot);

	}//isTransactionValid

	/**
	 * Builder for the MerkleValidityRequest class. 
	 * */
	public static class Builder {
		private String authIPAddr;
		private int authPort;
		private String mRoot;
		private List<String> mRequest;	
		
		public Builder(String authorityIPAddr, int authorityPort, String merkleRoot) {
			this.authIPAddr = authorityIPAddr;
			this.authPort = authorityPort;
			this.mRoot = merkleRoot;
			mRequest = new ArrayList<>();
		}
				
		public Builder addMerkleValidityCheck(String merkleHash) {
			mRequest.add(merkleHash);
			return this;
		}
		
		public MerkleValidityRequest build() {
			return new MerkleValidityRequest(this);
		}
	}
}