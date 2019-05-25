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
		
		return semestersByYearAndSemester;
	}
	
	public int getNumCourseNthSemester(int semester)
	{
		int NumCourse=semestersByYearAndSemester.get(Integer.toString(semester));
		return NumCourse;
	}
	
}
