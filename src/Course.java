import java.util.*;

public abstract class Course {
    private String name;
    private Teacher courseHolder;
    private Set<Assistant> assistants;
    private ArrayList<Grade> grades;
    private HashMap<String, Group> groups;
    private int creditPoints;
    private String strategy;
    private Snapshot snapshot;
    protected Course(CourseBuilder builder) {
        this.name = builder.name;
        this.courseHolder = builder.courseHolder;
        this.assistants = builder.assistants;
        this.grades = builder.grades;
        this.strategy = builder.strategy;
        this.groups = builder.groups;
        this.creditPoints = builder.creditPoints;
        this.snapshot = new Snapshot();
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCourseHolder(Teacher teacher) {
        this.courseHolder = teacher;
    }
    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }
    public String getName() {
        return name;
    }
    public String getStrategy() {
        return strategy;
    }
    public Teacher getCourseHolder() {
        return courseHolder;
    }
    public int getCreditPoints() {
        return creditPoints;
    }
    public Set<Assistant> getAssistants() {
        return assistants;
    }
    public ArrayList<Grade> getGrades() {
        return grades;
    }
    public HashMap<String, Group> getGroups() {
        return groups;
    }
    public abstract static class CourseBuilder {
        protected String name;
        protected Teacher courseHolder;
        protected Set<Assistant> assistants;
        protected ArrayList<Grade> grades;
        protected HashMap<String, Group> groups;
        protected int creditPoints;
        protected String strategy;
        public CourseBuilder(String name) {
            this.name = name;
            assistants = new HashSet<Assistant>();
            grades = new ArrayList<Grade>();
            groups = new HashMap<String, Group>();
        }
        public abstract Course build();
    }
    public void addAssistant(String ID, Assistant assistant) {
        assistants.add(assistant);
        if (ID != null)
            groups.get(ID).setAssistant(assistant);
    }

    public void addStudent(String ID, Student student) {
        if(groups.get(ID) != null) {
            groups.get(ID).add(student);
        }
    }

    public void addGroup(Group group) {
        groups.put(group.getID(), group);
    }

    public void addGroup(String ID, Assistant assistant) {
        groups.put(ID, new Group(ID, assistant));
    }

    public void addGroup(String ID, Assistant assist, Comparator<Student> comp) {
        groups.put(ID, new Group(ID, assist, comp));
    }

    public Grade getGrade(Student student) {
        for (Grade g: grades) {
            if(g.getStudent().equals(student))
                return g;
        }
        return null;
    }

    public void addGrade(Grade grade) {
        int ok = 0, i;
        for (i = 0; i < grades.size(); i++) {
            if (grades.get(i).getStudent() == grade.getStudent()) {
                if (grade.getExamScore() != null)
                    grades.get(i).setExamScore(grade.getExamScore());
                if (grade.getPartialScore() != null)
                    grades.get(i).setPartialScore(grade.getPartialScore());
                ok = 1;
            }
        }
        if (ok == 0)
            grades.add(grade);
    }

    public ArrayList<Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        for (Map.Entry<String, Group> entry: groups.entrySet()) {
            students.addAll(entry.getValue());
        }
        return students;
    }

    public HashMap<Student, Grade> getAllStudentGrades() {
        HashMap<Student, Grade> allGrades = new HashMap<Student, Grade>();
        for (Grade g: grades) {
            allGrades.put(g.getStudent(),g);
        }
        return allGrades;
    }

    public abstract ArrayList<Student> getGraduatedStudents();

    public Student getBestStudent() {
        if (getStrategy().equals("BestExamScore")) {
            BestExamScore bestExamScore = new BestExamScore();
            return bestExamScore.getBestScore(grades);
        } else if (getStrategy().equals("BestPartialScore")) {
            BestPartialScore bestPartialScore = new BestPartialScore();
            return bestPartialScore.getBestScore(grades);
        } else if (getStrategy().equals("BestTotalScore")) {
            BestTotalScore bestTotalScore = new BestTotalScore();
            return bestTotalScore.getBestScore(grades);
        }
        return null;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }
    public void makeBackup() {
        for(Grade g: Course.this.grades) {
            snapshot.grades.add(g.clone());
        }
    }
    public void undo() {
        this.grades = snapshot.grades;
    }

    public String toString() {
        return getName();
    }

    public class Snapshot {
        private final ArrayList<Grade> grades;
        public Snapshot() {
            this.grades = new ArrayList<Grade>();
        }
    }
}
