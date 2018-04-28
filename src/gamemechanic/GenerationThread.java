package gamemechanic;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
/*
 * Die GenerationThread-Klasse ist der Thread, welcher in einem bestimmten Zeitintervall einen Generationswechsel anstößt. Dafür wird eine double-Variable speed genutzt.
 */
public class GenerationThread extends Thread {
    private double speed;
    private GameOfLife life;
    private boolean interrupt=false;
    GenerationThread(GameOfLife l){
        life=l;
        speed = 1;
    }
    @Override
    public void run() {
        interrupt=false;
        while(!interrupt)
        {
            life.doGeneration();


            try {
                Thread.sleep((int)(250/speed));
            } catch (InterruptedException e) {
                interrupt=true;
            }
        }

    }
    void stopThread(){interrupt=true;}
    void setSpeed(double speed) {
        this.speed = speed;
    }
    double getSpeed(){
        return speed;
    }
}
