package lab1.strPerfMeasurement;

public class StringPerformance {

	public static int MAX_RUNS = 20;
	
	public static void main(String args[]) {

		/** Stat. container for String operations */
		long[] string_stats = new long[MAX_RUNS];
		/** Stat. container for StringBuffer operations */
		long[] buffer_stats = new long[MAX_RUNS];
		/** Stat. container for StringBuilder operations */
		long[] builder_stats = new long[MAX_RUNS];
		/** Variables containing values of start end time of measurement */
		long start, end;

		for(int run=0; run<MAX_RUNS; run++) {
			
			//append operation for String
			start = System.currentTimeMillis();
			String s = new String();
			for(int elm=0; elm<Math.pow(10, 4); elm++) {
				s = s.concat(String.valueOf(elm));
			}
			end = System.currentTimeMillis();
			string_stats[run] = end-start;

			//append operation for StringBuffer
			start = System.currentTimeMillis();
			StringBuffer buffer = new StringBuffer();
			for(int elm=0; elm<Math.pow(10, 5); elm++) {
				buffer.append(elm);
			}		
			end = System.currentTimeMillis();
			buffer_stats[run] = end-start;

			//append operation for StringBuilder
			start = System.currentTimeMillis();
			StringBuilder builder = new StringBuilder();
			for(int elm=0; elm<Math.pow(10, 5); elm++) {
				builder.append(elm);
			}		
			end = System.currentTimeMillis();
			builder_stats[run] = end-start;
		}
		 
		double[] stats = Util.computeSampleStats(string_stats, buffer_stats, builder_stats);
		System.out.println("StringStats: AVG " + stats[0] + " StdDev " + stats[1]);
		System.out.println("BufferStats: AVG " + stats[2] + " StdDev " + stats[3]);
		System.out.println("BuilderStats: AVG " + stats[4] + " StdDev " + stats[5]);		
	}
}
