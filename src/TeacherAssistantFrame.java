import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TeacherAssistantFrame  extends JFrame implements ActionListener {
    JList list;
    DefaultListModel defaultList;
    JScrollPane listScroller;
    User user;
    String type;
    ScoreVisitor visitor;
    public TeacherAssistantFrame(User user, String type, ScoreVisitor visitor) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setMinimumSize(new Dimension(700, 500));

        this.type = type;
        this.user = user;
        this.visitor = visitor;

        // list with courses
        defaultList = new DefaultListModel();
        if(type.equals("Assistant")) {
            defaultList.addAll(visitor.getPartialTuple((Assistant) user));
        } else {
            defaultList.addAll(visitor.getExamTuple((Teacher) user));
        }

        // get courses
        ArrayList<Course> courses = new ArrayList<Course>();
        for (int i = 0; i < Catalog.getInstance().courses.size(); i++) {
            if(type == "Assistant" && Catalog.getInstance().courses.get(i).getAssistants().contains((Assistant)user)) {
                courses.add(Catalog.getInstance().courses.get(i));
            }
            else if (type == "Teacher" && Catalog.getInstance().courses.get(i).getCourseHolder().equals((Teacher)user)) {
                courses.add(Catalog.getInstance().courses.get(i));
            }
        }

        list = new JList(defaultList);
        list.setFont(new Font("Century Gothic", Font.PLAIN, 18));
        list.setBackground(new Color(120, 2, 6));
        list.setForeground(Color.white);

        listScroller = new JScrollPane(list);
        listScroller.setLayout(new ScrollPaneLayout());

        GradientPanel panel1 = new GradientPanel(new Color(120, 2, 6), new Color(6, 17, 97));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        if(type == "Assistant") {
            JLabel assistantPage = new JLabel("ASSISTANT PAGE");
            assistantPage.setFont(new Font("Century Gothic", Font.PLAIN, 25));
            assistantPage.setAlignmentX(Component.CENTER_ALIGNMENT);
            assistantPage.setForeground(Color.WHITE);
            panel1.add(assistantPage);
            JLabel assistantName = new JLabel( "Assistant - " + user.toString());
            assistantName.setFont(new Font("Century Gothic", Font.PLAIN, 20));
            assistantName.setAlignmentX(Component.CENTER_ALIGNMENT);
            assistantName.setForeground(Color.WHITE);
            panel1.add(assistantName);
        } else if(type == "Teacher") {
            JLabel teacherPage = new JLabel("TEACHER PAGE");
            teacherPage.setFont(new Font("Century Gothic", Font.PLAIN, 25));
            teacherPage.setForeground(Color.WHITE);
            teacherPage.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel1.add(teacherPage);
            JLabel teacherName = new JLabel( "Teacher - " + user.toString());
            teacherName.setFont(new Font("Century Gothic", Font.PLAIN, 20));
            teacherName.setForeground(Color.WHITE);
            teacherName.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel1.add(teacherName);
        }
        Border border = BorderFactory.createLineBorder(new Color(120, 2, 6), 2);
        panel1.setBorder(border);
        add(panel1, BorderLayout.NORTH);

        int i = 0;
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(195, 55, 100));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = i;
        i++;
        JLabel attendingCourses = new JLabel("Courses");
        attendingCourses.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        attendingCourses.setAlignmentX(Component.CENTER_ALIGNMENT);
        attendingCourses.setForeground(Color.white);
        panel.add(attendingCourses, c);
        JLabel course = new JLabel();
        for(int j = 0; j < courses.size(); j++) {
            course.setText(courses.get(j).getName());
            course.setFont(new Font("Century Gothic", Font.PLAIN, 16));
            course.setAlignmentX(Component.CENTER_ALIGNMENT);
            course.setForeground(Color.white);
            c.gridy = i;
            panel.add(course, c);
            i++;
        }
        c.insets = new Insets(15,0,0,0);
        c.gridx = 0;
        c.gridy = i;
        i++;
        panel.add(listScroller, c);
        c.gridy = i;
        i++;
        JButton validateButton = new JButton("Validate grades");
        validateButton.setBackground(new Color(120, 2, 6));
        validateButton.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        validateButton.setForeground(Color.white);
        validateButton.addActionListener(this);
        panel.add(validateButton, c);

        add(panel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
    public static void main(String[] args) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            if (type.equals("Assistant")) {
                ((Assistant) user).accept(visitor);
            } else if (type.equals("Teacher")) {
                ((Teacher) user).accept(visitor);
            }
            listScroller.removeAll();
            listScroller.repaint();
            listScroller.setBackground(new Color(120, 2, 6));
        }
    }
}
