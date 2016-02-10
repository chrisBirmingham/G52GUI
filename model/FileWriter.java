/**
 * Class to save user given music into the music directory
 */
package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter {
	
	/**
	 * Method to read the file given by the user
	 * @param path
	 * @param fileName
	 * @param fileNameWithExtension
	 * @param albumnName
	 * @return
	 */
	public static boolean openFile(String path, String fileName,String fileNameWithExtension, String albumnName) {
		Path filePath = Paths.get(path);
		byte[] fileArray;
		boolean wroteFile;
		try {
			fileArray = Files.readAllBytes(filePath);
			wroteFile = writeFile(fileArray, fileName,fileNameWithExtension, albumnName);
			if (wroteFile) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Method to the write the file to the music directory based on its album name
	 * @param fileArray
	 * @param fileName
	 * @param fileNameWithExtension
	 * @param albumnName
	 * @return
	 */
	private static boolean writeFile(byte[] fileArray, String fileName,String fileNameWithExtension, String albumnName) {
		FileOutputStream out;
		try {
			if (albumnName == "") {
				out = new FileOutputStream(SettingsFinder.findSettings() + SettingsFinder.findOS() + "Unknown_Albumn" + SettingsFinder.findOS() +  fileNameWithExtension);
				out.write(fileArray);
				out.close();
			} else {
				if (new File(SettingsFinder.findSettings() + albumnName).exists()) {
					out = new FileOutputStream(SettingsFinder.findSettings() + SettingsFinder.findOS() + albumnName+ SettingsFinder.findOS() + fileNameWithExtension);
					out.write(fileArray);
					out.close();
				} else {
					File directory = new File(SettingsFinder.findSettings() + SettingsFinder.findOS() + albumnName + SettingsFinder.findOS());
					directory.mkdirs();
					out = new FileOutputStream(SettingsFinder.findSettings() + SettingsFinder.findOS() + albumnName + SettingsFinder.findOS() + fileNameWithExtension);
					out.write(fileArray);
					out.close();
				}
			}
			System.out.println("saved file " + fileName);
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

	}
}
