import java.util.*;

public class ScoreVisitor implements Visitor {
    private HashMap<Teacher, ArrayList<Tuple>> examScores;
    private HashMap<Assistant, ArrayList<Tuple>> partialScores;
    public ScoreVisitor() {
        examScores = new HashMap<Teacher, ArrayList<Tuple>>();
        partialScores = new HashMap<Assistant, ArrayList<Tuple>>();
    }
    public void addExamScore(Teacher teacher, Grade grade) {
        Tuple tuple = new Tuple(grade.getStudent(), grade.getCourse(), grade.getExamScore());
        if (examScores.containsKey(teacher)) {
            examScores.get(teacher).add(tuple);
        } else {
            ArrayList<Tuple> tuples = new ArrayList<Tuple>();
            tuples.add(tuple);
            examScores.put(teacher, tuples);
        }
    }
    public void addPartialScore(Assistant assistant, Grade grade) {
        Tuple tuple = new Tuple(grade.getStudent(), grade.getCourse(), grade.getPartialScore());
        if (partialScores.containsKey(assistant)) {
            partialScores.get(assistant).add(tuple);
        } else {
            ArrayList<Tuple> tuples = new ArrayList<Tuple>();
            tuples.add(tuple);
            partialScores.put(assistant, tuples);
        }
    }
    public ArrayList<Tuple> getPartialTuple(Assistant assistant) {
        return partialScores.get(assistant);
    }

    public ArrayList<Tuple> getExamTuple(Teacher teacher) {
        return examScores.get(teacher);
    }
    @Override
    public void visit(Assistant assistant) {
        int i, j;
        Tuple tuple;
        System.out.println("Notele de la partial au fost introduse.\n");
        for (Map.Entry<Assistant, ArrayList<Tuple>> entry: partialScores.entrySet()) {
            for (i = 0; i < Catalog.getInstance().courses.size(); i++) {
                for (j = 0; j < entry.getValue().size() && entry.getKey() == assistant; j++) {
                    tuple = entry.getValue().get(j);
                    if (Catalog.getInstance().courses.get(i).getAssistants().contains(assistant)
                            && Catalog.getInstance().courses.get(i).getName().equals(tuple.name)) {
                        Grade g = new Grade(tuple.student, tuple.name);
                        g.setPartialScore(tuple.grade);
                        Catalog.getInstance().courses.get(i).addGrade(g);
                        Catalog.getInstance().notifyObservers(g);
                    }
                }
            }
        }
    }

    @Override
    public void visit(Teacher teacher) {
        int i, j;
        Tuple tuple;
        System.out.println("Notele de la examen au fost introduse.\n");
        for (Map.Entry<Teacher, ArrayList<Tuple>> entry: examScores.entrySet()) {
            for (i = 0; i < Catalog.getInstance().courses.size(); i++) {
                for (j = 0; j < entry.getValue().size() && entry.getKey() == teacher; j++) {
                    tuple = entry.getValue().get(j);
                    if (Catalog.getInstance().courses.get(i).getCourseHolder().equals(teacher)
                            && Catalog.getInstance().courses.get(i).getName().equals(tuple.name)) {
                        Grade g = new Grade(tuple.student, tuple.name);
                        g.setExamScore(tuple.grade);
                        Catalog.getInstance().courses.get(i).addGrade(g);
                        Catalog.getInstance().notifyObservers(g);
                    }
                }
            }
        }

    }
    private class Tuple {
        Student student;
        String name;
        Double grade;
        public Tuple(Student student, String name, Double grade) {
            this.student = student;
            this.name = name;
            this.grade = grade;
        }
        public String toString() {
            return student.toString() + " | " + name + " | " + grade;
        }
    }
}
