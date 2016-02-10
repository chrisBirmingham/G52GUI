package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class to call the music finder class and 
 * returns arraylist containing all songs
 * in a given directory
 * @author cxb03u
 *
 */
public class SongCollector {
	private ObservableList<MusicFile> songData = FXCollections.observableArrayList();
	private ArrayList<MusicFile> musicList;
	
	public SongCollector(String albumnName){
		this.setAlbumnToSearch(albumnName);
	}
	
	/**
	 * Method to tell file finder to search a specific directory
	 * @param albumnName
	 */
	private void setAlbumnToSearch(String albumnName){
		MusicFinder finder = new MusicFinder();
		ArrayList<MusicFile> musicFiles = finder.findMusicFiles(albumnName);
		this.musicList = musicFiles;
		this.setTableInfo(musicFiles);
	}
	
	/**
	 * Add found files to an array list.
	 * @param musicFiles
	 */
	private void setTableInfo(ArrayList<MusicFile> musicFiles){
		this.songData.clear();
		for(MusicFile file : musicFiles){
			this.songData.add(file);
		}
	}
	
	public ArrayList<MusicFile> getSongList(){
		return this.musicList;
	}
	
	public ObservableList<MusicFile> getSongData(){ 
		return this.songData; 
	}
}
