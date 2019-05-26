package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;

public class Student {
	
	private String studentId; 
	private ArrayList<Course> coursesTaken;
	private HashMap<String,Integer> semestersByYearAndSemester;
	
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
		HashMap<String,Integer> getsemester=new HashMap<String,Integer>();
		return getsemester;
	}
	
	public int getNumCourseNthSemester(int semester)
	{
		int numcourse=semester;
		return numcourse;
	}
	
}
