public class Notification {
    Grade grade;
    public Notification(Grade grade) {
        this.grade = grade;
    }
    public String toString() {
        if (grade.getExamScore() != null) {
            return "Nota noua!\n" + "Curs - " + grade.getCourse() + " | Student - " + grade.getStudent()
                    + " | Examen - " + grade.getExamScore();
        } else {
            return "Nota noua!\n" + "Curs - " + grade.getCourse() + " | Student - " + grade.getStudent()
                    + " | Partial - " + grade.getPartialScore();
        }
    }
}
