package toolsForGui;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

import cellsociety_team05.GUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 
 * @author emanuele
 *
 */
public class PopulationChartBox extends HorizontalControlBoxes{

	private HBox myHBox;
	private GUI myGui;
	
	public PopulationChartBox(GUI gui, Stage primaryStage){
		myGui = gui;
		myHBox = new HBox();
		myHBox.setPrefHeight(20);
		myHBox.setSpacing(20.0);
		myHBox.setAlignment(Pos.CENTER);
		LineChart<Number, Number> sc = createChart();
		myHBox.getChildren().add(sc);
	}
	
	@Override
	public HBox getBox(){
		return myHBox;
	}
	
	
	private static final int MAX_DATA_POINTS = 50;
    private int xSeriesData = 0;
    private XYChart.Series series1;
    private XYChart.Series series2;
    private XYChart.Series series3;
    private ConcurrentLinkedQueue<Number> dataQ1 = new ConcurrentLinkedQueue<Number>();
    private ConcurrentLinkedQueue<Number> dataQ2 = new ConcurrentLinkedQueue<Number>();
    private ConcurrentLinkedQueue<Number> dataQ3 = new ConcurrentLinkedQueue<Number>();
    private NumberAxis xAxis;
    
    private LineChart createChart(){
    	xAxis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS/10);
		xAxis = new NumberAxis(0,MAX_DATA_POINTS,MAX_DATA_POINTS/10);
		xAxis.setForceZeroInRange(false);
		xAxis.setAutoRanging(false);
		
		
		xAxis.setTickLabelsVisible(false);
		xAxis.setTickMarkVisible(false);
		xAxis.setMinorTickVisible(false);
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setAutoRanging(true);
		LineChart<Number, Number> sc = new LineChart<Number, Number>(xAxis, yAxis);
		  
		sc.setAnimated(false);
		sc.setId("liveLineeChart");
		
		//-- Chart Series
		series1 = new XYChart.Series<Number, Number>();
		series2 = new XYChart.Series<Number, Number>();
		series3 = new XYChart.Series<Number, Number>();
		sc.getData().addAll(series1, series2, series3);
		  
		return sc;
   }

    public void AddToQueue(int[] data) {
        dataQ1.add(data[0]);
        dataQ2.add(data[1]);
        dataQ3.add(data[2]);
    }

    public void addDataToSeries() {
        for (int i = 0; i < 20; i++) { //-- add 20 numbers to the plot+
            if (dataQ1.isEmpty()) break;
            series1.getData().add(new AreaChart.Data(xSeriesData++, dataQ1.remove()));
            series2.getData().add(new AreaChart.Data(xSeriesData++, dataQ2.remove()));
            series3.getData().add(new AreaChart.Data(xSeriesData++, dataQ3.remove()));
        }
        // remove points to keep us at no more than MAX_DATA_POINTS
        if (series1.getData().size() > MAX_DATA_POINTS) {
            series1.getData().remove(0, series1.getData().size() - MAX_DATA_POINTS);
        }
        if (series2.getData().size() > MAX_DATA_POINTS) {
            series2.getData().remove(0, series2.getData().size() - MAX_DATA_POINTS);
        }
        if (series3.getData().size() > MAX_DATA_POINTS) {
            series3.getData().remove(0, series3.getData().size() - MAX_DATA_POINTS);
        }
        // update 
        xAxis.setLowerBound(xSeriesData-MAX_DATA_POINTS);
        xAxis.setUpperBound(xSeriesData-1);
    }

}
