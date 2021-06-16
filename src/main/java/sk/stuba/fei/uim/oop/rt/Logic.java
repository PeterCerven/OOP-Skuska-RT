package sk.stuba.fei.uim.oop.rt;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class Logic extends Adapter {

    @Getter
    private Render render;
    @Getter
    private JLabel label;
    private JPanel menu;
    private String action;
    private Color color;
    private int colorCounter;

    public Logic(JPanel sideMenu) {
        this.menu = sideMenu;
        this.render = new Render();
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
        this.colorCounter = 0;
        this.color = Color.RED;
        this.action = Paint.KRESLENIE;
        this.label = new JLabel();
        this.updateLabel();
    }

    public void repaint() {
        this.render.repaint();
    }

    private void updateLabel() {
        this.label.setText(this.action);
        this.menu.setBackground(this.color);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(Paint.FARBA)) {
            this.resolveColor();
        } else if (e.getActionCommand().equals(Paint.KRESLENIE)) {
            this.action = Paint.KRESLENIE;
        } else if (e.getActionCommand().equals(Paint.PRESUVANIE)) {
            this.action = Paint.PRESUVANIE;
        }
        this.updateLabel();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (this.action.equals(Paint.KRESLENIE)) {
            this.render.startDraw(e, this.color);
        } else if (this.action.equals(Paint.PRESUVANIE)) {
            this.render.startDrag(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (this.action.equals(Paint.KRESLENIE)) {
            this.render.draw(e);
        } else if (this.action.equals(Paint.PRESUVANIE)) {
            this.render.drag(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.action.equals(Paint.KRESLENIE) || this.action.equals(Paint.PRESUVANIE)) {
            this.render.end(e);
        }
    }

    private void resolveColor() {
        this.colorCounter++;
        if (this.colorCounter > 2) {
            this.colorCounter = 0;
        }
        if (this.colorCounter == 0) {
            this.color = Color.RED;
        } else if (this.colorCounter == 1) {
            this.color = Color.GREEN;
        } else {
            this.color = Color.BLUE;
        }
    }
}
