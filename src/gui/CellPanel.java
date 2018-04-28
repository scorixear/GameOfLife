package gui;

import gamemechanic.Cell;

import javax.swing.*;

/**
 * author: Paul Keller
 * date: 27.04.2018
 * version: 1.0
 */
/*
 * Die CellPanel-Klasse ist ein JPanel welches Informationen über die Zelle und ihre Koordinaten enthält, welche es bildlich darstellt.
 */
class CellPanel extends JPanel {
    private Cell c;
    CellPanel(Cell c){
        this.c=c;
    }

    int getRow(){
        return c.getRow();
    }
    int getCol(){
        return c.getCol();
    }
}
