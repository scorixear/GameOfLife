package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
/*
*   Eigene JPanels um Koordinaten des jPanels zu bekommen
*   Setzen implementieren (mögliche Funktion im GolFrame)
*   New Layout überarbeiten
*   Malen implementieren
*
 */
public class GolMouseListener implements MouseListener {
    boolean clickactivate;
    boolean drawactivate;
    GolFrame golframe;
    public GolMouseListener(GolFrame gol){
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
