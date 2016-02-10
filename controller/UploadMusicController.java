package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UploadMusicController implements Initializable {

	@FXML
	private Button cancel;
	@FXML
	private Button uploadFile;
	private Main main;
	private Stage stage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
	
	public void setStage(Stage stage){
		this.stage = stage;
	}

	public void setMainApp(Main main) {
		this.main = main;
	}

	public void removeBox() {
		this.stage.close();
	}

	public void addMusic() {
		this.main.openFile();
		this.stage.close();
	}

}
