package interfaces;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import detection.requirements.Requirements;
import detection.requirements.SizeRequirements;

public class SelfPartitionedCloneDetection {

	public static void main(String args[]) throws IOException {
		if(args.length != 9) {
			System.out.println("Usage: blockfile clonefile numThreads minLines maxLines minTokens maxTokens minSimilarity maxBlockSize");
			System.out.println("        blockfile: Path to file containing the blocks.");
			System.out.println("        clonefile: Path to file to write clones to.");
			System.out.println("       numThreads: Number of threads to use per parallelized task.");
			System.out.println("         minLines: Minimum clone size in lines.");
			System.out.println("         maxLines: Maximum clone size in lines.");
			System.out.println("        minTokens: Minimum clone size in tokens.");
			System.out.println("        maxTokens: Maximum clone size in tokens.");
			System.out.println("    minSimilarity: Minimum clone similarity.");
			System.out.println("     maxBlockSize: Maximum number of blocks to index at one time.");
			System.exit(-1);
		}
		
		Path input = Paths.get(args[0]);
		Path output = Paths.get(args[1]);
		
		int numThreads = Integer.parseInt(args[2]);
		
		int minLines = Integer.parseInt(args[3]);
		int maxLines = Integer.parseInt(args[4]);
		int minTokens = Integer.parseInt(args[5]);
		int maxTokens = Integer.parseInt(args[6]);
		double sim = Double.parseDouble(args[7]);
		int maxBlockSize = Integer.parseInt(args[8]);
		
		Requirements requirements = new SizeRequirements(minLines /*minLines*/, maxLines /*maxLines*/, minTokens /*minTokens*/, maxTokens /*maxTokens*/);
		
		long time = System.currentTimeMillis();
		
		detection.logic.SelfPartitionedCloneDetection.detect(
									      /*Input Blocks*/ input,
									      /*Clone Output*/ output,
									 /*Block Requirement*/ requirements,
									/*Minimum Similarity*/ sim,
								          /*MaxBlocKSize*/ maxBlockSize,
									 /*Number of Threads*/ numThreads
								);
		
		System.out.println("Detection time: " + 1.0*(System.currentTimeMillis()-time)/1000 + " seconds.");
		
	}
	
}