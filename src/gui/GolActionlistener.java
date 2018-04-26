package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
public class GolActionlistener implements ActionListener {
    private GolFrame golframe;
    private Color[][] colorsets = {{Color.gray,Color.red},{Color.gray,Color.green},{Color.red,Color.green},{Color.white, Color.red}};
    public GolActionlistener(GolFrame golframe)
    {
     this.golframe=golframe;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Laufen":
                golframe.gol.start();
                golframe.mouseListener.clickactivate=false;

                break;
            case "Setzen":
                golframe.gol.stop();
                golframe.mouseListener.clickactivate=true;

                break;
            case "Malen":
                golframe.gol.stop();
                golframe.mouseListener.clickactivate=false;

                break;
            case "Neues Layout":
                GolFrame newChild = new GolFrame(golframe);
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
}
