package gui;

import gamemechanic.Cell;
import gamemechanic.GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
public class GolFrame extends JInternalFrame implements Observer{
    MainFrame desk;
    private String titel;
    private JMenuBar menubar;
    GameOfLife gol;
    private JPanel[][] panels;
    GolMouseListener mouseListener;
    int rows, cols;
    private GolFrame clone;
    Color alive, dead;

    GolFrame(GolFrame clone)
    {
       this(clone.titel,clone.desk,new Point(clone.getLocation().x + 20, clone.getLocation().y + 20),new Dimension(clone.getHeight(), clone.getWidth()), clone.rows,clone.cols,  clone);
    }
    GolFrame(String titel, MainFrame desk, Point p, Dimension size, int rows, int cols)
    {
        this(titel, desk, p, size, rows, cols,  null);
    }

    GolFrame(String titel, MainFrame desk, Point p, Dimension size, int rows, int cols, GolFrame clone){
        super(titel+" "+desk.getChildcount(),true,true,true,true);
        menubar = new JMenuBar();

        GolActionlistener golActionlistener = new GolActionlistener(this);
        JMenu menu = new JMenu("Modus");
        JMenuItem laufen = new JMenuItem("Laufen", KeyEvent.VK_L);
        laufen.addActionListener(golActionlistener);
        JMenuItem setzen = new JMenuItem("Setzen", KeyEvent.VK_S);
        setzen.addActionListener(golActionlistener);
        JMenuItem malen = new JMenuItem("Malen", KeyEvent.VK_M);
        malen.addActionListener(golActionlistener);
        JMenuItem newLayout = new JMenuItem("Neues Layout", KeyEvent.VK_N);
        newLayout.addActionListener(golActionlistener);
        menu.add(laufen); menu.add(setzen); menu.add(malen); menu.add(newLayout);

        JMenu menu2 = new JMenu("Geschwindigkeit");
       JMenuItem schneller = new JMenuItem("Schneller", KeyEvent.VK_X);
       schneller.addActionListener(golActionlistener);
       JMenuItem langsamer = new JMenuItem("Langsamer",KeyEvent.VK_Y);
       langsamer.addActionListener(golActionlistener);
       menu2.add(schneller); menu2.add(langsamer);

       JMenu menu3 = new JMenu("Farbe");
       JMenuItem graurot = new JMenuItem("Grau - Rot");
       graurot.addActionListener(golActionlistener);
       JMenuItem graugelb = new JMenuItem("Grau - Gelb");
       graugelb.addActionListener(golActionlistener);
       JMenuItem rotgrün = new JMenuItem("Rot - Grün");
       rotgrün.addActionListener(golActionlistener);
       JMenuItem weißrot = new JMenuItem("Weiß - Rot");
       weißrot.addActionListener(golActionlistener);
       menu3.add(graurot);menu3.add(graugelb); menu3.add(rotgrün);menu3.add(weißrot);

       menubar.add(menu);menubar.add(menu2);menubar.add(menu3);
       setJMenuBar(menubar);

       alive = Color.RED;
       dead = Color.GRAY;


        this.titel=titel;
        this.desk=desk;
        this.rows=rows;
        this.cols=cols;
        this.clone=clone;

        mouseListener=new GolMouseListener();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(p);
        setSize(size);
        setUp();
    }

    private void setUp() {
        if(clone!=null)
        {
            gol=clone.gol;
            GridLayout layout;
                layout = new GridLayout(cols,rows);
                panels=new JPanel[cols][rows];
                for(int i=0;i<rows;i++)
                {
                    for(int j=0;j<cols;j++) {
                        panels[j][i]=new JPanel();
                        panels[j][i].addMouseListener(mouseListener);
                        add(panels[j][i]);
                    }
                }
            layout.setHgap(1);
            layout.setVgap(1);
            setLayout(layout);
            gol.addObserver(this);

        }
        else
        {
            gol = new GameOfLife(rows,cols);
            gol.fillRandom();
            gol.addObserver(this);
            GridLayout layout = new GridLayout(rows,cols);
            layout.setHgap(1);
            layout.setVgap(1);
            setLayout(layout);
            panels=new JPanel[rows][cols];
            for(int i=0;i<gol.getCells().length;i++)
            {
                for(int j=0;j<gol.getCells()[i].length;j++) {
                    panels[i][j]=new JPanel();
                    panels[i][j].addMouseListener(mouseListener);
                    add(panels[i][j]);
                }
            }
            update(gol,null);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        Cell[][] cells = gol.getCells();
        if(clone!=null)
        {
            for(int i = 0;i<rows;i++) {
                for(int j=0;j<cols;j++) {
                    panels[j][i].setBackground(cells[i][j].isAlife()?alive:dead);
                }
            }

        }
        else
        {
            for(int i = 0;i<rows;i++) {
                for(int j=0;j<cols;j++) {
                    panels[i][j].setBackground(cells[i][j].isAlife()?alive:dead);
                }
            }
        }


    }


}
