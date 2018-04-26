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
    GolFrame golframe;
    public GolMouseListener(GolFrame gol){
        clickactivate=false;
        golframe=gol;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Color cell = ((JPanel)e.getSource()).getBackground();
        if(golframe.alive.equals(cell))
        {

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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
