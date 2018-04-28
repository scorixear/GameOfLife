package gui;

import gamemechanic.Cell;
import gamemechanic.GameOfLife;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

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
    CellPanel[][] panels;
    GolMouseListener mouseListener;
    int rows, cols;
    GolFrame clone;
    Color alive, dead;
    int clonecount;
    private Dimension boundary=new Dimension(600,600);
    GolFrame(GolFrame clone, int clonecount)
    {
       this(clone.titel,clone.desk,new Point(clone.getLocation().x + 20, clone.getLocation().y + 20), clone.rows,clone.cols, clone, clonecount);
    }
    GolFrame(String titel, MainFrame desk, Point p, int rows, int cols)
    {
        this(titel, desk, p, rows, cols,  null, 0);
    }

    GolFrame(String titel, MainFrame desk, Point p, int rows, int cols, GolFrame clone, int clonecount){
        super(titel+" "+desk.getChildcount(),true,true,true,true);
        menubar = new JMenuBar();
        this.clonecount=clonecount;
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
        menu.add(laufen); menu.add(setzen); menu.add(malen);menu.add(newLayout);

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

       JMenu menu4 = new JMenu("Prepared");
        JMenuItem fillRandom = new JMenuItem("Random füllen");
        fillRandom.addActionListener(golActionlistener);
        JMenuItem glider = new JMenuItem("Gleiter");
        glider.addActionListener(golActionlistener);
        JMenuItem fpop=new JMenuItem("F-Population");
        fpop.addActionListener(golActionlistener);
        JMenuItem ship = new JMenuItem("Spaceship");
        ship.addActionListener(golActionlistener);
        menu4.add(fillRandom);menu4.add(glider);menu4.add(fpop);menu4.add(ship);

       menubar.add(menu);menubar.add(menu2);menubar.add(menu3);menubar.add(menu4);
       setJMenuBar(menubar);

       alive = Color.RED;
       dead = Color.GRAY;


        this.titel=titel;
        this.desk=desk;
        this.rows=rows;
        this.cols=cols;
        this.clone=clone;

        mouseListener=new GolMouseListener(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(p);
        setSize(getScaledDimension(new Dimension(cols*25,rows*25),boundary));
        setUp();
    }

    private void setUp() {
        if(clone!=null)
        {

            gol=clone.gol;
            gol.addObserver(this);
            GridLayout layout;
            if(clonecount%4==1)
            {
                layout = new GridLayout(cols,rows);
                setLayout(layout);
                setSize(getScaledDimension(new Dimension(rows*25,cols*25),boundary));
                panels=new CellPanel[cols][rows];
                for(int row=0;row<cols;row++)
                {
                    for(int col=0;col<rows;col++) {

                        panels[row][col]=new CellPanel(gol.getCells()[col][cols-(row+1)]);
                        panels[row][col].addMouseListener(mouseListener);
                        add(panels[row][col]);
                    }
                }
            } else if(clonecount%4==2)
            {
                layout = new GridLayout(rows,cols);
                setLayout(layout);
                setSize(getScaledDimension(new Dimension(cols*25,rows*25),boundary));
                panels=new CellPanel[rows][cols];
                for(int row=0;row<rows;row++)
                {
                    for(int col=0;col<cols;col++) {

                        panels[row][col]=new CellPanel(gol.getCells()[rows-(row+1)][cols-(col+1)]);
                        panels[row][col].addMouseListener(mouseListener);
                        add(panels[row][col]);
                    }
                }
            } else if(clonecount%4==3)
            {
                layout = new GridLayout(cols,rows);
                setLayout(layout);
                setSize(getScaledDimension(new Dimension(rows*25,cols*25),boundary));
                panels=new CellPanel[cols][rows];
                for(int row=0;row<cols;row++)
                {
                    for(int col=0;col<rows;col++) {

                        panels[row][col]=new CellPanel(gol.getCells()[col][cols-(row+1)]);
                        panels[row][col].addMouseListener(mouseListener);
                        add(panels[row][col]);
                    }
                }
            } else
            {
                layout = new GridLayout(rows,cols);
                setLayout(layout);
                setSize(getScaledDimension(new Dimension(cols*25,rows*25),boundary));
                panels=new CellPanel[rows][cols];
                for(int row=0;row<rows;row++)
                {
                    for(int col=0;col<cols;col++) {

                        panels[row][col]=new CellPanel(gol.getCells()[row][col]);
                        panels[row][col].addMouseListener(mouseListener);
                        add(panels[row][col]);
                    }
                }
            }
            layout.setHgap(rows>60?0:1);
            layout.setVgap(cols>60?0:1);
        }
        else
        {
            gol = new GameOfLife(rows,cols);
            gol.addObserver(this);
            GridLayout layout = new GridLayout(rows,cols);
            setSize(getScaledDimension(new Dimension(rows*25,cols*25),boundary));
            layout.setHgap(rows>60?0:1);
            layout.setVgap(cols>60?0:1);
            setLayout(layout);
            panels=new CellPanel[rows][cols];
            for(int i=0;i<gol.getCells().length;i++)
            {
                for(int j=0;j<gol.getCells()[i].length;j++) {
                    panels[i][j]=new CellPanel(gol.getCells()[i][j]);
                    panels[i][j].addMouseListener(mouseListener);
                    add(panels[i][j]);
                }
            }

        }
        update(gol,null);
    }

    @Override
    public void update(Observable o, Object arg) {
        Cell[][] cells = gol.getCells();
        if(clonecount%4==1)
        {
            for(int row = 0;row<cols;row++) {
                for(int col=0;col<rows;col++) {
                    panels[row][col].setBackground(cells[col][cols-(row+1)].isAlife()?alive:dead);
                }
            }
        } else if(clonecount%4==2)
        {
            for(int row = 0;row<rows;row++) {
                for(int col=0;col<cols;col++) {
                    panels[row][col].setBackground(cells[rows - (row + 1)][cols - (col + 1)].isAlife() ? alive : dead);

                }
            }
        }
        else if(clonecount%4==3)
        {
            for(int row = 0;row<cols;row++) {
                for(int col=0;col<rows;col++) {
                    panels[row][col].setBackground(cells[rows-(col+1)][row].isAlife()?alive:dead);

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
        repaint();


    }
    private Dimension getScaledDimension(Dimension oldSize, Dimension boundary) {

        int original_width = oldSize.width;
        int original_height = oldSize.height;
        int bound_width = boundary.width;
        int bound_height = boundary.height;
        int new_width = original_width;
        int new_height = original_height;

        // first check if we need to scale width
        if (original_width > bound_width) {
            //scale width to fit
            new_width = bound_width;
            //scale height to maintain aspect ratio
            new_height = (new_width * original_height) / original_width;
        }

        // then check if we need to scale even with the new height
        if (new_height > bound_height) {
            //scale height to fit instead
            new_height = bound_height;
            //scale width to maintain aspect ratio
            new_width = (new_height * original_width) / original_height;
        }

        return new Dimension(new_width, new_height);
    }


}
