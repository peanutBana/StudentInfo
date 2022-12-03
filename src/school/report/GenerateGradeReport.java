package school.report;

import java.util.ArrayList;

import grade.BasicEvaluation;
import grade.GradeEvaluation;
import grade.MajorEvaluation;
import school.School;
import school.Score;
import school.Student;
import school.Subject;
import utils.Define;

public class GenerateGradeReport {

	School school = School.getInstance();
	public static final String TITLE = "수강생 학점 \t\t\n";
	public static final String HEADER = "이름 | 학번 |필수과목| 점수   \n ";
	public static final String LINE = "-------------------------------------\n";
	private StringBuffer buffer = new StringBuffer();  
	
	public String getReport(){
		ArrayList<Subject> subjectList = school.getSubjectList();  //과목 리스트(국어, 수학) 불러오기
		for( Subject subject : subjectList) {
			makeHeader(subject);
			makeBody(subject);
			makeFooter();
		}
		return buffer.toString();  // String 으로 변환
	}
	
	public void makeHeader(Subject subject){
		buffer.append(GenerateGradeReport.LINE);
		buffer.append("\t" + subject.getSubjectName());
		buffer.append(GenerateGradeReport.TITLE );
		buffer.append(GenerateGradeReport.HEADER );
		buffer.append(GenerateGradeReport.LINE);
	} 
	
	public void makeBody(Subject subject){
		
		ArrayList<Student> studentList = subject.getStudentList();  // 해당 과목을 수강신청한 학생의 리스트
		
		for(int i=0; i<studentList.size(); i++){
			Student student = studentList.get(i);
			buffer.append(student.getStudentName()); //학생 이름
			buffer.append(" | ");
			buffer.append(student.getStudentId()); //학생 아이디
			buffer.append(" | ");
			buffer.append(student.getMajorSubject().getSubjectName() + "\t"); //학생들의 필수과목
			buffer.append(" | ");
			
			getScoreGrade(student, subject.getSubjectId());  //학생별 수강과목 학점 계산
			buffer.append("\n");
			buffer.append(LINE);
		}
	}
	
	public void getScoreGrade(Student student, int subjectId){
		
		ArrayList<Score> scoreList = student.getScoreList(); //해당 학생이 수강한 과목의 점수리스트
		int majorId = student.getMajorSubject().getSubjectId();
		
		GradeEvaluation[] gradeEvaluation = {new BasicEvaluation(), new MajorEvaluation()};  //학점 평가 클래스
		
		for(int i=0; i<scoreList.size(); i++){ 
			
			Score score = scoreList.get(i);
			if(score.getSubject().getSubjectId() == subjectId) {  // 학점 산출할 과목찾기
				String grade;
				if(score.getSubject().getSubjectId() == majorId)  // 필수 과목인 경우
					grade = gradeEvaluation[Define.SAB_TYPE].getGrade(score.getPoint());  
				else //일반 과목일 경우
					grade = gradeEvaluation[Define.AB_TYPE].getGrade(score.getPoint());
				buffer.append(score.getPoint());
				buffer.append(":");
				buffer.append(grade);
				buffer.append(" | ");
			}
		}
	}
	
	public void makeFooter(){
		buffer.append("\n");
	}
}