/**
 * Class to control the playMusicView 
 * and talk to the musicPlayer model to 
 * decide whether to play/pause or skip 
 * a track
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.MusicFile;
import model.MusicPlayer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class PlayMusicController implements Initializable {

	private boolean playingMusic;
	private boolean firstSongSeen = false;
	@FXML
	Circle playPauseButton;
	@FXML
	Circle forwardsButton;
	@FXML
	ImageView forwardsImage;
	@FXML
	Circle backwardsButton;
	@FXML
	ImageView backwardsImage;
	@FXML
	Label songLabel;
	@FXML
	ImageView playPauseImage;
	private MusicPlayer musicPlayer = new MusicPlayer(this);
	private boolean newSong = false;

	/**
	 * Sets the song in the model to be played by
	 * javas media player
	 * @param file
	 * 		The clicked on music file
	 */
	public void getClickedOnSong(MusicFile file) {
		this.musicPlayer.setMedia(file);
		this.firstSongSeen = true;
		this.newSong = true;
	}

	/**
	 * Method to decide whether to play/pause or 
	 * change the current song
	 */
	public void playPauseSong() {
		if (this.firstSongSeen) {
			if (!this.playingMusic) {
				this.playingMusic = true;
				this.musicPlayer.playSong();
			} else {
				if(this.musicPlayer.getPlayingSong()){
					if(this.newSong){
						this.musicPlayer.changeWhilePlaying();
						this.newSong = false;
					} else {
						this.musicPlayer.pauseSong();
						this.playingMusic = false;
					}
				} else {
					this.playingMusic = false;
					this.musicPlayer.pauseSong();
				}
			}
		}
		this.changeImage();
	}

	/**
	 * Changes the playPauseButtons image based on if a song 
	 * is being played or not
	 */
	public void changeImage() {
		if (this.playingMusic) {
			Image image = new Image("images/pause.jpg");
			this.playPauseImage.setImage(image);
		} else {
			Image image = new Image("images/play.jpg");
			this.playPauseImage.setImage(image);
		}
	}

	/**
	 * Calls the model to skip to next song
	 */
	public void getNextSong() {
		if(this.firstSongSeen){
			this.musicPlayer.getSong(false);
		}
	}

	/**
	 * Calls the model to go back a track
	 */
	public void getPreviousSong() {
		if(this.firstSongSeen){
			this.musicPlayer.getSong(true);
		}
	}

	public void changeLabel(String message) {
		this.songLabel.setText(message);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.playPauseImage.setImage(new Image("images/play.jpg"));
		this.forwardsImage.setImage(new Image("images/forwards.jpg"));
		this.backwardsImage.setImage(new Image("images/backwards.jpg"));
	}

	public void setSongList(ArrayList<MusicFile> list) {
		this.musicPlayer.setSongList(list);
	}
}
