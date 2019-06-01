package edu.handong.analysis;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.io.File;

public class Main {
	
	
	public static void main(String[] args) {
		HGUCoursePatternAnalyzer analyzer = new HGUCoursePatternAnalyzer();
		analyzer.run(args);
		
		Main main=new Main();
		main.run(args);
	}
	
	private void run(String[] args)
	{
		
	}
	
	private boolean parseOptions(Option options,)
}