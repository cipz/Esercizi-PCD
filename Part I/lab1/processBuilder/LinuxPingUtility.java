package lab1.processBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LinuxPingUtility {
	 
	
	private class IOErrStream extends Thread {
		InputStream inputStream = null;
		String type;
 
		// This abstract class is the superclass of all classes representing an input stream of bytes.
		IOErrStream(InputStream is, String type) {
			this.inputStream = is;
			this.type = type;
		}
 
		public void run() {
			String response = null;
			// Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
			try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){ 
				while ((response = br.readLine()) != null) {
					switch(type) {
						case "err": {System.err.println(response);break;}
						case "out": {System.out.println(response);break;}
						default: {}
					}

				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
 
	public IOErrStream getStreamResult(InputStream inputStream, String type) {
		return new IOErrStream(inputStream, type);
	}
 
	public static void main(String[] args) {
 
		// Returns the runtime object associated with the current Java application.
		Runtime runTime = Runtime.getRuntime();
		LinuxPingUtility rte = new LinuxPingUtility();
		IOErrStream error, res;
 
		try {
			Process proc1 = runTime.exec("ping www.google.com");
			error = rte.getStreamResult(proc1.getErrorStream(), "err");
			res = rte.getStreamResult(proc1.getInputStream(), "out");
			error.start();
			res.start();
 
			// Signals that an I/O exception of some sort has occurred.
		} catch (IOException exception) {
			exception.printStackTrace();
		}
 
	}
 
}
