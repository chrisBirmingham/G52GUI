/**
 * Class displays popup to find where users music is. User selects the directory
 */
package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SelectDirectoryController {
	@FXML private Button okButton;
	@FXML private Button exitButton;
	private Stage stage;
	private File chosenFolder;
	private MainScreenController mainScreenController;
	
	public void setStage(Stage stage){
		this.stage = stage;
	}
	
	/**
	 * Open dialog box for user to select where the music directory is
	 */
	public void selectFolder(){
		DirectoryChooser chooser = new DirectoryChooser();
		this.chosenFolder = chooser.showDialog(this.stage);
		if(this.chosenFolder != null){
			this.closeWindow();
			this.setFilePath(this.chosenFolder.getAbsolutePath());
		}
	}
	
	public void closeWindow(){
		this.stage.close();
	}
	
	/**
	 * Sets the music directory in the settings folder
	 * @param path
	 */
	private void setFilePath(String path){
		try {
			PrintWriter writer = new PrintWriter("src/.metadata/settings.txt");
			writer.print(path);
			writer.close();
			this.mainScreenController.findMusicAlbumns();
		} catch (FileNotFoundException e) {
			System.out.println("Settings file not there");
		}
	}

	public void setMainScreenController(MainScreenController mainScreenController) {
		this.mainScreenController = mainScreenController;
	}
}
