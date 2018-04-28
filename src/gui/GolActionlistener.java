package gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
/*
 * Der GolActionListener ist für die Funktionen der einzelnen JMenuItems aus den GolFrames zuständig. Dabei wird mit mit einem Switch-Case unterschieden und einzelnen Zellen und JPanels auf
 * andere Zustände gesetzt. Entweder werden direkt hier die Zellen bearbeitet oder eine Variable für den GolMouseListener wird verändert, welche dort die Weiterbearbeitung der Zellen ermöglicht
 */
public class GolActionlistener implements ActionListener {
    private GolFrame golframe;
    private Color[][] colorsets = {{Color.gray,Color.red},{Color.gray,Color.green},{Color.red,Color.green},{Color.white, Color.red}};
    GolActionlistener(GolFrame golframe)
    {
     this.golframe=golframe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Laufen":
                golframe.gol.start();
                golframe.mouseListener.clickactivate=false;
                golframe.mouseListener.drawactivate=false;

                break;
            case "Setzen":
                golframe.gol.stop();
                golframe.mouseListener.clickactivate=true;
                golframe.mouseListener.drawactivate=false;

                break;
            case "Malen":
                golframe.gol.stop();
                golframe.mouseListener.clickactivate=false;
                golframe.mouseListener.drawactivate=true;

                break;
            case "Random füllen":
                    for(int i=0;i<golframe.panels.length;i++)
                    {
                        for(int j=0;j<golframe.panels[i].length;j++)
                        {
                            CellPanel c = golframe.panels[i][j];
                            if(new Random().nextBoolean())
                            {
                                c.setBackground(golframe.alive);
                                golframe.gol.getCells()[c.getRow()][c.getCol()].setAlife(true);
                            }
                            else
                            {
                                c.setBackground(golframe.dead);
                                golframe.gol.getCells()[c.getRow()][c.getCol()].setAlife(false);
                            }

                        }
                    }
                break;
            case "Gleiter":
                for(CellPanel[] cp:golframe.panels)
                {
                    for(CellPanel c :cp)
                    {
                        c.setBackground(golframe.dead);
                        golframe.gol.getCells()[c.getRow()][c.getCol()].setAlife(false);
                    }
                }
                try{
                    setCellAlive(0,1);
                    setCellAlive(1,2);
                    setCellAlive(2,0);
                    setCellAlive(2,1);
                    setCellAlive(2,2);

                }catch(ArrayIndexOutOfBoundsException ignored) { }
                break;
            case "F-Population":
                golframe.gol.stop();
                for(CellPanel[] cp:golframe.panels)
                {
                    for(CellPanel c :cp)
                    {
                        c.setBackground(golframe.dead);
                        golframe.gol.getCells()[c.getRow()][c.getCol()].setAlife(false);
                    }
                }
                try{
                    setCellAlive(golframe.rows/2-1,golframe.cols/2);
                    setCellAlive(golframe.rows/2-1,golframe.cols/2+1);
                    setCellAlive(golframe.rows/2,golframe.cols/2-1);
                    setCellAlive(golframe.rows/2,golframe.cols/2);
                    setCellAlive(golframe.rows/2+1,golframe.cols/2);

                }catch(ArrayIndexOutOfBoundsException ignored) {
                }

                break;
            case "Spaceship":
                for(CellPanel[] cp:golframe.panels)
                {
                    for(CellPanel c :cp)
                    {
                        c.setBackground(golframe.dead);
                        golframe.gol.getCells()[c.getRow()][c.getCol()].setAlife(false);
                    }
                }
                try{
                    setCellAlive(golframe.rows/2-1,golframe.cols/2);
                    setCellAlive(golframe.rows/2-1,golframe.cols/2+1);
                    setCellAlive(golframe.rows/2,golframe.cols/2-2);
                    setCellAlive(golframe.rows/2,golframe.cols/2-1);
                    setCellAlive(golframe.rows/2,golframe.cols/2+1);
                    setCellAlive(golframe.rows/2,golframe.cols/2+2);
                    setCellAlive(golframe.rows/2+1,golframe.cols/2-2);
                    setCellAlive(golframe.rows/2+1,golframe.cols/2-1);
                    setCellAlive(golframe.rows/2+1,golframe.cols/2);
                    setCellAlive(golframe.rows/2+1,golframe.cols/2+1);
                    setCellAlive(golframe.rows/2+2,golframe.cols/2-1);
                    setCellAlive(golframe.rows/2+2,golframe.cols/2);

                }catch(ArrayIndexOutOfBoundsException ignored) { }
                break;
            case "Neues Layout":
                GolFrame newChild = new GolFrame(golframe,golframe.clonecount+1);
                golframe.desk.addChild(newChild);
                int r = (int) (Math.random() * 4);

                newChild.alive = colorsets[r][1];
                newChild.dead = colorsets[r][0];



                break;
            case "Schneller":
                golframe.gol.setSpeed(golframe.gol.getSpeed() * 2);

                break;
            case "Langsamer":
                golframe.gol.setSpeed(golframe.gol.getSpeed() / 2.0);

                break;
            case "Grau - Rot":
                golframe.alive = Color.RED;
                golframe.dead = Color.GRAY;

                break;
            case "Grau - Gelb":
                golframe.alive = Color.YELLOW;
                golframe.dead = Color.GRAY;

                break;
            case "Rot - Grün":
                golframe.alive = Color.GREEN;
                golframe.dead = Color.RED;

                break;
            case "Weiß - Rot":
                golframe.alive = Color.RED;
                golframe.dead = Color.WHITE;

                break;
        }
    }
    private void setCellAlive(int i, int j) throws ArrayIndexOutOfBoundsException
    {
        CellPanel c = golframe.panels[i][j];
        c.setBackground(golframe.alive);
        golframe.gol.getCells()[c.getRow()][c.getCol()].setAlife(true);
    }
}
