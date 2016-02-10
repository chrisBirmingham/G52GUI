/**
 * Media Player class
 * Plays, pauses, skips music
 */
package model;

import java.io.File;
import java.util.ArrayList;

import controller.PlayMusicController;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MusicPlayer {
	private Media song; // new
						// Media("http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
	private MediaPlayer player;
	private boolean newSong = false;
	private PlayMusicController controller; 
	private MusicFile musicFile;
	private boolean playingSong = false;
	private ArrayList<MusicFile> list;
	
	public MusicPlayer(PlayMusicController controller) {
		this.controller = controller;
	}

	/**
	 * Sets the new media object. Called from the controller
	 * @param file
	 * 		The clicked on music file
	 */
	public void setMedia(MusicFile file) {
		String filePath = file.getAbsolutePath();
		this.musicFile = file;
		//filePath = filePath.replace("\\", "/");
		this.song = new Media(new File(filePath).toURI().toString());
		this.newSong = true;
	}

	/**
	 * Method to play music 
	 */
	public void playSong() {
		if (this.newSong) {
			this.player = new MediaPlayer(this.song);
			this.newSong = false;
			this.playingSong = true;
		}
		this.player.play();
		this.playingSong = true;
		this.runnableFunction();
	}

	/**
	 * Method to pause music
	 */
	public void pauseSong() {
		this.player.pause();
		this.playingSong = false;
		this.controller.changeLabel("Paused " + this.musicFile.getSongName());
	}
	
	/**
	 * Method to add a runnable class onto the media player. 
	 * Runnable class detects when the song has ended and tries
	 * to get the next song from the list
	 */
	private void runnableFunction(){
		this.controller.changeLabel("Playing " + this.musicFile.getSongName());
		this.player.setOnEndOfMedia(new Runnable(){
			public void run(){
				getSong(false);
			}
		});
	}

	/**
	 * Method that changes the track while another song is 
	 * playing
	 */
	public void changeWhilePlaying() {
		this.player.pause();
		this.player = new MediaPlayer(this.song);
		this.player.play();
		this.newSong = false;
		this.playingSong = true;
		this.runnableFunction();
	}

	public boolean getPlayingSong() {
		return this.playingSong;
	}
	
	public void setSongList(ArrayList<MusicFile> list){
		this.list = list;
	}
	
	/**
	 * Method to get the previous or next song in the song list.
	 * If there isn't another song in the list it kills the music
	 * player
	 * @param before
	 * 			Boolean to say if the song should be before the last 
	 * 			played song or after
	 */
	public void getSong(boolean before) {
		boolean lastSong = false;
		int index = 0;
		for (MusicFile file : this.list) {
			if (file == this.musicFile) {
				break;
			}
			index++;
		}
		index++;
		if (before) {
			index--;
			index--;
			if(index < 0){
				index++;
			}
			if(index > this.list.size()){
				index++;
			}
			this.setMedia(this.list.get(index));
			if(this.playingSong){
				this.changeWhilePlaying();
				this.controller.changeLabel("Playing " + this.list.get(index).getSongName());
			} else {
				this.controller.changeLabel("Awaiting " + this.list.get(index).getSongName());
			}
		} else {
			if(index >= this.list.size()){
				lastSong = true;
			}
			if(!lastSong){
				this.setMedia(this.list.get(index));
				if(this.playingSong){
					this.changeWhilePlaying();
					this.controller.changeLabel("Playing " + this.list.get(index).getSongName());
				} else {
					this.controller.changeLabel("Awaiting " + this.list.get(index).getSongName());
				}
			} else {
				if(this.playingSong){
					this.player.seek(this.player.getStopTime());
				}
				this.controller.changeLabel("Not Playing");
			}
		}
	}
	
	public MusicFile getmusicFile(){
		return this.musicFile;
	}
}