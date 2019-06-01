package edu.handong.analysis;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.io.File;

public class Main {
	
	String inputpath;
	String outputpath;
	String startyear;
	String endyear;
	String opt;
	boolean help=false;
	
	public static void main(String[] args) {
		
		Main main=new Main();
		main.run(args);
	}
	
	private void run(String[] args)
	{
		Options options =createOption(); 
		
		if(parseOptions(options,args))
		{
			System.out.println("You provided \"" + inputpath + "\" as the value of the option i");
			System.out.println("You provided \"" + outputpath + "\" as the value of the option o");
			System.out.println("You provided \"" + startyear + "\" as the value of the option s");
			System.out.println("You provided \"" + endyear + "\" as the value of the option e");
			System.out.println("You provided \"" + opt + "\" as the value of the option opt");
			String[] arg= {inputpath,outputpath};
			System.out.println("0번째 값은: "+arg[0]);
			System.out.println("1번째 값은: "+arg[1]);
			if(help)
			{
				printHelp(options);
				return;
			}
			
			if(opt.equals("1"))
			{
				HGUCoursePatternAnalyzer analyzer = new HGUCoursePatternAnalyzer();
				analyzer.run(arg);
			}
			
		}
	}
	
	private boolean parseOptions(Options options, String[] args)
	{
		
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			
			inputpath=cmd.getOptionValue("i");
			outputpath=cmd.getOptionValue("o");
			startyear=cmd.getOptionValue("s");
			endyear=cmd.getOptionValue("e");
			help = cmd.hasOption("h");
			opt=cmd.getOptionValue("a");

		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}
	
	private Options createOption()
	{
		Options options =new Options();
		
		options.addOption(Option.builder("a").longOpt("analysis")
			   .desc("1: Count courses per semester, 2: Count per course name and year")
			   .hasArg()
			   .argName("Analysis option")
			   .required()
			   .build());
		
		options.addOption(Option.builder("i").longOpt("input")
			   .desc("Set an input file path")
			   .hasArg()
			   .argName("Input path")
	           .required()
	           .build());
		
		options.addOption(Option.builder("o").longOpt("output")
		        .desc("Set an output file path")
		        .hasArg()
		        .argName("Output path")
		        .required()
		        .build());
		
		options.addOption(Option.builder("s").longOpt("startyear")
		          .desc("Set the start year for analysis")
		          .hasArg()
		          .argName("Start year for analysis")
		          .required()
		          .build());
		
		options.addOption(Option.builder("e").longOpt("endyear")
		          .desc("Set the end year for analysis")
		          .hasArg()
		          .argName("End year for analysis")
		          .required()
		          .build());
		
		options.addOption(Option.builder("h").longOpt("help")
		          .desc("Show a Help page")
		          .argName("Help")
		          .build());
		

		return options;
	}
	
	private void printHelp(Options options)
	{
		HelpFormatter formatter = new HelpFormatter();
		String header = "HGU Course Analyzer";
		String footer ="";
		formatter.printHelp("HGU Course Analyzer", header, options, footer, true);
	}
}