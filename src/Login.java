import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;
/*
 * Created by JFormDesigner on Wed Jan 11 13:23:59 EET 2023
 */



/**
 * @author razva
 */
public class Login extends JFrame implements ActionListener {
    ScoreVisitor visitor;
    public Login(ScoreVisitor visitor) {
        this.visitor = visitor;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        panel1 = new JPanel();
        label3 = new JLabel();
        panel4 = new JPanel();
        textField1 = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        button1 = new JButton();
        label6 = new JLabel();
        passwordField1 = new JPasswordField();
        label7 = new JLabel();
        panel3 = new JPanel();

        //======== this ========
        setBackground(Color.white);
        var contentPane = getContentPane();

        //======== panel1 ========
        {
            panel1.setBackground(new Color(0x4a3cb8));

            //---- label3 ----
            label3.setIcon(new ImageIcon("G:\\java nou\\TemaPOO\\src\\rsz_3pic.png"));

            //======== panel4 ========
            {
                panel4.setBackground(new Color(0xdc2461));
                panel4.setBorder(null);

                //---- textField1 ----
                textField1.setBackground(Color.white);

                //---- label1 ----
                label1.setText("User");
                label1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                label1.setForeground(Color.black);
                label1.setHorizontalAlignment(SwingConstants.CENTER);

                //---- label2 ----
                label2.setText("Password");
                label2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
                label2.setForeground(Color.black);
                label2.setHorizontalAlignment(SwingConstants.CENTER);

                //---- label4 ----
                label4.setText("Login");
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                label4.setFont(new Font("Segoe UI", Font.PLAIN, 34));
                label4.setForeground(Color.white);

                //---- label5 ----
                label5.setText("Hello! Let's get started!");
                label5.setForeground(Color.white);

                //---- button1 ----
                button1.setText("LOGIN");
                button1.setBackground(new Color(0x4a3cb0));
                button1.setFont(new Font("Segoe UI", Font.BOLD, 12));
                button1.addActionListener(this);

                //---- label6 ----
                label6.setIcon(new ImageIcon("G:\\java nou\\TemaPOO\\src\\rsz_sample_user_icon.png"));
                label6.setBackground(Color.white);
                label6.setForeground(Color.white);

                //---- passwordField1 ----
                passwordField1.setBackground(Color.white);

                //---- label7 ----
                label7.setIcon(new ImageIcon("G:\\java nou\\TemaPOO\\src\\hide-password-2-32.png"));

                GroupLayout panel4Layout = new GroupLayout(panel4);
                panel4.setLayout(panel4Layout);
                panel4Layout.setHorizontalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addGap(94, 94, 94)
                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label5))
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addGroup(panel4Layout.createParallelGroup()
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addGap(66, 66, 66)
                                    .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label1, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))
                                .addGroup(panel4Layout.createSequentialGroup()
                                    .addGap(110, 110, 110)
                                    .addComponent(button1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
                                .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addGap(3, 3, 3)
                                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)))
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                            .addContainerGap(34, Short.MAX_VALUE)
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                            .addGap(260, 260, 260))
                );
                panel4Layout.setVerticalGroup(
                    panel4Layout.createParallelGroup()
                        .addGroup(panel4Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label5)
                            .addGap(33, 33, 33)
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel4Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addComponent(label6, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                .addComponent(textField1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                            .addGap(5, 5, 5)
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panel4Layout.createParallelGroup()
                                .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label7))
                            .addGap(30, 30, 30)
                            .addComponent(button1)
                            .addContainerGap(52, Short.MAX_VALUE))
                );
            }

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(panel4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addComponent(panel4, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(label3, GroupLayout.Alignment.TRAILING)
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        pack();
        setLocationRelativeTo(getOwner());

        //======== panel3 ========
        {

            GroupLayout panel3Layout = new GroupLayout(panel3);
            panel3.setLayout(panel3Layout);
            panel3Layout.setHorizontalGroup(
                panel3Layout.createParallelGroup()
                    .addGap(0, 315, Short.MAX_VALUE)
            );
            panel3Layout.setVerticalGroup(
                panel3Layout.createParallelGroup()
                    .addGap(0, 375, Short.MAX_VALUE)
            );
        }
        setVisible(true);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel panel1;
    private JLabel label3;
    private JPanel panel4;
    private JTextField textField1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label4;
    private JLabel label5;
    private JButton button1;
    private JLabel label6;
    private JPasswordField passwordField1;
    private JLabel label7;
    private JPanel panel3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on

    public static void main(String[] args) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int ok = 0;
        if (e.getSource() instanceof JButton) {
            String name = textField1.getText();
            char pass[] = passwordField1.getPassword();
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < pass.length; i++) {
                password.append(pass[i]);
            }
            for (int i = 0; i < Catalog.getInstance().courses.size(); i++) {
                for (Student s: Catalog.getInstance().courses.get(i).getAllStudents()) {
                    if (s.toString().equals(name) && s.getPassword().equals(password.toString())) {
                        MyFrame frame = new MyFrame(s);
                        ok = 1;
                        break;
                    }
                }
                for (Assistant a: Catalog.getInstance().courses.get(i).getAssistants()) {
                    if (a.toString().equals(name) && a.getPassword().equals(password.toString())) {
                        TeacherAssistantFrame assistantFrame = new TeacherAssistantFrame(a, "Assistant", visitor);
                        ok = 1;
                        break;
                    }
                }
                for (Observer o: Catalog.getInstance().getObservers()) {
                    if (((Parent)o).toString().equals(name) && ((Parent)o).getPassword().equals(password.toString())) {
                        ParentFrame parentFrame = new ParentFrame((Parent) o);
                        ok = 1;
                        break;
                    }
                }
                if (Catalog.getInstance().courses.get(i).getCourseHolder().toString().equals(name) && Catalog
                        .getInstance().courses.get(i).getCourseHolder().getPassword().equals(password.toString())) {
                    TeacherAssistantFrame teacherFrame = new TeacherAssistantFrame(Catalog.getInstance().courses.
                            get(i).getCourseHolder(), "Teacher", visitor);
                    ok = 1;
                    break;
                }
                if (ok == 1)
                    break;
            }
        }
    }
}
