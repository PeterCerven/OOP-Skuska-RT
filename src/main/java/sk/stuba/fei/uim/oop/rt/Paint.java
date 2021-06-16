package sk.stuba.fei.uim.oop.rt;
import javax.swing.*;
import java.awt.*;

public class Paint {

    public static final String KRESLENIE = "KRESLENIE";
    public static final String PRESUVANIE = "PRESUVANIE";
    public static final String FARBA = "FARBA";

    public Paint() {
        JFrame frame = new JFrame("Skuska OOP RT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,900);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel sideMenu = new JPanel();
        Logic logic = new Logic(sideMenu);
        frame.add(logic.getRender(), BorderLayout.CENTER);



        JButton first = new JButton(KRESLENIE);
        first.addActionListener(logic);
        JButton second = new JButton(PRESUVANIE);
        second.addActionListener(logic);
        JButton third = new JButton(FARBA);
        third.addActionListener(logic);

        sideMenu.setLayout(new GridLayout(1, 4));
        sideMenu.add(first);
        sideMenu.add(second);
        sideMenu.add(third);
        sideMenu.add(logic.getLabel());
        frame.add(sideMenu, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }
}
