package cellsociety_team05;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class TriangleCell {

    public Canvas myTriangle;
    private int a, b, c, d;
    public Cell topCell;
    public Cell bottomCell;
    private double side;
    private Color topColor;
    private Color bottomColor;
    private boolean alternate;

    public TriangleCell (int xCoordinate,
                         int yCoordinate,
                         GridPane myGrid,
                         double triangleSide) {
        // TODO Auto-generated constructor stub
        myTriangle = new Canvas(triangleSide, triangleSide);
        side = triangleSide;
        alternate = true;
        GraphicsContext gc = myTriangle.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        gc.strokePolygon(new double[] { 0, triangleSide, 0 },
                         new double[] { 0, 0, triangleSide }, 3);
        gc.setFill(Color.BLACK);
        gc.fillPolygon(new double[] { 0, triangleSide, 0 },
                       new double[] { 0, 0, triangleSide }, 3);
        gc.strokePolygon(new double[] { 0, side, 0 },
                         new double[] { side, 0, side }, 3);
        gc.setFill(Color.GOLD);
        gc.fillPolygon(new double[] { 0, side, 0 },
                       new double[] { side, 0, side }, 3);
        GridPane.setConstraints(myTriangle, xCoordinate, yCoordinate);
        myGrid.add(myTriangle, xCoordinate, yCoordinate);

    }

    public void updateVisualCells () {

        updateCells();
        GraphicsContext gc = myTriangle.getGraphicsContext2D();
        gc.setFill(topColor);
        gc.fillPolygon(new double[] { 0, side, 0 },
                       new double[] { 0, 0, side }, 3);
        gc.setFill(bottomColor);
        gc.fillPolygon(new double[] { 0, side, 0 },
                       new double[] { side, 0, side }, 3);

    }

    private void updateCells () {
        if (alternate) {
            topColor = Color.AQUAMARINE;
            bottomColor = Color.WHITE;
            alternate = !alternate;
        }
        else {
            topColor = Color.BLACK;
            bottomColor = Color.GOLD;
            alternate = !alternate;
        }

    }

}
