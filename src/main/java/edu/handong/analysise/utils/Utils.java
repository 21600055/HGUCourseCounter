package edu.handong.analysise.utils;

import java.util.ArrayList;
import 	java.io.*;
import java.util.Scanner;

public class Utils {
	
	public static ArrayList<String> getLines(String file,boolean removeHeader)
	{
		ArrayList<String> aline =new ArrayList<String>();
		int i;
		try {
			
			Scanner inputStream =new Scanner(new File(file));
			String line;
            while(inputStream.hasNextLine())
            {
            	if(removeHeader)
            	{
            		removeHeader=false;
            		continue;
            	}
            	line=inputStream.nextLine();
            	
            	aline.add(line);
            }
            
            inputStream.close();
		} catch(FileNotFoundException e) {
			System.out.println("The file path does not exist. Please check your CLI argument!");
		}
		
		return aline;
	}
	
	public static void writeAFile(ArrayList<String>lines,String targetFileName)
	{
		PrintWriter outputStream = null;
		
		try {
			outputStream = new PrintWriter(targetFileName);
		} catch(FileNotFoundException e) {
			System.out.println("Error opening the file " + targetFileName);
			System.exit(0);
		}
		int i;
		for(i=0;i<lines.size();i++)
		{
			outputStream.println(lines.get(i));
		}
	}
}
