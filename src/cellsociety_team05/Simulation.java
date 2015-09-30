// This entire file is part of my masterpiece.
// Lucas Donaldson

package cellsociety_team05;

import java.util.ArrayList;
import java.util.Collections;
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
    protected GUI myGUI;
    private static final int MILLISECOND_DELAY = 1000;
    private Timeline myAnimation;
    protected Map<String, Double> myParameters;
    private boolean myPaused;
    protected int myWidth;
    protected int myHeight;
    protected int[] myStats;

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
    public Simulation (GridPane gridPane, GUI gui, Map<String, Double> params, 
                       List<CellInfo> list, int height, int width) {
        myWidth = width;
        myHeight = height;
        myParameters = params;
        myGrid = new ToroidGrid(getMyHeight(), myWidth, this, myParameters);
        try {
            readCellList(list);
        }
        catch (SimulationException e) {
            e.printStackTrace();
        }
        myAnimation = new Timeline();
        myGUI = gui;
        initializeGridPane(gridPane);
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
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        myAnimation.getKeyFrames().add(frame);
        myAnimation.setCycleCount(Animation.INDEFINITE);
        myAnimation.play();
        myPaused = false;
    }
    
    /**
     * stops animation
     */
    public void stopAnimation () {
        myAnimation.stop();
    }

    /**
     * pauses/resumes animation
     */
    public void changeFlow () {
        if (myPaused) {
            myAnimation.play();
        }
        else {
            myAnimation.pause();
        }
        myPaused = !myPaused;
    }

    /**
     * changes speed of animation
     *
     * @param speed
     */
    public void updateSpeed (double speed) {
        myAnimation.setRate(2 * (speed / 10));
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

    /**
     * connects grid of cells to GUI
     */
    protected void initializeGridPane (GridPane gridpane) {
        for (List<Cell> listCell : myGrid.getCellMatrix()) {
            for (Cell cell : listCell) {
                gridpane.getChildren().add(cell.getMySquare());
            }
        }
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
    public abstract List<List<Cell>> setUpCells ();

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

    /**
     * sets up simulation with randomized cells
     * @param states 
     * @return grid of cells
     */
    public List<List<Cell>> setUpRandomCells (int states) {
        ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i = 0; i < myWidth; i++) {
            list.add(new ArrayList<Cell>());
            for (int j = 0; j < myHeight; j++) {
                int state = (int) Math.floor(Math.random() * states);
                Cell newcell = makeCell(i, j, state, myGrid, myParameters);
                list.get(i).add(newcell);
            }
        }
        return list;
    }

    /**
     * returns number of cells of each type for use in graph display
     * @return myStats
     */
    public int[] getStats () {
        return myStats;
    }

    /**
     * returns name of simulation
     * @return string name of simulation
     */
    public abstract String getName ();

    /**
     * returns simulation grid height
     * @return myHeight
     */
    public int getMyHeight () {
        return myHeight;
    }
    
    /**
     * returns unmodifiable version of myParameters
     * @return myParameters
     */
    public Map<String, Double> getParams () {
        return Collections.unmodifiableMap(myParameters);
    }

    /**
     * returns unmodifiable list of cells in given row number
     * @param row
     * @return list of cells
     */
    public List<Cell> getGridRow (int row) {
        return Collections.unmodifiableList(myGrid.getCellMatrix().get(row));

    }
}
