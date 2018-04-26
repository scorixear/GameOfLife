package gamemechanic;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
public class GenerationThread extends Thread {
    double speed;
    GameOfLife life;
    boolean interrupt=false;
    public GenerationThread(GameOfLife l){
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
    public void stopThread(){interrupt=true;}
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public double getSpeed(){
        return speed;
    }
}
