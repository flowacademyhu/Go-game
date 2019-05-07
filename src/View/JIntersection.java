package View;

import javax.swing.*;

public class JIntersection extends JButton {
    int xPosition;
    int yPosition;

    public JIntersection(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public JIntersection(Icon icon, int xPosition, int yPosition) {
        super(icon);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public JIntersection(String text, int xPosition, int yPosition) {
        super(text);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public JIntersection(Action a, int xPosition, int yPosition) {
        super(a);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public JIntersection(String text, Icon icon, int xPosition, int yPosition) {
        super(text, icon);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
}
