/**
 * Class to read the settings file and determine some actions based on them
 */
package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SettingsFinder {

	/**
	 * Method to check where the music directory is
	 * @return
	 */
	static String findSettings(){
		FileReader reader = null;
		String filePath = null;
		try {
			reader = new FileReader("src/.metadata/settings.txt");
			BufferedReader textReader = new BufferedReader(reader);
			filePath = textReader.readLine();
			textReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Settings file not found");
		} catch (IOException e) {
			System.out.println("Failed to read settings file");
		}
		return filePath;
	}
	
	/**
	 * File to check which OS the program is running on
	 * @return
	 */
	static String findOS(){
		FileReader reader = null;
		String filePath = null;
		try {
			reader = new FileReader("src/.metadata/settingsOS.txt");
			BufferedReader textReader = new BufferedReader(reader);
			filePath = textReader.readLine();
			textReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Settings file not found");
		} catch (IOException e) {
			System.out.println("Failed to read settings file");
		}
		if(filePath.startsWith("win") || filePath.startsWith("Win")){
			return "\\";
		} else {
			return "/";
		}
	}
}
