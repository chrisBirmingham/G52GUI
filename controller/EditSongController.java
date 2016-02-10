/**
 * Class to edit the metadata of music files
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;

import model.MusicFile;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditSongController implements Initializable {
	private Stage stage;
	@FXML TextField songNameArea;
	@FXML TextField songAuthorArea;
	@FXML TextField songAlbumnArea;
	@FXML TextField songGenreArea;
	@FXML Button editButton;
	@FXML Button cancelButton;
	MusicFile musicFile;
	
	public void setMusicFile(MusicFile file){
		this.musicFile = file;
		this.songNameArea.setStyle("-fx-font: 10pt 'Arial';");
		this.songAuthorArea.setStyle("-fx-font: 10pt 'Arial';");
		this.songAlbumnArea.setStyle("-fx-font: 10pt 'Arial';");
		this.songGenreArea.setStyle("-fx-font: 10pt 'Arial';");
		this.songNameArea.setText(this.musicFile.getSongName());
		this.songAuthorArea.setText(this.musicFile.getSongAuthor());
		this.songAlbumnArea.setText(this.musicFile.getAlbumnName());
		this.songGenreArea.setText(this.musicFile.getSongGenre());
	}
	
	public void setStage(Stage editStage) {
		this.stage = editStage;
	}
	
	public void close(){
		this.stage.close();
	}
	
	
	public void editSong(){
		String editName = this.songNameArea.getText();
		String editAuthor = this.songAuthorArea.getText();
		String editAlbumn = this.songAlbumnArea.getText();
		String editGenre = this.songGenreArea.getText();
		if(editName != this.musicFile.getSongName()){
			this.musicFile.setSongName(editName);
		}
		if(editAuthor != this.musicFile.getSongAuthor()){
			this.musicFile.setSongAuthor(editAuthor);
		}
		if(editAlbumn != this.musicFile.getAlbumnName()){
			this.musicFile.setSongAlbumnName(editAlbumn);
		}
		if(editAlbumn != this.musicFile.getSongGenre()){
			this.musicFile.setSongGenre(editGenre);
		}
		this.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
