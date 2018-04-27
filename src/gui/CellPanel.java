package gui;

import gamemechanic.Cell;

import javax.swing.*;

/**
 * author: Paul Keller
 * date: 27.04.2018
 * version: 1.0
 */
public class CellPanel extends JPanel {
    Cell c;
    public CellPanel(Cell c){
        this.c=c;
    }

    public Cell getC() {
        return c;
    }
    public int getRow(){
        return c.getRow();
    }
    public int getCol(){
        return c.getCol();
    }
}
