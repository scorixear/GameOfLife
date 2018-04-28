package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * author: Paul Keller
 * date: 27.04.2018
 * version: 1.0
 */
/*
 * Der GolCreateFrame ist ein einfacher Frame mit zwei Eingabefeldern für Reihen- und Spaltenanzahl
 */
public class GolCreateFrame extends JInternalFrame implements ActionListener, KeyListener {
    private MainFrame desk;
    private JTextField rowField, colField;
    GolCreateFrame(MainFrame desk, Point p){
        this.desk = desk;
        setSize(200,100);
        setLocation(p);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JButton createButton = new JButton("Create Game of Life");
        createButton.addActionListener(this);
        rowField = new JTextField();
        rowField.setToolTipText("Reihenanzahl");
        rowField.addKeyListener(this);
        colField = new JTextField();
        colField.setToolTipText("Spaltenanzahl");
        colField.addKeyListener(this);
        setLayout(new GridLayout(3,1));
        add(rowField);
        add(colField);
        add(createButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int rows;
        int cols;
        try{
            rows = Integer.parseInt(rowField.getText());
        }catch(NumberFormatException ex)
        {
            rowField.setBackground(Color.RED);
            return;
        }
        try{
            cols = Integer.parseInt(colField.getText());
        }catch(NumberFormatException ex)
        {
            colField.setBackground(Color.RED);
            return;
        }
        desk.addChild(new GolFrame("Game of Life",desk,new Point(new Random().nextInt(100),new Random().nextInt(100)),rows,cols));

    }

    @Override
    public void keyTyped(KeyEvent e) {
        ((JTextField)e.getSource()).setBackground(Color.WHITE);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
