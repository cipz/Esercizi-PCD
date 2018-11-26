package merkleClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Main {

	public static final void main(String args[]) throws IOException, InterruptedException {
		
		String merkleRoot = HashUtil.md5Java("0000000000");
		String merkleTx_1 = HashUtil.md5Java("0000000001");
		String merkleTx_2 = HashUtil.md5Java("0000000020");

		Map<Boolean, List<String>> report = new MerkleValidityRequest.Builder("127.0.0.1", 2323, merkleRoot)
																	.addMerkleValidityCheck(merkleTx_1)
								 									.addMerkleValidityCheck(merkleTx_2)
								 									.build()
								 									.checkWhichTransactionValid();
		//Print the valid transactions.
		report.entrySet().stream()
						 .filter(Entry::getKey)
						 .forEach(Entry::getValue);

		for(Boolean bool : report.keySet()) {
			System.out.println(bool);
			for (String str : report.get(bool))
				System.out.println(str);
			System.out.println();
		}//for

	}//main

}//Main