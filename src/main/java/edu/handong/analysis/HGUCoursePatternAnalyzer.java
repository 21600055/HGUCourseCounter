package edu.handong.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
		System.out.println(students.get("0001"));
	}
	
		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
		//Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
		
		// Generate result lines to be saved.
		//ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
		
		// Write a file (named like the value of resultPath) with linesTobeSaved.
		//Utils.writeAFile(linesToBeSaved, resultPath);
//	}
	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a student id and the corresponding object is an instance of Student.
	 * The Student instance have all the Course instances taken by the student.
	 * @param lines
	 * @return
	 */
	private HashMap<String,Student> loadStudentCourseRecords(ArrayList<String> lines) {
		
		// TODO: Implement this method
		HashMap<String,Student> hashmap=new HashMap<String,Student>();
		ArrayList<Course> course=new ArrayList<Course>();//스트링 어레이리스트 코스리스트로 변환시키기
		ArrayList<String> nodupli=new ArrayList<String>();//중복없는 학번 리스트
		for(String line:lines)
		{
			course.add(new Course(line));
		}
		
		for(int i=0;i<course.size();i++)
		{
			if(!nodupli.contains(course.get(i).getstudentId()))
			{
				nodupli.add(course.get(i).getstudentId());
			}//학번과 학생의 코스를 넣기//학번과 학생의 코스를 넣기
		}
		//System.out.println(course.get(78).getstudentId());
		for(int i=0,j=0;i<nodupli.size();i++)
		{
			Student student= new Student(nodupli.get(i));
			while(j<course.size())
			{
				if(course.get(j).getstudentId()!=nodupli.get(i))
				{
					break;
				}                 //new Course(lines.get(j))
				student.addCourse(course.get(j));
				j++;
			}
			hashmap.put(nodupli.get(i),student);//학번과 학생의 코스를 넣기
		}
		//System.out.println(course.get(0).getstudentId());
		return hashmap; // do not forget to return a proper variable.
	}
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
/*	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		
		// TODO: Implement this method
		ArrayList<String> rpath=new ArrayList<String>();
		
		//rpath.add(sortedStudents.get());
		return rpath; // do not forget to return a proper variable.
	}
}*/
