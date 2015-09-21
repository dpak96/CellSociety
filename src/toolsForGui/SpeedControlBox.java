package toolsForGui;

import cellsociety_team05.GUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class SpeedControlBox extends HorizontalControlBoxes{
	
	private HBox myHBox;
	
	public SpeedControlBox(int DEFAULT_SPEED, GUI myGui){
		Label sliderDescription = new Label("Speed");
		Label speedValue = new Label(Integer.toString(DEFAULT_SPEED));
		myHBox = new HBox();
		myHBox.setPrefHeight(20);
		myHBox.setSpacing(20.0);
	    myHBox.setAlignment(Pos.CENTER);
		Slider slider = new Slider(0, 10, DEFAULT_SPEED);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
            	long updatedSpeed = Math.round(slider.getValue());
            	speedValue.setText(Long.toString(updatedSpeed)); 
            	if(updatedSpeed != myGui.getSimulationSpeed()){
            		myGui.updateSimulationSpeed(updatedSpeed);
            		System.out.println("Speed " + updatedSpeed);
            	}
            }
        });
		speedValue.setPrefWidth(20);
	    myHBox.getChildren().addAll(sliderDescription, slider, speedValue);
	    
	}

	@Override
	public HBox getBox() {
		return myHBox;
	}
	
	
	
}
