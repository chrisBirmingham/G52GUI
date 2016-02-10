package controller;

import model.SongCollector;
import model.MusicFile;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MusicAreaController{
	@FXML TableView<MusicFile> songTable;
	@FXML TableColumn<MusicFile, String> songNameColumn;
	@FXML TableColumn<MusicFile, String> songAuthorColumn;
	@FXML TableColumn<MusicFile, String> songGenreColumn;
	@FXML TableColumn<MusicFile, String> songAlbumnColumn;
	private MessengerClass messenger;
	
	public MusicAreaController(){
		
	}
	
	public void setMessengerClass(MessengerClass messenger){
		this.messenger = messenger;
	}
	
	private void notifyMain(MusicFile newValue) {
		this.messenger.setSong(newValue);
	}
	
	/**
	 * Method to fill the table with the collected information
	 */
	@FXML private void initialize(){
		songNameColumn.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("songName"));
        songAuthorColumn.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("songAuthor"));
        songGenreColumn.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("songGenre"));
        songAlbumnColumn.setCellValueFactory(new PropertyValueFactory<MusicFile, String>("albumnName"));
        this.songTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MusicFile>(){
            public void changed(ObservableValue<? extends MusicFile> observable, MusicFile oldValue, MusicFile newValue) {
                notifyMain(newValue);
            }});
    }

	public void setMainApp(String albumnName) {
		SongCollector collector = new SongCollector(albumnName);
		this.songTable.setItems(collector.getSongData());
		this.messenger.setSongList(collector.getSongList());
	}
	
}
