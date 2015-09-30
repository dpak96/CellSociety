package toolsForGui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

/**
 * 
 * @author emanuele
 *
 */
public class ShapeOption extends PersonalizationOption {

	private InitialChoiceDialog myDialog;
	private String[] myCellShapes;

	public ShapeOption(InitialChoiceDialog dialog, String[] possibleShapes) {
		super("Shape");
		myDialog = dialog;
		myCellShapes = possibleShapes;
		initializeOption();
	}

	private void initializeOption() {
		ChoiceBox<String> cellShapesBox = new ChoiceBox<String>(
				FXCollections.observableArrayList(myCellShapes));
		cellShapesBox.getSelectionModel().selectFirst();
		cellShapesBox.getSelectionModel().selectedIndexProperty()
				.addListener(new ChangeListener<Number>() {
					@Override
					public void changed(ObservableValue oc, Number value,
							Number newValue) {
						myDialog.setCellShape(myCellShapes[newValue.intValue()]);
					}
				});
		this.setControl(cellShapesBox);
	}

}
