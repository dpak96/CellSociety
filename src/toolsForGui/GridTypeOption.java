package toolsForGui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;

/**
 * 
 * @author emanuele
 *
 */
public class GridTypeOption extends PersonalizationOption{
	private InitialChoiceDialog myDialog;
	private String[] myGridTypes = {"Normal", "Toroidal"};

	public GridTypeOption(InitialChoiceDialog dialog) {
		super("GridType");
		myDialog = dialog;
		initializeOption();
	}
	
	private void initializeOption(){
		ChoiceBox<String> myChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(myGridTypes));
		myChoiceBox.getSelectionModel().selectFirst();
		myChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>(){
					@Override
					public void changed(ObservableValue oc, Number value, Number newValue){
						myDialog.setGridType(myGridTypes[newValue.intValue()]);
					}
				}
		);
		this.setControl(myChoiceBox);
	}
}
