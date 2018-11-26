package merkleClient;

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

		Socket serverSocket  = null;
		PrintWriter out = null;
		BufferedReader in = null;

		try {
			serverSocket = new Socket(authIPAddr, authPort);
			out = new PrintWriter(serverSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
		} catch (UnknownHostException e) {
			System.err.println("Unknown Host: " + authIPAddr);
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O from: " + authIPAddr);
			System.exit(1);
		}//try_catch_catch

		for (String currentTransaction : mRequests){
			out.println(currentTransaction);

			String nodesFromServer = in.readLine();

			System.out.println(nodesFromServer);

			List<String> merkleNodes = Arrays.asList(nodesFromServer.split(","));

			Boolean currentTransactionValidity = isTransactionValid(currentTransaction, merkleNodes);

			transactionValidityMap.get(currentTransactionValidity).add(currentTransaction);

		}//for

		out.println("close");

		serverSocket.close();
		out.close();
		in.close();

		return transactionValidityMap;

	}//checkWhichTransactionValid

	private static void log(String str) {
		System.out.println(str);
	}//log()
	
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

		String hashedMerkleTx = HashUtil.md5Java(merkleTx);

		List<String> newMerkleNodes = new ArrayList<>();

		newMerkleNodes.add(hashedMerkleTx);
		newMerkleNodes.addAll(merkleNodes);

		String computedRoot = newMerkleNodes.stream().reduce((x, y) -> HashUtil.md5Java(x.concat(y))).get();

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