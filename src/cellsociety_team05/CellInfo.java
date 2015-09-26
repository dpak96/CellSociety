package cellsociety_team05;

public class CellInfo {
    private int x;
    private int y;
    private int state;

    public CellInfo (int xc, int yc, int stat) {
        x = xc;
        y = yc;
        state = stat;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public int getState () {
        return state;
    }
}
