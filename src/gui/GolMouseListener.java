package gui;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
/*
 * der GolMouseListener handelt die Funktion Setzen und Malen
 */
public class GolMouseListener implements MouseListener {
    boolean clickactivate;
    boolean drawactivate;
    private GolFrame golframe;
    GolMouseListener(GolFrame gol){
        clickactivate=false;
        golframe=gol;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if(clickactivate)
        {
            CellPanel p = ((CellPanel)e.getSource());
            Color cell = p.getBackground();
            if(golframe.alive.equals(cell))
            {
                p.setBackground(golframe.dead);
                golframe.gol.getCells()[p.getRow()][p.getCol()].setAlife(false);

            }
            else
            {
                p.setBackground(golframe.alive);
                golframe.gol.getCells()[p.getRow()][p.getCol()].setAlife(true);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(drawactivate)
        {
            CellPanel p = ((CellPanel)e.getSource());
            Color cell = p.getBackground();
            if(golframe.alive.equals(cell))
            {
                p.setBackground(golframe.dead);
                golframe.gol.getCells()[p.getRow()][p.getCol()].setAlife(false);
            }
            else
            {
                p.setBackground(golframe.alive);
                golframe.gol.getCells()[p.getRow()][p.getCol()].setAlife(true);
            }
        }


    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
