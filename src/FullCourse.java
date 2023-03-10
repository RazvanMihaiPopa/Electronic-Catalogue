import java.util.ArrayList;

public class FullCourse extends Course {
    private FullCourse(FullCourseBuilder builder) {
        super(builder);
    }
    public static class FullCourseBuilder extends CourseBuilder {
        public FullCourseBuilder(String name) {
            super(name);
        }
        public FullCourseBuilder courseHolder(Teacher courseHolder) {
            super.courseHolder = courseHolder;
            return this;
        }
        public FullCourseBuilder creditPoints(int creditPoints) {
            super.creditPoints = creditPoints;
            return this;
        }
        public FullCourseBuilder strategy(String strategy) {
            super.strategy = strategy;
            return this;
        }
        @Override
        public FullCourse build() {
            return new FullCourse(this);
        }
    }

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> graduatedStudents = new ArrayList<Student>();
        for (Grade g: getGrades()) {
            if (g.getPartialScore() >= 3 && g.getExamScore() >=2)
                graduatedStudents.add(g.getStudent());
        }
        return graduatedStudents;
    }
}
