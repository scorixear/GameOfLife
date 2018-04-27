package gui;

import javax.swing.*;
import java.awt.*;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
public class MainFrame extends JFrame {
    private JDesktopPane desk;
    private int childcount=0;
    public MainFrame(){
        desk = new JDesktopPane();
        desk.setDesktopManager(new DefaultDesktopManager());
        setContentPane(desk);

        Dimension userd = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(userd.width,userd.height-40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(0,0);
        setVisible(true);

    }

    protected void addChild(JInternalFrame child) {
       desk.add(child);
       child.setVisible(true);
    }
    protected int getChildcount(){return childcount++;}

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.addChild(new GolCreateFrame(frame,new Point(0,0)));
    }
}
