import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.*;

public class MyFrame extends JFrame implements ListSelectionListener {
    JList list;
    DefaultListModel defaultList;
    JScrollPane listScroller;
    Student student;
    public MyFrame(Student student) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setMinimumSize(new Dimension(700, 500));
        setResizable(false);
        setLayout(new BorderLayout());

        this.student = student;

        // list with courses
        defaultList = new DefaultListModel();
        Border border = BorderFactory.createLineBorder(new Color(179, 62, 213), 2);
        // get courses
        for (int i = 0; i < Catalog.getInstance().courses.size(); i++) {
            if(Catalog.getInstance().courses.get(i).getAllStudents().contains(student)) {
                defaultList.addElement(Catalog.getInstance().courses.get(i));
            }
        }

        list = new JList(defaultList);
        list.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        list.addListSelectionListener(this);
        list.setBackground(new Color(233, 153, 253));
        listScroller = new JScrollPane(list);
        listScroller.setLayout(new ScrollPaneLayout());
        listScroller.setBorder(border);

        GradientPanel panel1 = new GradientPanel(new Color(145, 241, 247), new Color(179, 62, 213));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setBorder(border);
        JLabel studentPage = new JLabel("STUDENT PAGE");
        studentPage.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        studentPage.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(studentPage);
        JLabel studentName = new JLabel( "Student - " + student.toString());
        studentName.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        studentName.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(studentName);
        add(panel1, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(145, 241, 247));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        JLabel attendingCourses = new JLabel("Hey there, " + student.getFirstName() + "! Here are your " +
                "courses this semester.");
        attendingCourses.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        attendingCourses.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(attendingCourses, c);
        c.insets = new Insets(15,0,0,0);
        c.gridx=0;
        c.gridy=1;
        panel.add(listScroller, c);

        add(panel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
    public static void main(String[] args) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        boolean adjust = e.getValueIsAdjusting();
        if(!adjust) {
            JList ls = (JList)e.getSource();
            Course course = (Course)ls.getSelectedValue();
            JFrame courseFrame = new JFrame();
            courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            courseFrame.setSize(400, 500);
            courseFrame.setMinimumSize(new Dimension(400, 500));
            courseFrame.setResizable(false);

            GradientPanel panel = new GradientPanel(new Color(145, 241, 247), new Color(179, 62, 213));
            panel.setLayout(new GridBagLayout());

            JLabel courseHolder = new JLabel("Course Holder - " + course.getCourseHolder().toString());
            courseHolder.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            Double grade = null;
            if(course.getGrade(student) != null) {
                grade = course.getGrade(student).getPartialScore();
            }
            JLabel partialScore = new JLabel("Partial score - " + grade);
            partialScore.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            if(course.getGrade(student) != null) {
                grade = course.getGrade(student).getExamScore();
            }
            JLabel examScore = new JLabel("Exam score - " + grade);
            examScore.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            JLabel ID = new JLabel();
            JLabel assistant = new JLabel();
            for (Map.Entry<String, Group> entry: course.getGroups().entrySet()) {
                if(entry.getValue().contains(student)) {
                    assistant.setText("Assistant - " + entry.getValue().getAssistant());
                    assistant.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                    ID.setText("Group ID - " + entry.getValue().getID());
                    ID.setFont(new Font("Century Gothic", Font.PLAIN, 18));
                }
            }
            DefaultListModel defaultList2 = new DefaultListModel();
            for(Assistant a: course.getAssistants()) {
                defaultList2.addElement(a);
            }
            Border border = BorderFactory.createLineBorder(new Color(179, 62, 213), 2);
            JList assistants = new JList(defaultList2);
            assistants.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            assistants.setBackground(new Color(233, 153, 253));
            assistants.setBorder(border);
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx=0;
            c.gridy=0;
            panel.add(courseHolder, c);
            c.gridy=1;
            panel.add(assistants, c);
            c.gridy=2;
            panel.add(ID, c);
            c.gridy=3;
            panel.add(assistant, c);
            c.gridy=4;
            panel.add(partialScore, c);
            c.gridy=5;
            panel.add(examScore, c);

            courseFrame.add(panel);

            courseFrame.pack();
            courseFrame.setVisible(true);
        }
    }
}
