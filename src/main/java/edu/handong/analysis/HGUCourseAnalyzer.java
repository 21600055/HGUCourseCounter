package edu.handong.analysis;

import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
//import java.io.IOException;

import edu.handong.analysis.datamodel.Course;
//import edu.handong.analysis.datamodel.Student;
import edu.handong.analysise.utils.NotEnoughArgumentException;
import edu.handong.analysise.utils.Utils;

public class HGUCourseAnalyzer {
	
    public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());	
			System.exit(0);
		}
		
		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		String coursecode=args[2];
		String startyear=args[3];
		String endyear=args[4];
		String all =new String();
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		ArrayList<Course> course= new ArrayList<Course>();
		ArrayList<Course> allcourse=new ArrayList<Course>();
		ArrayList<String> linesToBeSaved=new ArrayList<String>();
		
		linesToBeSaved.add("Year,Semester,CouseCode, CourseName,TotalStudents,StudentsTaken,Rate");
		for(String line:lines)
		{
			if(coursecode.equals(line.split(",")[4].trim()))
			{
				course.add(new Course(line));
			}
			allcourse.add(new Course(line));
		}
		
		double a,b,rate;
		for(int i=Integer.parseInt(startyear);i<=Integer.parseInt(endyear);i++)
		{
			for(int j=1;j<=4;j++)
			{
				a=0;
				b=0;
				for(int k=0;k<course.size();k++)
				{
					if(course.get(k).getyearTaken()==i&&course.get(k).getsemesterCourseTaken()==j)
					{
						a++;
					}
				}// 그해 그 학기에 그 과목을 들은 학생수
				
				for(int k=0;k<allcourse.size();k++)
				{
					if(allcourse.get(k).getyearTaken()==i&&allcourse.get(k).getsemesterCourseTaken()==j)
					{
						b++;
					}
				}// 그해 학기 에 들은 학생들 수
				
				if(b==0||((a==0)&&b==0))
				{
					continue;
				}//아예 들은 학생이 없을경우
				else
				{
					rate=a/b;
					all=i+","+j+","+coursecode+","+course.get(0).getcourseName()+","+b+","+a+","+rate;
					linesToBeSaved.add(all);
				}
			}
		}

		Utils.writeAFile(linesToBeSaved, resultPath);
	}    
}
