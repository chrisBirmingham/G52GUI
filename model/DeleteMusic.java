/**
 * Class to delete music and albums
 */
package model;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DeleteMusic {
	
	/**
	 * Method to delete the selected music file
	 * @param filePath
	 * @param albumnName
	 * @return
	 */
	public boolean deleteSong(String filePath, String albumnName) {
		try {
			System.out.println(filePath);
			Path path = Paths.get(filePath);
			Files.delete(path);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * If the folder is empty, java attempts to delete the folder as well 
	 * @param filePath
	 * @return
	 */
	public boolean deleteFolder(String filePath){
		File folder = new File(SettingsFinder.findSettings() + SettingsFinder.findOS() + filePath);
		File[] listOfFiles = folder.listFiles();
		if(listOfFiles.length == 0){
			try{
				Files.delete(Paths.get(folder.getPath()));
				return true;
			} catch (Exception e){
				return false;
			}
		} else {
			return false;
		}
	}
}
