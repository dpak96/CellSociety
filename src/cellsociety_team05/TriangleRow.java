package cellsociety_team05;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class TriangleRow {
    public Canvas myTriangles;
    private double height;
    public Cell topCell;
    public Cell bottomCell;
    private double side;
    private Color topColor;
    private Color bottomColor;
    private boolean alternate;

    public TriangleRow (int row, GridPane myGrid, double length, double gridSize) {
        // TODO Auto-generated constructor stub
        height = (gridSize / length);
        myTriangles = new Canvas(length * height, height);
        GraphicsContext gc = myTriangles.getGraphicsContext2D();
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);
        if (row % 2 == 0) {
            for (int i = 0; i < length; i++) {
                gc.setFill(Color.BLACK);
                gc.strokePolygon(new double[] { i * height, i * height + height / 2,
                                                i * height + height },
                                 new double[] { height, 0, height }, 3);
                gc.fillPolygon(new double[] { i * height, i * height + height / 2,
                                              i * height + height },
                               new double[] { height, 0, height }, 3);
                if (i < length - 1) {
                    gc.setFill(Color.GOLD);
                    gc.strokePolygon(new double[] { i * height + height / 2,
                                                    (i + 1) * height + height / 2,
                                                    (i + 1) * height },
                                     new double[] { 0, 0, height }, 3);
                    gc.fillPolygon(new double[] { i * height + height / 2,
                                                  (i + 1) * height + height / 2, (i + 1) * height },
                                   new double[] { 0, 0, height }, 3);
                }
            }
        }
        else {
            for (int i = 0; i < length; i++) {
                if (i < length - 1) {
                    gc.setFill(Color.BLACK);
                    gc.strokePolygon(new double[] { i * height + height / 2, i * height + height,
                                                    i * height + 3 * height / 2 },
                                     new double[] { height, 0, height }, 3);
                    gc.fillPolygon(new double[] { i * height + height / 2, i * height + height,
                                                  i * height + 3 * height / 2 },
                                   new double[] { height, 0, height }, 3);
                }
                gc.setFill(Color.GOLD);
                gc.strokePolygon(new double[] { i * height, (i + 1) * height,
                                                i * height + height / 2 },
                                 new double[] { 0, 0, height }, 3);
                gc.fillPolygon(new double[] { i * height, (i + 1) * height,
                                              i * height + height / 2 },
                               new double[] { 0, 0, height }, 3);

            }
        }
        myGrid.add(myTriangles, 0, row);
    }

    public void updateVisualCells () {
        /*
         * updateCells();
         * GraphicsContext gc = myTriangle.getGraphicsContext2D();
         * gc.setFill(topColor);
         * gc.fillPolygon(new double[]{0, side ,0 },
         * new double[]{0, 0, side}, 3);
         * gc.setFill(bottomColor);
         * gc.fillPolygon(new double[]{0, side ,0 },
         * new double[]{side, 0, side}, 3);
         */

    }

    private void updateCells () {
        /*
         * if(alternate){
         * topColor = Color.AQUAMARINE;
         * bottomColor = Color.WHITE;
         * alternate = !alternate;
         * } else {
         * topColor = Color.BLACK;
         * bottomColor = Color.GOLD;
         * alternate = !alternate;
         * }
         */

    }
}
