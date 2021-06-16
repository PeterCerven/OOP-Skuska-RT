package sk.stuba.fei.uim.oop.rt;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Tree {
    @Setter @Getter
    private int x;
    @Setter @Getter
    private int y;
    @Setter @Getter
    private int height;
    @Setter @Getter
    private int width;
    private Color color;

    public Tree(int x, int y, int height, int width, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, width, (int) (height * (2.0/3.0)));
        g.fillRect(x + (int) (width * (1.0/3.0)), y + (int) (height * 0.5), (int) (width * (1.0/3.0)), (int) (height * 0.5));
        g.setColor(Color.BLACK);
    }

    public boolean clicked(int clickX, int clickY) {
        Rectangle rect = new Rectangle(x + (int) (width * (1.0/3.0)), y + (int) (height * 0.5), (int) (width * (1.0/3.0)), (int) (height * 0.5));
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, width, (int) (height * (2.0/3.0)));
        return rect.contains(clickX, clickY) || ellipse.contains(clickX, clickY);
    }
}
