package edu.handong.analysise.utils;

import java.util.ArrayList;
import 	java.io.*;

public class Utils {
	
	public static ArrayList<String> getLines(String file,boolean removeHeader)
	{
		ArrayList<String> aline =new ArrayList<String>();
		int i;
		try {
			
			File csv= new File(file);
			BufferedReader br = new BufferedReader(new FileReader(csv));
            String line = "";
            while((line=br.readLine())!=null)
            {
            	if(removeHeader)
            	{
            		removeHeader=false;
            		continue;
            	}
            	
            	aline.add(line);
            }
            i=0;
            
            while(i<aline.size())
            {
            	aline.get(i);
            	i++;
            }
            br.close();
            
            
		} catch(FileNotFoundException e) {
			System.out.println("The file path does not exist. Please check your CLI argument!");
		} catch(IOException e) {
			
		}
		
		return aline;
	}
	
	public static void writeAFile(ArrayList<String>lines,String targetFileName)
	{
		
	}
	
}
