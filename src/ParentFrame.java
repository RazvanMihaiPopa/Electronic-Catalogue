import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParentFrame extends JFrame implements ActionListener {
    Parent parent;
    public ParentFrame(Parent parent) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setMinimumSize(new Dimension(700, 500));
        setResizable(false);
        setLayout(new BorderLayout());

        this.parent = parent;

        GradientPanel panel1 = new GradientPanel(new Color(52, 232, 158),new Color(15, 52, 67));
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));

        Border border = BorderFactory.createLineBorder(new Color(15, 52, 67), 2);
        panel1.setBorder(border);

        JLabel parentPage = new JLabel("PARENT PAGE");
        parentPage.setFont(new Font("Century Gothic", Font.PLAIN, 25));
        parentPage.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(parentPage);
        JLabel studentName = new JLabel( "Parent - " + parent.toString());
        studentName.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        studentName.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel1.add(studentName);
        add(panel1, BorderLayout.NORTH);

        GradientPanel mainPanel = new GradientPanel(new Color(67, 198, 172), new Color(25, 22, 84));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;

        JButton checkNotifications = new JButton("Check notifications");
        checkNotifications.setSize(150, 60);
        checkNotifications.setBackground(new Color(52, 232, 158));
        checkNotifications.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        checkNotifications.setBorder(border);
        checkNotifications.setAlignmentX(Component.CENTER_ALIGNMENT);
        checkNotifications.setAlignmentY(Component.CENTER_ALIGNMENT);
        checkNotifications.addActionListener(this);
        mainPanel.add(checkNotifications, c);
        add(mainPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);
    }
    public static void main(String[] args) {
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof JButton) {
            JFrame courseFrame = new JFrame();
            courseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            courseFrame.setSize(400, 500);
            courseFrame.setMinimumSize(new Dimension(400, 500));
            courseFrame.setResizable(false);

            GradientPanel panel = new GradientPanel(new Color(52, 232, 158),new Color(15, 52, 67));
            panel.setLayout(new GridBagLayout());

            DefaultListModel defaultList2 = new DefaultListModel();
            defaultList2.addAll(parent.notifications);
            JList notifications = new JList(defaultList2);
            notifications.setFont(new Font("Century Gothic", Font.PLAIN, 18));
            notifications.setBackground(new Color(52, 232, 158, 129));

            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx=0;
            c.gridy=0;
            panel.add(notifications, c);

            courseFrame.add(panel);

            courseFrame.pack();
            courseFrame.setVisible(true);
        }
    }
}
