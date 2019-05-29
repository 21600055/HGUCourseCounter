package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	
	private String studentId; 
	private ArrayList<Course> coursesTaken=new ArrayList<Course>();
	private HashMap<String,Integer> semestersByYearAndSemester=new HashMap<String,Integer>();
	
	public Student(String studentId)
	{
		this.studentId=studentId;
	}	
	public void setStudentId(String studentId)
	{
		this.studentId=studentId;
	}
	public String getStudentId()
	{
		return studentId;
	}
	
	public void addCourse(Course newRecord)
	{
		coursesTaken.add(newRecord);
	}
	
	public ArrayList<Course> getCourse() {
		
		return coursesTaken;
	}
	
	public HashMap<String,Integer> getSemesterByYearAndSemester()
	{
		int minyear=10000;
		int minsemester=1;
		String yearandsemester;
		for(int i=0; i<coursesTaken.size();i++)
		{
			if(minyear>=coursesTaken.get(i).getyearTaken())
			{
				minyear=coursesTaken.get(i).getyearTaken();
			}
		}//입학년도 찾기
		
		for(int i=minyear;i<=Integer.parseInt(coursesTaken.get(0).getyearMonthGraduated().substring(0,3));i++)
		{
			yearandsemester=Integer.toString(minyear)+"-"+Integer.toString(minsemester);
			semestersByYearAndSemester.put(yearandsemester,minsemester);
			minsemester++;
			yearandsemester=Integer.toString(minyear)+"-"+Integer.toString(minsemester);
			semestersByYearAndSemester.put(yearandsemester,minsemester);
		}//입학년도와 학기 해시맵에 넣기
		
		return semestersByYearAndSemester;
	}
	
	public int getNumCourseNthSemester(int semester)
	{
		int numCourse=0;
		String year;
		String month;
		for(String key:semestersByYearAndSemester.keySet())
		{
			Integer value=semestersByYearAndSemester.get(key);
			if(value.intValue()==semester)
			{
				year=key.substring(0,3);
				month=key.substring(4,5);
			}
		}//밸류값 읽어와서 년도랑 학기랑 같으면 키값 끊어서 year에 넣기
		
		for(int i=0;i<coursesTaken.size();i++)
		{
			if(year.equals(coursesTaken.get(i).getyearMonthGraduated().substring(0,3)))
			{
				if(month.contentEquals(coursesTaken.get(i).getyearMonthGraduated().substring(4,5)))
				{
					numCourse++;
				}
			}
		}
		
		return numCourse;
	}
	
}
