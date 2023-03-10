import com.sun.security.auth.UserPrincipal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Catalog catalog = Catalog.getInstance();
        ArrayList<Assistant> allAssistants = new ArrayList<Assistant>();
        ArrayList<Student> allStudents = new ArrayList<Student>();
        ArrayList<Parent> allParents = new ArrayList<Parent>();
        ArrayList<Teacher> allTeachers = new ArrayList<Teacher>();
        ScoreVisitor visitor = new ScoreVisitor();

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src\\package.json"));
            JSONObject jsonObject = (JSONObject) obj;
            // Set the courses into the catalogue
            JSONArray courses = (JSONArray) jsonObject.get("courses");
            for (Object o: courses) {
                JSONObject course = (JSONObject) o;
                // get type
                String type = (String) course.get("type");
                // get strategy
                String strategy = (String) course.get("strategy");
                // get course name
                String name = (String) course.get("name");
                JSONObject teacher = (JSONObject) course.get("teacher");
                // create teacher
                int okTeacher = 0, indexTeacher = 0;
                for(int i = 0; i < allTeachers.size(); i++) {
                    if(allTeachers.get(i).getFirstName().equals((String) teacher.get("firstName")) &&
                            allTeachers.get(i).getLastName().equals((String) teacher.get("lastName"))) {
                        okTeacher = 1;
                        indexTeacher = i;
                    }
                }
                User t;
                if (okTeacher == 0) {
                    t = UserFactory.getUser("Teacher", (String) teacher.get("firstName"),
                            (String) teacher.get("lastName"));
                    t.setPassword((String) teacher.get("password"));
                    allTeachers.add((Teacher) t);
                } else {
                    t = allTeachers.get(indexTeacher);
                }
                // create course depending on type
                Course c;
                if (type.equals("PartialCourse")) {
                    c = new PartialCourse.PartialCourseBuilder(name).courseHolder((Teacher) t).strategy(strategy).build();
                } else {
                    c = new FullCourse.FullCourseBuilder(name).courseHolder((Teacher) t).strategy(strategy).build();
                }
                // add assistants
                for (Object assistants: (JSONArray) course.get("assistants")) {
                    JSONObject assist = (JSONObject) assistants;
                    int ok = 0;
                    for(Assistant a: allAssistants) {
                        if(a.getFirstName().equals((String) assist.get("firstName")) &&
                                a.getLastName().equals((String) assist.get("lastName"))) {
                            ok = 1;
                            c.addAssistant(null, a);
                        }
                    }
                    if (ok == 0) {
                        User a = UserFactory.getUser("Assistant", (String) assist.get("firstName"),
                                (String) assist.get("lastName"));
                        a.setPassword((String) assist.get("password"));
                        c.addAssistant(null, (Assistant) a);
                        allAssistants.add((Assistant) a);
                    }
                }
                // create groups and add them
                for (Object groups: (JSONArray) course.get("groups")) {
                    JSONObject group = (JSONObject) groups;
                    JSONObject assist = (JSONObject) group.get("assistant");
                    // get group assistant
                    int indexA = 0;
                    for(int i = 0; i < allAssistants.size(); i++) {
                        if(allAssistants.get(i).getFirstName().equals((String)assist.get("firstName")) &&
                        allAssistants.get(i).getLastName().equals((String)assist.get("lastName"))) {
                            indexA = i;
                        }
                    }
                    // create new group with given ID and assistant
                    Group g = new Group((String) group.get("ID"), allAssistants.get(indexA), new NameSorter());
                    c.addGroup(g);
                    // get all students
                    for (Object students: (JSONArray) group.get("students")) {
                        JSONObject student = (JSONObject) students;
                        // check if student exists
                        int ok = 0, index = 0;
                        for (int i = 0; i < allStudents.size(); i++) {
                            if (allStudents.get(i).getFirstName().equals((String)student.get("firstName")) && allStudents.get(i).getLastName().equals((String)
                            student.get("lastName"))) {
                                index = i;
                                ok = 1;
                            }
                        }
                        if (ok == 0) {
                            // create student
                            User s = UserFactory.getUser("Student", (String) student.get("firstName"),
                                    (String) student.get("lastName"));
                            s.setPassword(s.toString()+"123");
                            allStudents.add((Student) s);
                            // set mother
                            if (student.get("mother") != null) {
                                JSONObject mother = (JSONObject) student.get("mother");
                                User m = UserFactory.getUser("Parent", (String) mother.get("firstName"),
                                        (String) mother.get("lastName"));
                                m.setPassword(m.toString()+"123");
                                ((Student) s).setMother((Parent) m);
                                catalog.addObserver((Parent) m);
                            }
                            // set father
                            if (student.get("father") != null) {
                                JSONObject father = (JSONObject) student.get("father");
                                User f = UserFactory.getUser("Parent", (String) father.get("firstName"),
                                        (String) father.get("lastName"));
                                f.setPassword(f.toString()+"123");
                                ((Student) s).setFather((Parent) f);
                                catalog.addObserver((Parent) f);
                            }
                            // add student to the group
                            c.addStudent(g.getID(), (Student) s);
                            c.addGrade(new Grade((Student) s, name));
                        } else {
                            c.addStudent(g.getID(), allStudents.get(index));
                            c.addGrade(new Grade(allStudents.get(index), name));
                        }

                    }
                }
                catalog.addCourse(c);
            }

            // set examScores in ScoreVisitor
            JSONArray exam = (JSONArray) jsonObject.get("examScores");
            for (Object o: exam) {
                JSONObject examScore = (JSONObject) o;
                // get course name
                String name = (String) examScore.get("course");
                // get grade
                Double grade = ((Number) examScore.get("grade")).doubleValue();
                // get student
                JSONObject student = (JSONObject) examScore.get("student");
                int indexStud = 0, indexTeacher = 0;
                for (int i = 0; i < allStudents.size(); i++) {
                    if (allStudents.get(i).getFirstName().equals((String)student.get("firstName")) &&
                            allStudents.get(i).getLastName().equals((String) student.get("lastName"))) {
                        indexStud = i;
                    }
                }
                // get teacher
                JSONObject teacher = (JSONObject) examScore.get("teacher");
                for(int i = 0; i < allTeachers.size(); i++) {
                    if(allTeachers.get(i).getFirstName().equals(teacher.get("firstName")) &&
                            allTeachers.get(i).getLastName().equals(teacher.get("lastName"))) {
                        indexTeacher = i;
                    }
                }
                Grade g = new Grade(allStudents.get(indexStud), name);
                g.setExamScore(grade);
                visitor.addExamScore(allTeachers.get(indexTeacher), g);
            }

            // set partialScores in Score Visitor
            JSONArray partial = (JSONArray) jsonObject.get("partialScores");
            for (Object o: partial) {
                JSONObject partialScore = (JSONObject) o;
                // get course name
                String name = (String) partialScore.get("course");
                // get grade
                Double grade = ((Number) partialScore.get("grade")).doubleValue();
                // get student
                JSONObject student = (JSONObject) partialScore.get("student");
                int indexStud = 0, indexAssist = 0;
                for (int i = 0; i < allStudents.size(); i++) {
                    if (allStudents.get(i).getFirstName().equals((String)student.get("firstName")) &&
                            allStudents.get(i).getLastName().equals((String) student.get("lastName"))) {
                        indexStud = i;
                    }
                }
                // get assistant
                JSONObject assistant = (JSONObject) partialScore.get("assistant");
                for(int i = 0; i < allAssistants.size(); i++) {
                    if(allAssistants.get(i).getFirstName().equals(assistant.get("firstName")) &&
                            allAssistants.get(i).getLastName().equals(assistant.get("lastName"))) {
                        indexAssist = i;
                    }
                }
                Grade g = new Grade(allStudents.get(indexStud), name);
                g.setPartialScore(grade);
                visitor.addPartialScore(allAssistants.get(indexAssist), g);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Testing getGrade
        System.out.println(catalog.courses.get(0).getGrade(allStudents.get(0)));

        // Testing getAllStudents
        System.out.println("Testing getAllStudents\n");
        System.out.println(catalog.courses.get(0).getAllStudents());

        // Testing ScoreVisitor for Teacher and Assistant
        System.out.println("Testing accept/visit for assistant\n");
        allAssistants.get(0).accept(visitor);
        allAssistants.get(1).accept(visitor);
        // Also testing Snapshot
        catalog.courses.get(0).makeBackup();
        System.out.println("Testing accept/visit for teacher\n");
        allTeachers.get(0).accept(visitor);
        System.out.println("\nTESTING\nGrade before " + catalog.courses.get(0).getGrade(allStudents.get(0)));
        // Testing undo
        catalog.courses.get(0).undo();
        System.out.println("Grade after undo " + catalog.courses.get(0).getGrade(allStudents.get(0)) + "\n");
        allTeachers.get(0).accept(visitor);

        // Testing getAllStudentGrades
        System.out.println("\nTESTING getallStudentGrades");
        HashMap<Student, Grade> allGrades = catalog.courses.get(0).getAllStudentGrades();
        for (Map.Entry<Student, Grade> entry: allGrades.entrySet()) {
            System.out.println(entry.getKey().toString() + " | Examen - " + entry.getValue().getExamScore()
                    + " | Partial - " + entry.getValue().getPartialScore());
        }
        // Testing getBestStudent
        System.out.println("\nTESTING getBestStudent\nStrategy - " + catalog.courses.get(0).getStrategy());
        System.out.println(catalog.courses.get(0).getBestStudent().toString());

        // Testing getGraduatedStudents
        System.out.println("\nTESTING getGraduatedStudents\n" + catalog.courses.get(0).getGraduatedStudents());

        // Course 2
        // Testing ScoreVisitor for Teacher and BestExam Score
        System.out.println("Testing accept/visit for teacher\n");
        allTeachers.get(1).accept(visitor);
        // Testing getBestStudent
        System.out.println("\nTESTING getBestStudent\nStrategy - " + catalog.courses.get(1).getStrategy());
        System.out.println(catalog.courses.get(1).getBestStudent().toString());

        // Course 3
        // Testing ScoreVisitor for Teacher
        System.out.println("Testing accept/visit for teacher\n");
        allTeachers.get(2).accept(visitor);
        // Testing ScoreVisitor for Assistant
        allAssistants.get(4).accept(visitor);
        allAssistants.get(5).accept(visitor);
        // Testing getBestStudent
        System.out.println("\nTESTING getBestStudent\nStrategy - " + catalog.courses.get(1).getStrategy());
        System.out.println(catalog.courses.get(2).getBestStudent().toString());

        // Testing sorting
        System.out.println("\nTESTING sortStudents using comparator\n");
        for(int i = 0 ; i < catalog.courses.size(); i++) {
            for (Map.Entry<String, Group> entry : catalog.courses.get(i).getGroups().entrySet()) {
                entry.getValue().sort();
                System.out.println(entry.getValue());
            }
        }

        // Testing student frame
//        MyFrame frame = new MyFrame(allStudents.get(0));
//
//        // Testing assistant frame
//        TeacherAssistantFrame frame1 = new TeacherAssistantFrame(allAssistants.get(2), "Assistant", visitor);
//
//        // Testing parent frame
//        ParentFrame frame2 = new ParentFrame((Parent) catalog.getObservers().get(0));


        // Example Teacher/Assistant: User: Sonia Lupu | Password: viorelteiubesc
        // Example Student: User: Gigel Frone | Password: "Gigel Frone123"
        // Example Parent: User: Maria Frone | Password: "Maria Frone123"
        Login l = new Login(visitor);

    }
}
