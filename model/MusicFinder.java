/**
 * Class to search music directory for music files
 */
package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class MusicFinder {

	public MusicFinder() {
	}

	/**
	 * Method to find all the directories in a directory
	 * @return
	 */
	public File[] findMusicAlbumns() {
		File folder = new File(SettingsFinder.findSettings());
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}

	public boolean saveMusic() {
		return true;
	}
	
	/**
	 * Method to find all the music files in a given directory
	 * @param directory
	 * @return
	 * 	Array list with files in directory
	 */
	public ArrayList<MusicFile> findMusicFiles(String directory){
		File folder = new File(SettingsFinder.findSettings() + SettingsFinder.findOS() +  directory);
		File[] listOfFiles = folder.listFiles();
		return createMusicFileObjects(listOfFiles);
	}
	
	
	/**
	 * Method to create MusicFile objects and place into an
	 * array list
	 * @param listOfFiles
	 * @return
	 */
	private ArrayList<MusicFile> createMusicFileObjects(File[] listOfFiles){
		ArrayList<MusicFile> listOfMusicFiles = new ArrayList<MusicFile>();
		for(File file : listOfFiles){
			if(file.isFile()){
				AudioFile musicFile = null;
				try {
					musicFile = AudioFileIO.read(file);
					try{
						Tag tag = musicFile.getTag();
						String fileName = tag.getFirst(FieldKey.TITLE);
						String albumnName = tag.getFirst(FieldKey.ALBUM);
						String artist = tag.getFirst(FieldKey.ARTIST);
						String genre = tag.getFirst(FieldKey.GENRE);
						String path = file.getPath();
						MusicFile aMusicFile = new MusicFile(fileName, artist, albumnName, path, genre);
						listOfMusicFiles.add(aMusicFile);
					} catch(Exception e) {
						System.out.println("Non music file in directory");
					}
				} catch (CannotReadException | IOException | TagException
					| ReadOnlyFileException | InvalidAudioFrameException e) {
					System.out.println("Not music file");
				}
				
			}
		}
		return listOfMusicFiles;
	}
}
