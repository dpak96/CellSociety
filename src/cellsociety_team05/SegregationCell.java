package cellsociety_team05;

public class SegregationCell extends Cell {
    private final String[] myPossibleStates = {"X/Blue", "O/Red", "Empty"};

    public SegregationCell (Grid grid, int xCoordinate, int yCoordinate, 
                            int startingState/*, Simulation simulation*/) {
        super.Cell(grid, xCoordinate, yCoordinate, startingState/*, simulation*/);
    }

}
