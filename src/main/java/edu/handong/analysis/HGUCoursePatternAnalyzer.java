package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.io.IOException;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysise.utils.NotEnoughArgumentException;
import edu.handong.analysise.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String,Student> students;
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
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
		ArrayList<String> lines = Utils.getLines(dataPath, true);
		//Utils.writeAFile(lines, resultPath);
		students = loadStudentCourseRecords(lines);
		/*for(int i=0;i<90;i++) 
		{
			System.out.println(students.get("0002").getCourse().get(i).getcourseName());
		}*/
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		// TODO: Implement this method
		HashMap<String,Student> hashmap=new HashMap<String,Student>();
		//학번별 리스트
		ArrayList<Course> course=new ArrayList<Course>();
		ArrayList<String> nodupli=new ArrayList<String>();
		
		for(String line:lines)
		{
			course.add(new Course(line));
		}
		
		for(int i=0;i<course.size();i++)
		{
			if(!nodupli.contains(course.get(i).getstudentId()))
			{
				nodupli.add(course.get(i).getstudentId());
			}
		}
		
		for(int i=0;i<nodupli.size();i++)
		{
			ArrayList<Course> list=new ArrayList<Course>();
			Student student= new Student(nodupli.get(i));
			
			for(String line:lines)
			{
				if(student.getStudentId().equals(line.split(",")[0].trim()))
				{
					list.add(new Course(line));
				}
			}
			
			for(int j=0;j<list.size();j++)
			{	//System.out.println("list.get: " + list.get(j).getstudentId());
				student.addCourse(list.get(j));
			}
			
			hashmap.put(student.getStudentId(),student);
			
		}
		return hashmap; // do not forget to return a proper variable.*/
	}
	/**
	 * This method generate the number of courses taken by a student in each semester. The result file look like this:
	 * StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9
     * 0001,14,2,8
	 * ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		
		// TODO: Implement this method
		String all;
		ArrayList<String> rpath=new ArrayList<String>();
		String first="StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester";
		rpath.add(first);

		for(String key:sortedStudents.keySet())
		{
			Student student= sortedStudents.get(key);
			System.out.println(student.getStudentId());
			HashMap<String, Integer> hash=new HashMap<String,Integer>();
			hash=student.getSemesterByYearAndSemester();
			Map<String, Integer> sorthash = new TreeMap<String,Integer>(hash);
			int lastsemester=0;
			
			//System.out.println(student.getCourse().get(0).getcourseName());
			for(String key1 : sorthash.keySet())
			{
				Integer value=hash.get(key1);
				//System.out.println(value.intValue());
				if(lastsemester<value.intValue())
				{
					lastsemester=value.intValue();
				}
			}//막학기 찾기
			// System.out.println(lastsemester);
			
			for(int j=1;j<=lastsemester;j++)
			{
				all=student.getCourse().get(0).getstudentId()+", "+
			    lastsemester+", "+
				j+", "+ 					  
	            student.getNumCourseNthSemester(j);
			    rpath.add(all); 
			}
			System.out.println("다음 학생!!");
		}
		return rpath; // do not forget to return a proper variable.
	}
}