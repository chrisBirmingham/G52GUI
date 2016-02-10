package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopUpController {
	private Stage stage;
	@FXML private Button okButton;
	@FXML private Text textArea;
	
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	public void setText(String message){
		this.textArea.setStyle("-fx-font: 12pt 'Arial';");
		this.textArea.setText(message);
	}
	
	public void closeWindow(){
		this.stage.close();
	}
}
