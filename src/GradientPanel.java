import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {
    Color c1, c2;
    public GradientPanel(Color c1, Color c2) {
        this.c1 = c1;
        this.c2 = c2;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        GradientPaint gradientPaint = new GradientPaint(0, getHeight(), c1,
                getWidth(), 0, c2);
        graphics2D.setPaint(gradientPaint);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }
}
