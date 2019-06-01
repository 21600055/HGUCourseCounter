package edu.handong.analysise.utils;

import java.util.ArrayList;
import 	java.io.*;
import java.util.Scanner;

public class Utils {
	
	public static ArrayList<String> getLines(String file,boolean removeHeader)
	{
		ArrayList<String> aline =new ArrayList<String>();
		try {
			
			Scanner inputStream =new Scanner(new File(file));
			String line;
            while(inputStream.hasNextLine())
            {
            		line=inputStream.nextLine();
            		if(removeHeader==true)
            		{
            			removeHeader=false;
            			continue;
            		}
            		else
            		{
            			aline.add(line);
            		}	
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
			System.out.println("The file path does not exist. Please check your CLI argument!");
			System.exit(0);
		}
		int i;
		/*for(int j=0;j<lines.size();j++)
		{
			System.out.println(lines.get(j));
		}*/
		//System.out.println(lines.size());
		int ct=1;
		for(i=0;i<lines.size();i++)
		{
			System.out.println(lines.get(i));
			outputStream.println(lines.get(i));
		}
	}
}
