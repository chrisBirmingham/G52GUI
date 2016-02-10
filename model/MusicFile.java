/**
 * Music file class, has a music file associated to it
 */
package model;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import controller.PopUpDisplayer;

public class MusicFile {
	private String songName;
	private String songAuthor;
	private String albumnName;
	private String absolutePath;
	private String genre;
	
	public MusicFile(String songName, String songAuthor, String albumName, String path, String genre){
		this.songName = songName;
		this.songAuthor = songAuthor;
		this.albumnName = albumName;
		this.absolutePath = path;
		this.genre = genre;
	}
	
	public String getSongName(){
		return this.songName;
	}
	
	public String getSongAuthor(){
		return this.songAuthor;
	}
	
	public String getAlbumnName(){
		return this.albumnName;
	}
	
	public String getAbsolutePath(){
		return this.absolutePath;
	}
	
	public String getSongGenre(){
		return this.genre;
	}
	
	public void setSongName(String name){
		this.songName = name;
		this.changeSongData("name");
	}
	
	public void setSongAuthor(String author){
		this.songAuthor = author;
		this.changeSongData("author");
	}
	
	public void setSongAlbumnName(String albumn){
		this.albumnName = albumn;
		this.changeSongData("albumn");
	}
	
	public void setSongGenre(String genre){
		this.genre = genre;
		this.changeSongData("genre");
	}
	
	/**
	 * Method to create temp file in case something goes wrong
	 */
	private void createTempFile(){	
		System.out.println(this.absolutePath.lastIndexOf(SettingsFinder.findOS()));
		System.out.println(this.absolutePath.lastIndexOf("."));
		FileWriter.openFile(this.absolutePath, this.songName, "temp" + this.absolutePath.substring(this.absolutePath.lastIndexOf("."), this.absolutePath.length()) , this.albumnName);
	}
	
	/**
	 * Method to write metadata to the actual music file. If file changes album it tries to move the file to the different album
	 * If anything fails renames temp fiel to the name of the file that was deleted
	 * @param tagType
	 */
	private void changeSongData(String tagType){
		this.createTempFile();
		boolean changedAlbumn = false; 
		try {
			AudioFile file = AudioFileIO.read(new File(this.absolutePath));
			Tag tag = file.getTag();
			switch(tagType){
				case "name":
					tag.setField(FieldKey.TITLE, this.songName);
					break;
				case "author":
					tag.setField(FieldKey.ARTIST, this.songAuthor);
					break;
				case "albumn":
					tag.setField(FieldKey.ALBUM, this.albumnName);
					changedAlbumn = true;
					break;
				case "genre":
					tag.setField(FieldKey.GENRE, this.genre);
					break;
			}
			try {
				AudioFileIO.write(file);
				if(changedAlbumn){
					FileWriter.openFile(this.absolutePath, this.songName, (new File(this.absolutePath)).getName(), this.albumnName);
					DeleteMusic destroyer = new DeleteMusic();
					destroyer.deleteSong(this.absolutePath, this.albumnName);
				} 
			} catch (CannotWriteException e) {
				System.out.println("Failed to write to file");
				File newFile = new File(this.getAbsolutePath());
				File tempFile = new File(this.getAbsolutePath().substring(0, this.absolutePath.lastIndexOf(SettingsFinder.findOS())) + "temp" + this.absolutePath.substring(this.absolutePath.lastIndexOf("."), this.absolutePath.length()));
				tempFile.renameTo(newFile);
			}
		} catch (CannotReadException | IOException | TagException| ReadOnlyFileException | InvalidAudioFrameException e) {
			System.out.println("failed to write to file");
			File newFile = new File(this.getAbsolutePath());
			File tempFile = new File(this.getAbsolutePath().substring(0, this.absolutePath.lastIndexOf(SettingsFinder.findOS())) + SettingsFinder.findOS() + "temp" + this.absolutePath.substring(this.absolutePath.lastIndexOf("."), this.absolutePath.length()));
			tempFile.renameTo(newFile);
			PopUpDisplayer displayer = new PopUpDisplayer();
			displayer.showPopUpBox("edit error");
		}
		
	}
}
