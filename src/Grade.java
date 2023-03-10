public class Grade implements Comparable, Cloneable {
    private Double partialScore, examScore;
    private Student student;
    private String course;
    public Grade(Student student, String course) {
        this.student = student;
        this.course = course;
    }
    public Double getPartialScore() {
        return partialScore;
    }
    public Double getExamScore() {
        return examScore;
    }
    public Student getStudent() {
        return student;
    }
    public String getCourse() {
        return course;
    }

    public void setPartialScore(Double partialScore) {
        this.partialScore = partialScore;
    }
    public void setExamScore(Double examScore) {
        this.examScore = examScore;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public Double getTotal() {
        return partialScore + examScore;
    }

    @Override
    public int compareTo(Object o) {
        Grade obj = (Grade) o;
        if (getTotal() - obj.getTotal() > 0)
            return 1;
        else if (getTotal() - obj.getTotal() == 0)
            return 0;
        else return -1;
    }

    @Override
    public Grade clone() {
        try {
            Grade clone = (Grade) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    public String toString() {
        return student.toString() + " cu nota " + getPartialScore() + " la partial si " + getExamScore() +
        " la examen.";
    }
}
