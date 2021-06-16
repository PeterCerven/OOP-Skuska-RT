package sk.stuba.fei.uim.oop.rt;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Render extends JPanel {

    @Getter
    private List<Tree> treeArray;
    private Tree rendered;
    private int startX;
    private int startY;

    public Render() {
        this.setBackground(Color.CYAN);
        this.treeArray = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.treeArray.forEach(tree -> tree.draw(g));
    }

    public void startDraw(MouseEvent e, Color c) {
        this.startX = e.getX();
        this.startY = e.getY();
        this.rendered = new Tree(e.getX(), e.getY(), 0, 0, c);
        this.treeArray.add(rendered);
        this.repaint();
    }

    public void startDrag(MouseEvent e) {
        for (int i = this.treeArray.size() - 1; i >= 0; i--) {
            Tree tree = this.treeArray.get(i);
            if (tree.clicked(e.getX(), e.getY())) {
                this.rendered = tree;
                this.startX = e.getX() - tree.getX();
                this.startY = e.getY() - tree.getY();
                break;
            }
        }
    }

    public void draw(MouseEvent e) {
        this.rendered.setX(Math.min(startX, e.getX()));
        this.rendered.setY(Math.min(startY, e.getY()));
        this.rendered.setWidth(Math.abs(e.getX() - startX));
        this.rendered.setHeight(Math.abs(e.getY() - startY));
        this.repaint();
    }

    public void drag(MouseEvent e) {
        if (this.rendered != null) {
            this.rendered.setX(e.getX() - startX);
            this.rendered.setY(e.getY() - startY);
        }
        this.repaint();
    }

    public void end(MouseEvent e) {
        this.startX = 0;
        this.startY = 0;
        this.rendered = null;
    }
}
