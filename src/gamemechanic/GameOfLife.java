package gamemechanic;
import java.util.Observable;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
/*
 * Die GameOfLife-Klasse repräsentiert das zweidimensionale Array aus Zellen. In der GameOfLife Klasse wird das starten und Stoppen des dazugehörigen Threads übernommen
 * sowie das erstellen von Zellen und das ausrechnen der nächsten Generation. Für den Nutzer dieses packages sollte kein Zugriff auf die Klasse GenerationThread und Cell nötig sein
 */
@SuppressWarnings("deprecation")
public class GameOfLife extends Observable {
    private Cell[][] cells;
    private GenerationThread thread;
    public GameOfLife(int rows, int cols)
    {
        cells =createNewGame(rows,cols);
        thread= new GenerationThread(this);
    }

    public void start(){
        if(!thread.isAlive())
        {
            double speed = thread.getSpeed();
            thread = new GenerationThread(this);
            thread.start();
            thread.setSpeed(speed);
        }

    }
    public void stop(){
        thread.stopThread();
    }
    public void setSpeed(double speed){
        thread.setSpeed(speed);
    }
    public double getSpeed(){
        return thread.getSpeed();
    }

    public Cell[][] getCells() {
        return cells;
    }

    void doGeneration(){
        int rows = cells.length;
        int cols = cells[0].length;
        Cell[][] nextGeneration = createNewGame(rows,cols);
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                nextGeneration[i][j]=new Cell(i,j);
                nextGeneration[i][j].setLife(cells[i][j].checkNeighbours(cells));

            }
        }
        cells=nextGeneration;
        setChanged();
        notifyObservers();
        if(countObservers()==0) {
            stop();
        }
    }

    private Cell[][] createNewGame(int rows, int cols) {
        Cell[][] cells = new Cell[rows][cols];
        for(int i =0;i<rows;i++) {
            for(int j=0;j<cols;j++)
                cells[i][j]=new Cell(i,j);
        }
        return cells;
    }

}
