package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import school.School;
import school.Score;
import school.Student;
import school.Subject;
import school.report.GenerateGradeReport;
import utils.Define;

public class StudentInfoApplicationTXT {
	
	School goodSchool = School.getInstance();
	Subject korean;
	Subject math;

	GenerateGradeReport gradeReport = new GenerateGradeReport();
	
	public static void main(String[] args) throws FileNotFoundException {

		StudentInfoApplicationTXT test = new StudentInfoApplicationTXT();
		test.creatSubject();
		test.createStudent();
		
		String report = test.gradeReport.getReport();
		System.out.println(report);		
		
	}
	
	public void creatSubject(){
		
		korean = new Subject("국어", Define.KOREAN);
		math = new Subject("수학", Define.MATH);
		
		goodSchool.addSubject(korean);
		goodSchool.addSubject(math);
	}
	
	public void createStudent() throws FileNotFoundException {
		
		Scanner scanner = new Scanner(new FileInputStream("studentinfo.txt"));
		
		String name;
		int id;
		int koreanScore;
		int mathScore;
		int majorCode;
		
		Student student = null;
		
		while(scanner.hasNextLine()) {
			
			name = scanner.next();
			id = scanner.nextInt();
			koreanScore = scanner.nextInt();
			mathScore = scanner.nextInt();
			majorCode = scanner.nextInt();
			
			if(majorCode == Define.KOREAN) {
				student = new Student(id, name, korean );
			}
			else if(majorCode == Define.MATH) {
				student = new Student(id, name, math );
			}
			else {
				System.out.println("존재하지 않는 과목입니다.");
				return;
			}
			
			goodSchool.addStudent(student);
			korean.register(student);
			math.register(student);
			addScoreForStudent(student, korean, koreanScore);
			addScoreForStudent(student, math, mathScore);
						
		}
		scanner.close();
	}
	
	//과목별 점수 추가 메소드
	public void addScoreForStudent(Student student, Subject subject, int point){
			
		Score score = new Score(student.getStudentId(), subject, point);
		student.addSubjectScore(score);
			
	}
}
