package gamemechanic;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
public class GameOfLife extends Observable {
    private Cell[][] cells;
    GenerationThread thread;
    private ArrayList<Observer> observers;
    public GameOfLife(int rows, int cols)
    {
        cells =createNewGame(rows,cols);
        thread= new GenerationThread(this);
        observers=new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer)
    {

        if(!observers.contains(observer))
        {
            observers.add(observer);
            super.addObserver(observer);
        }
    }

    @Override
    public void deleteObserver(Observer observer)
    {

        if(observers.contains(observer))
        {
            observers.remove(observer);
            super.deleteObserver(observer);
        }
    }

    public ArrayList<Observer> getObservers(){
        return observers;
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

    protected void doGeneration(){
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

    public void fillRandom() {
        for(int i=0;i<cells.length;i++) {
            for(int j=0;j<cells[i].length;j++) {
                cells[i][j].setLife(new Random().nextBoolean());
            }
        }
    }
}
