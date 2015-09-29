package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;


/**
 *
 * @author emanuele macchi / Lucas Donaldson
 *
 */

public abstract class Simulation {

    protected Grid myGrid;
    protected double mySpeed;
    protected GridPane myGridPane;
    protected GUI myGUI;
    private static final int MILLISECOND_DELAY = 1000;
    protected Timeline animation;
    protected Map<String, Double> myParameters;
    private boolean paused;
    private int myWidth;
    private int myHeight;
    private List<CellInfo> myInfoList;
    protected int[] myStats;
    private String cellShape;

    /**
     * constructor for simulation class
     *
     * @param gridPane
     * @param gui
     * @param params
     * @param list
     * @param height
     * @param width
     */
    public Simulation (GridPane gridPane,
                       GUI gui,
                       Map<String, Double> params,
                       List<CellInfo> list,
                       int height,
                       int width,
                       String shape) {
        myWidth = width;
        setMyHeight(height);
        myInfoList = list;
        myParameters = params;
        myGridPane = gridPane;
        cellShape = shape;
        myGrid = new ToroidGrid(getMyHeight(), myWidth, this, myParameters);
        try {
            readCellList(myInfoList);
        }
        catch (SimulationException e) {
            e.printStackTrace();
        }
        animation = new Timeline();
        myGUI = gui;
        initializeGridPane();
    }

    /**
     * reads in list of cells specified in xml file and initializes grid
     *
     * @param list
     * @throws SimulationException
     */
    public void readCellList (List<CellInfo> list) throws SimulationException {
        if (list != null) {
            for (CellInfo cell : list) {
                Cell thisCell = myGrid.getCellMatrix().get(cell.getX()).get(cell.getY());
                thisCell.setMyCurrentState(cell.getState());
                thisCell.changeColor();
            }
        }
    }

    /**
     * begins animation
     */
    public void start () {
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                                      e -> step());
        animation.getKeyFrames().add(frame);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();
        paused = false;
    }

    /**
     * pauses/resumes animation
     */
    public void changeFlow () {
        if (paused) {
            animation.play();
        }
        else {
            animation.pause();
        }
        paused = !paused;
    }

    /**
     * changes speed of animation
     *
     * @param speed
     */
    public void updateSpeed (double speed) {
        mySpeed = speed;
        animation.setRate(2 * (speed / 10));
    }

    /**
     * performs single step of simulation
     */
    public void step () {
        myGrid.preUpdateGrid();
        myGrid.updateGrid();
        myStats = myGrid.getStats();
        myGUI.updateGraph();
    }

    public int[] getStats () {
        return myStats;
    }

    /**
     * connects grid of cells to GUI
     */
    protected void initializeGridPane () {
        for (List<Cell> listCell : myGrid.getCellMatrix()) {
            for (Cell cell : listCell) {
                myGridPane.getChildren().add(cell.getShape());
            }
        }
    }

    /**
     * stops animation
     */
    public void stopAnimation () {
        animation.stop();
    }

    /**
     * initializes cells of appropriate type for given simulation
     *
     * @param grid
     * @param width
     * @param height
     * @param map
     * @return
     */
    public abstract List<List<Cell>> setUpCells (Grid grid,
                                                 int width,
                                                 int height,
                                                 Map<String, Double> map);

    /**
     * initializes grid with randomized cell states
     */
    public void initGrid () {
    }

    /**
     * initializes cell of specific type for given simulation
     *
     * @param x
     * @param y
     * @param start
     * @param g
     * @param map
     * @return
     */
    public abstract Cell makeCell (int x, int y, int start, Grid g, Map<String, Double> map);

    public int getMyHeight () {
        return myHeight;
    }

    public void setMyHeight (int myHeight) {
        this.myHeight = myHeight;
    }

    public List<List<Cell>> setUpRandomCells (Grid grid,
                                              int width,
                                              int height,
                                              Map<String, Double> map,
                                              int states) {
        ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i = 0; i < width; i++) {
            list.add(new ArrayList<Cell>());
            for (int j = 0; j < height; j++) {
                int state = (int) Math.floor(Math.random() * states);
                Cell newcell = makeCell(i, j, state, grid, map);
                list.get(i).add(newcell);
            }
        }
        return list;
    }

    public String getShape () {
        return cellShape;
    }

    public void clear () {
        int temp = myGrid.getCellMatrix().get(0).get(0).getNumberStates();
        for (int i = 0; i < myHeight; i++) {
            for (int j = 0; j < myWidth; j++) {
                myGrid.getCellMatrix().get(i).get(j).setMyCurrentState(temp - 1);
            }
        }
        step();
    }

    public abstract String getName ();

    public Map<String, Double> getParams () {
        return myParameters;
    }

    public Grid getGrid () {
        return myGrid;
    }
}