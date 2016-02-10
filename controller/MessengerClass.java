/**
 * Messenger class to handle communication between different controllers
 * @Author Christopher Birmingham
 */
package controller;

import java.util.ArrayList;

import model.MusicFile;
import javafx.scene.control.ScrollPane;

public class MessengerClass {
	private Main main;
	private PlayMusicController playMusicController;
	private MusicFile musicFile = null;
	private MainScreenController mainScreenController;
	
	public MessengerClass(Main main){
		this.main = main;
	}
	
	public void setMainScreenController(MainScreenController mainScreenController){
		this.mainScreenController = mainScreenController;
	}
	
	public void setPlayMusicController(PlayMusicController controller){
		this.playMusicController = controller;
	}
	
	public void setSong(MusicFile file) {
		this.playMusicController.getClickedOnSong(file);
		this.musicFile = file;
	}
	
	public void setSongList(ArrayList<MusicFile> list){
		this.playMusicController.setSongList(list);
	}
	
	public void tellMainToShowMusic(ScrollPane scrollPane, String string){
		this.main.showMusicSection(scrollPane, string);
	}
	
	public void editSong(){
		if(this.musicFile != null){
			PopUpDisplayer displayer = new PopUpDisplayer();
			displayer.showEditSongBox(this.musicFile);
			this.mainScreenController.showMainFolderOrMusicSelection(false);
		}
	}
	
	public void tellMainScreenToUpDate(){
		this.mainScreenController.displayDirectorySelector();
	}
}
