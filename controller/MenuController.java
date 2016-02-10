package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

public class MenuController implements Initializable {

	@FXML private MenuItem about;
	@FXML private MenuItem close;
	@FXML private MenuItem addMusic;
	@FXML private MenuItem editMusic;
	@FXML private MenuItem deleteMusic;
	@FXML private MenuItem changeMusicDirectory;
	@FXML private MenuItem license;
	@FXML private MenuItem help;
	private MessengerClass messenger;
	
	private Main main;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	public void setMessengerClass(MessengerClass messenger){
		this.messenger = messenger;
	}
	
	public void showAboutBox(){
		PopUpDisplayer displayer = new PopUpDisplayer();
		displayer.showPopUpBox("about");
	}

	public void setMainApp(Main main) {
		this.main = main;
	}
	
	public void closeProgram(){
		this.main.closeProgram();
	}
	
	public void addMusic(){
		this.main.showUploadBox();
	}
	
	public void showLicenseBox(){
		PopUpDisplayer displayer = new PopUpDisplayer();
		displayer.showPopUpBox("license");
	}
	
	public void showHelpBox(){
		PopUpDisplayer displayer = new PopUpDisplayer();
		displayer.showPopUpBox("help");
	}
	
	public void editMusic(){
		this.messenger.editSong();
	}
	
	public void changeMusicDirectory(){
		this.messenger.tellMainScreenToUpDate();
	}

}
