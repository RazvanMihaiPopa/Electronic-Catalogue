import java.util.ArrayList;

public class PartialCourse extends Course {
    public PartialCourse(PartialCourseBuilder builder) {
        super(builder);
    }
    public static class PartialCourseBuilder extends CourseBuilder {
        public PartialCourseBuilder(String name) {
            super(name);
        }
        public PartialCourseBuilder courseHolder(Teacher courseHolder) {
            super.courseHolder = courseHolder;
            return this;
        }
        public PartialCourseBuilder creditPoints(int creditPoints) {
            super.creditPoints = creditPoints;
            return this;
        }
        public PartialCourseBuilder strategy(String strategy) {
            super.strategy = strategy;
            return this;
        }
        @Override
        public  PartialCourse build() {
            return new PartialCourse(this);
        }
    }

    @Override
    public ArrayList<Student> getGraduatedStudents() {
        ArrayList<Student> graduatedStudents = new ArrayList<Student>();
        for(Grade g: getGrades()) {
            if(g.getTotal() >= 5)
                graduatedStudents.add(g.getStudent());
        }
        return graduatedStudents;
    }
}
