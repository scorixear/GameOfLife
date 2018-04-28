package gamemechanic;

/**
 * author: Paul Keller
 * date: 26.04.2018
 * version: 1.0
 */
/*
*   Diese Klasse repräsentiert eine Zelle im Modell Game of Life. Sie weiß ihre Position im Spiel und ihren aktuellen Status ("Lebendig" oder "Tot)
*   Sie kann ebenso ihre nächste Generation vorraussehen (checkNeighbours)
 */
public class Cell {
    private int col,row;
    private boolean alife;

    Cell(int row, int col){
        super();
        this.row=row;
        this.col=col;
        alife = false;

    }
    void setLife(boolean a){alife=a;}
    public boolean isAlife(){return alife;}
    boolean checkNeighbours(Cell[][] cells)
    {

        int minusrow = (row-1==-1)?cells.length-1:row-1;
        int minuscol=(col-1==-1)?cells[0].length-1:col-1;
        int plusrow = (row+1==cells.length)?0:row+1;
        int pluscol = (col+1==cells[0].length)?0:col+1;

        int lifecount= cells[minusrow][minuscol].isAlife()?1:0;
        lifecount= cells[minusrow][col].isAlife()?lifecount+1:lifecount;
        lifecount= cells[minusrow][pluscol].isAlife()?lifecount+1:lifecount;

        lifecount= cells[row][minuscol].isAlife()?lifecount+1:lifecount;
        lifecount= cells[row][pluscol].isAlife()?lifecount+1:lifecount;

        lifecount= cells[plusrow][minuscol].isAlife()?lifecount+1:lifecount;
        lifecount= cells[plusrow][col].isAlife()?lifecount+1:lifecount;
        lifecount= cells[plusrow][pluscol].isAlife()?lifecount+1:lifecount;


        if(isAlife())
        {
            return lifecount >= 2 && lifecount <= 3;
        }
        return lifecount == 3;
    }
    public void setAlife(boolean b){
        alife=b;
    }

    public int getRow() {
        return row;
    }
    public int getCol(){
        return col;
    }
}
