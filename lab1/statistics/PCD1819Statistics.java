package lab1.statistics;

import java.io.File;
import java.io.IOException;
import java.time.Month;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class can be considered as a final helper class used to compute the balance sheet of a company.  
 * The program takes as arguments 2 filenames corresponding to the balance_input_file and balance_output_file,
 * respectively. 
 * An entry in the balance input file represents the monthly budget of the company for that specific year.
 * The output file format is a synthesized version of the former, reporting the mean and average balance for 
 * a particular year.
 * For more insights on the file format refer to the provided {input, output}.txt files.
 */

public final class PCD1819Statistics {

	/**
	 * Fills in the MultiMap data structure containing the raw data read from the input file.
	 * 
	 * @param map:			the map structure to fill with data
	 * @param inputFilePath	the path of the input balance sheet file 
	 * 
	 * @throws IOException  if the file denoted by the inputFilePath parameter does not exist
	 */
	public static void buildMapFromInput(MultiMap<Integer, Double> map, String inputFilePath) {

		BufferedReader reader = null;

		try {

			File file = new File(inputFilePath);
			reader = new BufferedReader(new FileReader(file));
		
			// Read first line that contains intestation of table
			String line = reader.readLine();
			while ((line = reader.readLine()) != null){
				String[] val = line.split("\t");
				for(int i = 1; i < val.length; i++){
					map.put(Integer.parseInt(val[0]), Double.parseDouble(val[i]));
				}//for
			}//while
			
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				
				reader.close();

			} catch (IOException e) {

				e.printStackTrace();

			}//try_catch

		}//try_catch_finally

	}//buildMapFromInput

	/**
	 * Fills in the MultiMap data structure containing the raw data read from the input file.
	 * 
	 * @param map:			the map structure to fill with data
	 * @param inputFile		the path of the input balance sheet file 
	 * 
	 * @throws IOException  if the file denoted by the inputFilePath parameter does not exist
	 * @throws IllegalArgumentException if the input parameters are not valid
	 */
	public static void buildMapFromInput(MultiMap<Integer, Double> map, File inputFile) throws IOException {

		BufferedReader reader = null;

		try {

			reader = new BufferedReader(new FileReader(inputFile));
		
			// Read first line that contains intestation of table
			String line = reader.readLine();
			while ((line = reader.readLine()) != null){
				String[] val = line.split("\t");
				for(int i = 1; i < val.length; i++){
					map.put(Integer.parseInt(val[0]), Double.parseDouble(val[i]));
				}//for
			}//while
			
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {
				
				reader.close();

			} catch (IOException e) {

				e.printStackTrace();

			}//try_catch

		}//try_catch_finally

	}//buildMapFromInput

	/**
	 * Produces the synthesized version of the input balance file from the data stored in the multimap data structure.
	 * 
	 * @param map:			a MultiMap containing the balance data
	 * @param balanceOut:	file where the synthesized version of the balance sheet is stored
	 * 
	 * @throws IOException: if the I/O operation fails
	 * @throws IllegalArgumentException: if the input parameters are not safe
	 */
	public static void outputStatisticsFile(MultiMap<Integer, Double> map, File balanceOut) throws IOException {
		
		//https://www.baeldung.com/java-write-to-file
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(balanceOut));
		
		writer.append("\tMean\tStdDev\n");
		
		for(Integer i : map.keySet()) {
			writer.append(i + "\t");
			for(Double d : map.get(i))
				writer.append(d + "\t");
			writer.append("\n");
		}//for
		
		writer.close();
		
	}//outputStatisticsFile
	
	/**
	 * Produces a synthesized version of the input balance sheet. This method can be seen as a sequential pipeline
	 * combining the buildMapFromInput(...) method and the outputStatisticsFile(...)
	 * 
	 * @param inputFile:	denotes the balance sheet file path	
	 * @param outputFile:	denotes the file where the output balance sheet should be stored
	 * 
	 * @throws IOException: in case some I/O error occurred e.g., the file does not adhere to a specific format or an
	 * 						unexpected error during read/write.
	 * @throws IllegalArgumentException: in case input parameters are not valid
x	 */
	public static void produceSynthetizedBalanceSheet(String inputFile, String outputFile) {
		
		MultiMap<Integer, Double> map = new MultiMap<Integer, Double>();
		
		buildMapFromInput(map, inputFile);
		
		try {
			
			File file = new File(inputFile);
			buildMapFromInput(map, file);
			
		} catch (IOException e) {
			
			System.err.print("Error:" + e);
			
		}//try_catch
		
		for(Integer k : map.keySet()) {
			
			Double avg = 0.0;
			Double stdDev = 0.0;
			int n = map.get(k).size();
			
			for(Double val : map.get(k))
				avg += val;
			avg /= n;
			
			for(Double val : map.get(k))
				stdDev += val-avg;
			stdDev /= n;
			stdDev = Math.sqrt(stdDev);
			
			map.get(k).clear();
			map.get(k).add(avg);
			map.get(k).add(stdDev);
			
		}//for
		
		
		
		try {
			
			File file  = new File(outputFile);
			outputStatisticsFile(map, file);
			
		} catch (IOException e) {
			
			System.err.print("Error:" + e);
			
		}//try_catch

		
	}//produceSynthetizedBalanceSheet
	
	public static void main(String[] args) {

		String inputPath = "/home/cip/Desktop/UNI/P3/LAB/src/lab1/statistics/file/input.txt";
		String outputPath = "/home/cip/Desktop/UNI/P3/LAB/src/lab1/statistics/file/output.txt";
		
		produceSynthetizedBalanceSheet(inputPath, outputPath);
				
		System.out.println("OK!");

		
	}//main
	
}


	