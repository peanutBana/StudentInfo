package school;

//어느 학생의 어느 과목 점수가 몇점이다.
public class Score {
	int studentId;   //학번
	Subject subject; //과목
	int point;      //점수
	
	public Score( int studentId, Subject subject, int point){
		this.studentId = studentId;
		this.subject = subject;
		this.point = point;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	//toString() 메소드 재정의
	//해당 객체가 가지고 있는 학번, 과목, 점수 정보를 리턴해준다.
	@Override
	public String toString() {
		return "학번:" + studentId + "," + subject.getSubjectName() + ":" + point;
	}	
}
