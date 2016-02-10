/**
 * Class to check user given music files
 */
package model;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

public class MusicChecker {
	private String errorHeader;
	private String errorMessage;

	public MusicChecker() {

	}

	/**
	 * Method to check if the file is a music file/readable music file
	 * @param file
	 * @return
	 */
	public boolean checkFile(File file) {
		String filePath;
		String fileNameWithExtension = file.getName();
		boolean readFile;
		Tag tag = readMusicFile(file);
		try {
			String fileName = tag.getFirst(FieldKey.TITLE);
			String albumnName = tag.getFirst(FieldKey.ALBUM);
			filePath = file.getAbsolutePath();
			readFile = FileWriter.openFile(filePath, fileName, fileNameWithExtension,
					albumnName);
			if (readFile) {
				return true;
			} else {
				this.errorHeader = "Read Error";
				this.errorMessage = "I'm sorry but there was a problem reading the file";
				return false;
			}
		} catch (Exception e) {
			this.errorHeader = "Not Music File";
			this.errorMessage = "The file you gave is not a music file";
			return false;
		}
	}

	/**
	 * Method reads the file and creates errors if something goes wrong
	 * @param musicFile
	 * @return
	 */
	private Tag readMusicFile(File musicFile) {
		AudioFile file = null;
		try {
			file = AudioFileIO.read(musicFile);
		} catch (CannotReadException e) {
			this.errorHeader = "Read Exception";
			this.errorMessage = "Sorry but the file cannot be read";
			return null;
		} catch (IOException e) {
			this.errorHeader = "I/O Exception";
			this.errorMessage = "I'm sorry but the file is not there";
			return null;
		} catch (TagException e) {
			this.errorHeader = "Tag Exception";
			this.errorMessage = "I'm sorry but that was a tag error";
			return null;
		} catch (ReadOnlyFileException e) {
			this.errorHeader = "Read only Exception";
			this.errorMessage = "I'm sorry but the file is read only";
			return null;
		} catch (InvalidAudioFrameException e) {
			this.errorHeader = "Invalid File-Type";
			this.errorMessage = "I'm sorry but the file is not a music file";
			return null;
		}
		Tag tag = file.getTag();
		return tag;
	}

	public String getErrorHeader() {
		return this.errorHeader;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}
}
