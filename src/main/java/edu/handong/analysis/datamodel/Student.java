package edu.handong.analysis.datamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


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
		int year;
		int semester;
		String yearandsemester;
		HashMap<String,Integer> temp=new HashMap<String,Integer>();
		for(int i=0; i<coursesTaken.size();i++)
		{
			if(minyear>=coursesTaken.get(i).getyearTaken())
			{
				minyear=coursesTaken.get(i).getyearTaken();
			}
		}//입학년도 찾기
		
		for(int i=0;i<coursesTaken.size();i++)
		{
			year=coursesTaken.get(i).getyearTaken();
			semester=coursesTaken.get(i).getsemesterCourseTaken();
			yearandsemester=Integer.toString(year)+"-"+Integer.toString(semester);
			System.out.println(yearandsemester);
			if(!(temp.containsKey(yearandsemester)))
			{
				if((semester==1)||(semester==3))
				{
					temp.put(yearandsemester,1);
				}
				 
				if((semester==2)||(semester==4))
				{
					temp.put(yearandsemester,2);
				}
			}
		}
		Map<String,Integer> sorttemp=new TreeMap<String,Integer>(temp);
		//임시 해시맵에 넣은다음, 정렬해서 뽑고  
		for(String key:sorttemp.keySet())
		{
			Integer value=sorttemp.get(key);
		}
		
		for(int i=minyear;i<=Integer.parseInt(coursesTaken.get(0).getyearMonthGraduated().substring(0,4));i++)
		{
			yearandsemester=Integer.toString(i)+"-"+Integer.toString(1);
			semestersByYearAndSemester.put(yearandsemester,minsemester);
			minsemester++;
			yearandsemester=Integer.toString(i)+"-"+Integer.toString(2);
			semestersByYearAndSemester.put(yearandsemester,minsemester);
			minsemester++;
		}//입학년도와 학기 해시맵에 넣기
		
		return semestersByYearAndSemester;
	}
	
	public int getNumCourseNthSemester(int semester)
	{
		int numCourse=0;
		String year=new String();
		String month=new String();
		
		for(String key:semestersByYearAndSemester.keySet())
		{
			Integer value=semestersByYearAndSemester.get(key);
			if(value.intValue()==semester)
			{
				year=key.substring(0,4);
				//System.out.println(year);
				month=key.substring(5);
				//System.out.println(year+"-"+month+":"+value.intValue());
				
			}
		}//밸류값 읽어와서 년도랑 학기랑 같으면 키값 끊어서 year에 넣기
		
		for(int i=0;i<coursesTaken.size();i++)
		{
			if(Integer.parseInt(year)==(coursesTaken.get(i).getyearTaken()))
			{
				if((Integer.parseInt(month)==coursesTaken.get(i).getsemesterCourseTaken())||
					(Integer.parseInt(month)==(coursesTaken.get(i).getsemesterCourseTaken()-2)))
				{
					numCourse++;
				}
			}
		}
		//System.out.println("이번학기 과목"+numCourse);
		return numCourse;
	}
	
}
