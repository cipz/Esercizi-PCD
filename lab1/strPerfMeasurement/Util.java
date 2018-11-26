package lab1.strPerfMeasurement;


public class Util {

	public static double[] computeSampleStats(long[] string_stats, 
			long[] buffer_stats, 
			long[] builder_stats) {
		
		double mean_string = computeMean(string_stats);
		double stddev_string = computeStdDev(string_stats, mean_string);
		
		double mean_buffer = computeMean(buffer_stats);
		double stddev_buffer = computeStdDev(buffer_stats, mean_buffer);
		double mean_builder = computeMean(builder_stats);		
		double stddev_builder = computeStdDev(builder_stats, mean_builder);

		return new double[]{mean_string, stddev_string, 
							mean_buffer, stddev_buffer, 
							mean_builder, stddev_builder};
	}
	
	private static double computeMean(long[] sample) {
		if(sample == null || sample.length < 1 ) return 0d;
		double sum =0d;
		for(long v: sample) {
			sum+=v;
		}
		
		return sum/sample.length;
	}
	
	private static double computeStdDev(long[] sample, double mean) {

		if(sample == null || sample.length < 1 ) return 0d;

		double pow_sum=0d;
		for(long v: sample) {
			pow_sum+=Math.pow(v-mean, 2);
		}
		
		return Math.sqrt(pow_sum/sample.length);
	}
	
}
